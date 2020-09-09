package mm.chenme.lib.commutillibdemo.ui

import android.graphics.Color
import androidx.core.content.edit
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_main.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.getSP
import mm.chenme.lib.commutillib.utils.stoast
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.complex_demo.netdemo.DemoActivity
import mm.chenme.lib.commutillibdemo.complex_demo.rv_paging_scroll.GridPagerSnapHelperActivity
import mm.chenme.lib.commutillibdemo.consts.Values
import mm.chenme.lib.commutillibdemo.complex_demo.bezier.BezierHeartViewTestActivity
import mm.chenme.lib.commutillibdemo.complex_demo.interview.base.pass_value4service.ServiceActivity
import mm.chenme.lib.commutillibdemo.complex_demo.lottery.LotteryViewActivity
import mm.chenme.lib.commutillibdemo.complex_demo.lottery_pro.LotteryProActivity
import mm.chenme.lib.commutillibdemo.complex_demo.lotteryx.LotteryXViewActivity
import mm.chenme.lib.commutillibdemo.complex_demo.surface_view.SurfaceViewActivity
import mm.chenme.lib.commutillibdemo.ui.main.FragmentMainActivity
import mm.chenme.lib.commutillibdemo.ui.other.*
import mm.chenme.lib.commutillibdemo.ui.qmui.QMUIBottomSheetActivity
import mm.chenme.lib.commutillibdemo.ui.qmui.QMUIWidgetTestActivity
import mm.chenme.lib.commutillibdemo.ui.rare.ClipChildrenTestActivity
import mm.chenme.lib.commutillibdemo.ui.rare.ClipToPaddingTestActivity
import org.jetbrains.anko.startActivity

class MainActivity(
    override val layoutResId: Int = R.layout.act_main,
    override val isStatusBarLightMode: Boolean = false
) : BaseActivity() {

    private var mPressTime: Long = 0 // 上一次点击返回键的时间


    override fun initView() {
        initTopBar(topbar, "主页面", Color.WHITE, isShowBackBtn = false)

        getSP().edit { putString("asdadsa", "小盆友，你是不是有很多问？") }
    }

    override fun initListener() {
//            startActivity<FlutterDemoActivity>()
        srb_passValueService.onClick { startActivity<ServiceActivity>() }


        srb_lottery1.onClick { startActivity<LotteryViewActivity>() }
        srb_lottery2.onClick { startActivity<LotteryXViewActivity>() }
        srb_lottery3.onClick { startActivity<LotteryProActivity>() }


        srb_divider4TabLayout.onClick { startActivity<Divider4TabLayoutActivity>() }
        srb_pagingSnap.onClick { startActivity<GridPagerSnapHelperActivity>() }
        srb_netDemo.onClick { startActivity<DemoActivity>() }


        qmBtn_qmuiTest.onClick { startActivity<QMUIWidgetTestActivity>() }
        srb_qmuiBottomSheet.onClick { startActivity<QMUIBottomSheetActivity>() }


        qmBtn_surfaceView.onClick { startActivity<SurfaceViewActivity>() }
        qmBtn_viewStub.onClick { startActivity<ViewStubActivity>() }
        qmBtn_clipToPadding.onClick { startActivity<ClipToPaddingTestActivity>() }
        qmBtn_clipChildren.onClick { startActivity<ClipChildrenTestActivity>() }


        qmBtn_citySelect.onClick { startActivity<AreaSelectActivity>() }
        qmBtn_selfClipLayout.onClick { startActivity<SelfClipLayoutTestActivity>() }
        srb_shapeRippleButton.onClick { startActivity<ShapeRippleButtonActivity>() }
        srb_errorEmpty.onClick { startActivity<ErrorEmptyViewActivity>() }
        srb_glide.onClick { startActivity<GlideScaleTypeActivity>() }
        srb_sharedPreferences.onClick { startActivity<SharedPreferencesActivity>() }
        srb_coroutine.onClick { startActivity<CoroutineActivity>() }
        srb_bezier.onClick { startActivity<BezierHeartViewTestActivity>() }
        srb_fragmentMain.onClick { startActivity<FragmentMainActivity>() }
    }

    /**
     * 双击退出
     */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - mPressTime > Values.Time_DoubleClickInterval) {
            stoast("再按一次退出程序")
            mPressTime = time
        } else {
            finish()
//            APPManager.instance.exitApp(this)
        }
    }
}
