package mm.chenme.lib.commutillibdemo.ui.other

import android.graphics.PointF
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
import androidx.recyclerview.widget.SnapHelper


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/3
 * Email：ibelieve1210@163.com
 */

class GridPagerSnapHelper// 得到私有字段

// 通過反射設置私有對象可以訪問

// 從父類中得到對象，并強制轉換為想要得到的對象
    (private  val    mLayoutManager: RecyclerView.LayoutManager) : SnapHelper() {
    private var mRow = DEFAULT_ROW
    private var mColumn = DEFAULT_COLUMN

    private var mRecyclerView: RecyclerView? = null

    fun setRow(row: Int): GridPagerSnapHelper {
        require(mRow > 0) { "row must be greater than zero" }
        mRow = row
        return this
    }

    fun setColumn(column: Int): GridPagerSnapHelper {
        require(mColumn > 0) { "column must be greater than zero" }
        mColumn = column
        return this
    }

    // Orientation helpers are lazily created per LayoutManager.
    @Nullable
    private var mVerticalHelper: OrientationHelper? = null

    @Nullable
    private var mHorizontalHelper: OrientationHelper? = null

    @Nullable
    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToCenter(
                layoutManager, targetView,
                getHorizontalHelper(layoutManager)
            )
        } else {
            out[0] = 0
        }
        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToCenter(
                layoutManager, targetView,
                getVerticalHelper(layoutManager)
            )
        } else {
            out[1] = 0
        }
        return out
    }

    private fun distanceToCenter(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View, helper: OrientationHelper
    ): Int {
        return if (layoutManager.canScrollHorizontally()) {
            val totalWidth = initRecyclerView().width
//            val totalWidth = mRecyclerView.width
            val columnWidth = totalWidth / mColumn
            val position = layoutManager.getPosition(targetView)
            val pageIndex = pageIndex(position)
            val currentPageStart = pageIndex * countOfpage()
            val distance = (position - currentPageStart) / mRow * columnWidth
            val childStart = helper.getDecoratedStart(targetView)
            childStart - distance
        } else { //数值方向
            val totalHeight = initRecyclerView().height
//            val totalHeight = mRecyclerView.height
            val rowHeight = totalHeight / mRow
            val position = layoutManager.getPosition(targetView)
            val pageIndex = pageIndex(position)
            val currentPageStart = pageIndex * countOfpage()
            val distance = (position - currentPageStart) / mColumn * rowHeight
            val childStart = helper.getDecoratedStart(targetView)
            childStart - distance
        }
    }

    @Nullable
    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager))
        } else if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager))
        }
        return null
    }

    /**
     * get the page of position
     *
     * @param position
     * @return
     */
    private fun pageIndex(position: Int): Int {
        return position / countOfpage()
    }

    /**
     * the total count of per page
     *
     * @return
     */
    private fun countOfpage(): Int {
        return mRow * mColumn
    }

    /**
     * Return the child view that is currently closest to the center of this parent.
     *
     * @param layoutManager The [RecyclerView.LayoutManager] associated with the attached
     * [RecyclerView].
     * @param helper        The relevant [android.support.v7.widget.OrientationHelper] for the attached [RecyclerView].
     * @return the child view that is currently closest to the center of this parent.
     */
    @Nullable
    private fun findCenterView(
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return null
        }
        var closestChild: View? = null
        val center: Int
        center = if (layoutManager.clipToPadding) {
            helper.startAfterPadding + helper.totalSpace / 2
        } else {
            helper.end / 2
        }
        var absClosest = Int.MAX_VALUE
        for (i in 0 until childCount) {
            val child: View? = layoutManager.getChildAt(i)
            val childCenter = (helper.getDecoratedStart(child)
                    + helper.getDecoratedMeasurement(child) / 2)
            val absDistance = Math.abs(childCenter - center)
            /** if child center is closer than previous closest, set it as closest   */
            if (absDistance < absClosest) {
                absClosest = absDistance
                closestChild = child
            }
        }
        return closestChild
    }

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager, velocityX: Int,
        velocityY: Int
    ): Int {
        val itemCount = layoutManager.itemCount
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION
        }
        var mStartMostChildView: View? = null
        if (layoutManager.canScrollVertically()) {
            mStartMostChildView = findStartView(layoutManager, getVerticalHelper(layoutManager))
        } else if (layoutManager.canScrollHorizontally()) {
            mStartMostChildView = findStartView(layoutManager, getHorizontalHelper(layoutManager))
        }
        if (mStartMostChildView == null) {
            return RecyclerView.NO_POSITION
        }
        val centerPosition = layoutManager.getPosition(mStartMostChildView)
        if (centerPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION
        }
        val forwardDirection: Boolean = if (layoutManager.canScrollHorizontally()) {
            velocityX > 0
        } else {
            velocityY > 0
        }
        var reverseLayout = false
        if (layoutManager is ScrollVectorProvider) {
            val vectorProvider = layoutManager as ScrollVectorProvider
            val vectorForEnd = vectorProvider.computeScrollVectorForPosition(itemCount - 1)
            if (vectorForEnd != null) {
                reverseLayout = vectorForEnd.x < 0 || vectorForEnd.y < 0
            }
        }
        val pageIndex = pageIndex(centerPosition)
        val currentPageStart = pageIndex * countOfpage()
        return if (reverseLayout) if (forwardDirection) currentPageStart - countOfpage() else currentPageStart else if (forwardDirection) currentPageStart + countOfpage() else currentPageStart + countOfpage() - 1
    }

    @Nullable
    private fun findStartView(
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return null
        }
        var closestChild: View? = null
        var startest = Int.MAX_VALUE
        for (i in 0 until childCount) {
            val child: View? = layoutManager.getChildAt(i)
            val childStart = helper.getDecoratedStart(child)
            /** if child is more to start than previous closest, set it as closest   */
            if (childStart < startest) {
                startest = childStart
                closestChild = child
            }
        }
        return closestChild
    }

    override fun createSnapScroller(layoutManager: RecyclerView.LayoutManager?): LinearSmoothScroller? {
        return if (layoutManager !is ScrollVectorProvider) {
            null
        } else object : LinearSmoothScroller(initRecyclerView().context) {
//        } else object : LinearSmoothScroller(mRecyclerView.context) {

            override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
                val snapDistances: IntArray = calculateDistanceToFinalSnap(
                    initRecyclerView().layoutManager!!,
//                    mRecyclerView.layoutManager!!,
                    targetView
                )
                val dx = snapDistances[0]
                val dy = snapDistances[1]
                val time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)))
                if (time > 0) {
                    action.update(dx, dy, time, mDecelerateInterpolator)
                }
            }

            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return 25f / displayMetrics.densityDpi
