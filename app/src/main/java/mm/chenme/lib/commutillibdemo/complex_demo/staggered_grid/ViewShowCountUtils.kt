package mm.chenme.lib.commutillibdemo.complex_demo.staggered_grid

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillib.utils.screenHeightPx
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs

/**
 * Descriptions：计算 RecyclerView 中 Item 被曝光的次数（滑动过程中的不做计算）
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/10/23
 * Email：ibelieve1210@163.com
 */

class ViewShowCountUtils {

    private var mIsFirstVisible = true // 刚进入列表时统计当前屏幕可见 views
    private val mHashMap: ConcurrentHashMap<ItemData, Int> = ConcurrentHashMap() // 用于统计曝光量的 map

    /**
     * 统计RecyclerView里当前屏幕可见子view的曝光量
     */
    fun recordViewShowCount(recyclerView: RecyclerView?) {
        mHashMap.clear()
        if (recyclerView == null || recyclerView.visibility != View.VISIBLE) {
            return
        }
        //检测 RecyclerView 的滚动事件
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                /*
                这里通过的是停止滚动后屏幕上可见 view。如果滚动过程中的可见 view 也要统计，可以根据 newState 去做区分
                SCROLL_STATE_IDLE:停止滚动
                SCROLL_STATE_DRAGGING: 用户慢慢拖动
                SCROLL_STATE_SETTLING：惯性滚动
                */
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getVisibleViews(recyclerView)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 刚进入列表时统计当前屏幕可见 views
                if (mIsFirstVisible) {
                    getVisibleViews(recyclerView)
                    mIsFirstVisible = false
                }
            }
        })
    }

    /**
     * 获取曝光过的数据
     */
    fun getViewShowData(): ConcurrentHashMap<ItemData, Int> {
        return mHashMap
    }

    /**
     * 获取当前屏幕上可见的view
     */
    private fun getVisibleViews(reView: RecyclerView?) {
        if (reView == null || reView.visibility != View.VISIBLE ||
            !reView.isShown || !reView.getGlobalVisibleRect(Rect())
        ) {
            return
        }
        // 保险起见，为了不让统计影响正常业务，这里做下 try-catch
        try {
            var range = IntArray(2)
            val manager = reView.layoutManager
            when (manager) {
                is LinearLayoutManager -> {
                    range = findRangeLinear(manager)
                }
                is GridLayoutManager -> {
                    range = findRangeGrid(manager)
                }
                is StaggeredGridLayoutManager -> {
                    range = findRangeStaggeredGrid(manager)
                }
            }
            if (range.size < 2) {
                return
            }
            loge("屏幕内可见条目的起始位置：${range[0]} --- ${range[1]}","---CME---ViewShowCountUtils.getVisibleViews()")
            for (i in range[0]..range[1]) {
                val view: View? = manager!!.findViewByPosition(i)
                recordViewCount(view)
            }
            mHashMap.forEach {
                loge("key:${it.key.toString()}, value:${it.value}", "---CME---ViewShowCountUtils.getVisibleViews()")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 获取view绑定的数据
     */
    private fun recordViewCount(view: View?) {
        if (view == null || view.visibility != View.VISIBLE ||
            !view.isShown || !view.getGlobalVisibleRect(Rect())
        ) {
            return
        }
        val top: Int = view.top
        val halfHeight: Int = view.height / 2
        val screenHeight: Int = screenHeightPx
//        val statusBarHeight: Int = UiUtils.getStatusBarHeight(view.getContext())
        if (top < 0 && abs(top) > halfHeight) {
            return
        }
//        if (top > screenHeight - halfHeight - statusBarHeight) {
        if (top > screenHeight - halfHeight) {
            return
        }

        //这里获取的是我们view绑定的数据，相应的你要去在你的view里setTag，只有set了，才能get
        val tag = view.tag
        if (tag is ItemData) {
            mHashMap[tag] = if (!mHashMap.containsKey(tag)) 1 else (mHashMap[tag]!! + 1)
        }
    }

    private fun findRangeLinear(manager: LinearLayoutManager): IntArray {
        val range = IntArray(2)
        range[0] = manager.findFirstVisibleItemPosition()
        range[1] = manager.findLastVisibleItemPosition()
        return range
    }

    private fun findRangeGrid(manager: GridLayoutManager): IntArray {
        val range = IntArray(2)
        range[0] = manager.findFirstVisibleItemPosition()
        range[1] = manager.findLastVisibleItemPosition()
        return range
    }

    private fun findRangeStaggeredGrid(manager: StaggeredGridLayoutManager): IntArray {
        val startPos = IntArray(manager.spanCount)
        val endPos = IntArray(manager.spanCount)
        manager.findFirstVisibleItemPositions(startPos)
        manager.findLastVisibleItemPositions(endPos)
        return findRange(startPos, endPos)
    }

    private fun findRange(startPos: IntArray, endPos: IntArray): IntArray {
        var start = startPos[0]
        var end = endPos[0]
        for (i in 1 until startPos.size) {
            if (start > startPos[i]) {
                start = startPos[i]
            }
        }
        for (i in 1 until endPos.size) {
            if (end < endPos[i]) {
                end = endPos[i]
            }
        }
        return intArrayOf(start, end)
    }
}