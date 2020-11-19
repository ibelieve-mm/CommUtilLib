package mm.chenme.lib.commutillibdemo.complex_demo.staggered_grid

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_staggered_grid.*
import kotlinx.android.synthetic.main.menu_text_view.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillib.utils.st
import mm.chenme.lib.commutillibdemo.R
import org.jetbrains.anko.dip
import org.jetbrains.anko.find
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.max
import kotlin.math.min

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/10/22
 * Email：ibelieve1210@163.com
 */
class StaggeredGridActivity(override val layoutResId: Int = R.layout.act_staggered_grid) : BaseActivity() {
    //    private val mViewShowCountUtils = ViewShowCountUtils()
    private val mList = mutableListOf<ItemData>()
    private lateinit var mAdapter: BaseRecyclerViewAdapter<ItemData>
    override fun initView() {
        super.initView()

        initTopBar(topbar, "瀑布流")
        topbar.addRightTextButton("剔除看过的item", R.id.tv_menuStyle)

        initRecyclerView()

        tv_menuStyle.onClick {
            mList.forEach { loge(it.toString(), "---CME---StaggeredGridActivity.initView()") }

//            mViewShowCountUtils.getViewShowData().forEach{
//                mList.remove(it.key)
//            }
            mAdapter.notifyDataSetChanged()
        }

        btn_res.onClick {

//            mMap.forEach { // it.key 可能存在 -1 ，注意排除
//                if (it.value.startTime > 0L) {
//                    it.value.durationTime += System.currentTimeMillis() - it.value.startTime
//                }
//            }
//            mMap.forEach {
//                loge("key : ${it.key} , value : ${it.value.toString()}", "---CME---StaggeredGridActivity.onScrollStateChanged()")
//            }
            recyclerView.post {

//            recyclerView.scrollTo(0,3308)
            }
//            mLayoutManager.scrollToPositionWithOffset(12,0)
//            recyclerView.scrollY=3308
            recyclerView.scrollBy(0,3308)
            st("123")
        }
//        cardView.onClick { play() }

//        asiv.play(R.mipmap.banner_new_friend_img, R.mipmap.banner_new_friend_img, AutoScrollImageView.ORIENTATION_TOP)
    }

//    private fun play(){
//        val interpolator = LinearInterpolator()
//        val anim11: Animator = ObjectAnimator.ofFloat(iv_girls, "translationY", 0f, dip(-185).toFloat())
//        anim11.duration = 4500L
//        anim11.interpolator = interpolator
//        anim11.start()
//    }

private var mTop = 0;
    private var mIsRecordFirst = false
    private val mMap = ConcurrentHashMap<Int, Record>()
    private val mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    private fun initRecyclerView() {

        (0..30).forEach {
            mList.add(ItemData(id = it))
        }

        recyclerView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                if (!mIsRecordFirst) {
                    val canWatchFirst = IntArray(2)
                    mLayoutManager.findFirstVisibleItemPositions(canWatchFirst)
                    val canWatchEnd = IntArray(2)
                    mLayoutManager.findLastVisibleItemPositions(canWatchEnd)
                    mIsRecordFirst = true
                    (min(canWatchFirst[0], canWatchFirst[1])..max(canWatchEnd[0], canWatchEnd[1])).forEach {
                        if (it >= 0) {
                            mMap[it] = Record(System.currentTimeMillis())
                        }
                    }
                }
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val canWatchFirst = IntArray(2)
                mLayoutManager.findFirstVisibleItemPositions(canWatchFirst)
                val canWatchEnd = IntArray(2)
                mLayoutManager.findLastVisibleItemPositions(canWatchEnd)

                val iterable = (min(canWatchFirst[0], canWatchFirst[1])..max(canWatchEnd[0], canWatchEnd[1]))
                iterable.forEach {
                    val value = mMap[it]
                    if (value is Record) {
                        if (value.startTime == 0L) {
                            value.startTime = System.currentTimeMillis()
                        }
                    } else {
                        if (it >= 0) {
                            mMap[it] = Record(System.currentTimeMillis())
                        }
                    }
                }

                mMap.forEach {
                    if (!iterable.contains(it.key) && it.value.startTime != 0L) {
                        it.value.durationTime += System.currentTimeMillis() - it.value.startTime
                        it.value.startTime = 0L
                    }
                }
//                if(RecyclerView.SCROLL_STATE_IDLE==newState){
//                    loge("aaaaaaaaaaaaa 1 -- ${recyclerView.top}","---CME---StaggeredGridActivity.onScrollStateChanged()")
//                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                mTop+=dy
                loge("aaaaaaaaaaaaa  -- ${mTop}","---CME---StaggeredGridActivity.onScrollStateChanged()")
                loge("aaaaaaaaaaaaa 1 -- ${dy}","---CME---StaggeredGridActivity.onScrollStateChanged()")
            }
        })


        recyclerView.layoutManager = mLayoutManager
        mAdapter = BaseRecyclerViewAdapter(this, mList, R.layout.li_staggered_grid) { root, data, pos ->
            val lp = root.layoutParams
            if (0 == pos) {
                lp.height = dip(150)
            } else {
                lp.height = dip(190)
            }
            root.layoutParams = lp
            root.tag = data

            root.find<TextView>(R.id.tv_content).text = data.id.toString()

        }
        recyclerView.adapter = mAdapter
//        mViewShowCountUtils.recordViewShowCount(recyclerView)
    }

    data class Record(
        var startTime: Long = 0L,
        var durationTime: Long = 0L
    )
}