//                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
            }

            override fun calculateTimeForScrolling(dx: Int): Int {
                return Math.min(MAX_SCROLL_ON_FLING_DURATION, super.calculateTimeForScrolling(dx))
            }

            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return null
            }
        }
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {

//        val privateStringField = OrientationHelper::class.java.getDeclaredField("mLayoutManager")
//        privateStringField.isAccessible = true
//        val mLayoutManager = privateStringField.get(this) as RecyclerView.LayoutManager

        if (mVerticalHelper == null || mLayoutManager != layoutManager) {
//        if (mVerticalHelper == null || mVerticalHelper!!.mLayoutManager != layoutManager) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return mVerticalHelper!!
    }

    private fun getHorizontalHelper(
        layoutManager: RecyclerView.LayoutManager
    ): OrientationHelper {

//        val privateStringField = OrientationHelper::class.java.getDeclaredField("mLayoutManager")
//        privateStringField.isAccessible = true
////        val mLayoutManager = privateStringField.type as RecyclerView.LayoutManager
//        val mLayoutManager = privateStringField.get(object :OrientationHelper(){}) as RecyclerView.LayoutManager
//
////        val field = Class.forName("androidx.recyclerview.widget.OrientationHelper").getDeclaredField("mLayoutManager")
////        field.isAccessible = true

        if (mHorizontalHelper == null || mLayoutManager != layoutManager) {
//        if (mHorizontalHelper == null || mHorizontalHelper!!.mLayoutManager != layoutManager) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return mHorizontalHelper!!
    }

    companion object {
        private const val DEFAULT_ROW = 1
        private const val DEFAULT_COLUMN = 1
        private const val MAX_SCROLL_ON_FLING_DURATION = 100 // ms
    }

    fun initRecyclerView(): RecyclerView {
        if (null == mRecyclerView) {
            val privateStringField = SnapHelper::class.java.getDeclaredField("mRecyclerView")
            privateStringField.isAccessible = true
            mRecyclerView = privateStringField.get(this) as RecyclerView
        }
        return mRecyclerView!!
    }
}