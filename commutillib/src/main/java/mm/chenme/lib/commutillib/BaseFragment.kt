package mm.chenme.lib.commutillib

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.qmuiteam.qmui.arch.QMUIFragment
import mm.chenme.lib.commutillib.utils.inflate

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/13
 * Email：ibelieve1210@163.com
 */
abstract class BaseFragment : QMUIFragment() {

    protected lateinit var mActivity: FragmentActivity

    abstract val layoutResId: Int // 页面布局资源ID
    private lateinit var mContentView: View

    open fun initData() {} // 初始化数据
    open fun initView() {} // 初始化View
    open fun initListener() {} // 初始化Listener
    open fun loadData() {} // 请求数据

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = requireActivity()
        initData()
    }

    override fun onCreateView(): View {
        mContentView = requireActivity().inflate(layoutResId)
        return mContentView
    }

    override fun onViewCreated(rootView: View) {
        super.onViewCreated(rootView)
        initView()
        initListener()
        loadData()
    }
}