package mm.chenme.lib.commutillibdemo.ui.other

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.model.*
import mm.chenme.lib.commutillibdemo.utils.parseJson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlinx.android.synthetic.main.act_area_select.*
import kotlinx.android.synthetic.main.act_area_select.topbar
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
import kotlinx.android.synthetic.main.menu_text_view.*

/**
 * Descriptions：城市选择页面
 * <p>
 * Author：ChenME
 * Date：2020/5/1
 * Email：ibelieve1210@163.com
 */
class AreaSelectActivity : BaseActivity() {

    private val mCityList = mutableListOf<CityBean>()
    private val mAreaList = mutableListOf<AreaBean>()
    private val mStreetList = mutableListOf<StreetBean>()

    private lateinit var mCityAdapter: BaseRecyclerViewAdapter<CityBean>
    private lateinit var mAreaAdapter: BaseRecyclerViewAdapter<AreaBean>
    private lateinit var mStreetAdapter: BaseRecyclerViewAdapter<StreetBean>

    private var mLastClickCity = 0
    private var mLastClickArea = 0

    private var mResult = mutableMapOf<Long, ResultBean>() // 用于保存结果

    override val layoutResId: Int = R.layout.act_area_select

    override fun initData() {
        val jsonRes = readJsonFromAssets("location.json")
        val bean = parseJson(jsonRes, DataBean::class.java)
        mCityList.addAll(bean.data)
        mCityList[0].isSelected = true
        mAreaList.addAll(bean.data[0].value)
        mAreaList[0].isSelected = true
        mStreetList.addAll(bean.data[0].value[0].value)
    }

    override fun initView() {
        initTopBar(topbar, "城市选择")
        topbar.addRightTextButton("打印结果", R.id.tv_menuStyle)

        recyclerView1.layoutManager = LinearLayoutManager(this)
        mCityAdapter = BaseRecyclerViewAdapter(this, mCityList, R.layout.list_city_item) { rootView, dataItem, pos ->
            val tv = (rootView as TextView)
            var text = dataItem.name
            if (dataItem.selectCount > 0) {
                text += "（${dataItem.selectCount}）"
            }
            tv.text = text
            tv.setTextColor(resources.getColor(if (dataItem.isSelected) R.color.color_red_ff639b else R.color.color_theme))

            rootView.onClick {
                if (mLastClickCity == pos) {
                    return@onClick
                }

                // 清除当前上一位置的选中效果，并选中当前点击的位置
                mCityList[mLastClickCity].isSelected = false
                mCityAdapter.notifyItemChanged(mLastClickCity)
                dataItem.isSelected = true
                mCityAdapter.notifyItemChanged(pos)

                // 清除第二列上一位置的选中效果
                mAreaList[mLastClickArea].isSelected = false
                mLastClickArea = 0

                mAreaList.clear()
                mAreaList.addAll(dataItem.value)
                mAreaList[0].isSelected = true
                mAreaAdapter.notifyDataSetChanged()

                updateStreetView(dataItem.value[0])

                mLastClickCity = pos
            }
        }
        recyclerView1.adapter = mCityAdapter

        recyclerView2.layoutManager = LinearLayoutManager(this)
        mAreaAdapter = BaseRecyclerViewAdapter(this, mAreaList, R.layout.list_city_item) { rootView, dataItem, pos ->
            val tv = (rootView as TextView)
            var text = dataItem.name
            if (dataItem.selectCount > 0) {
                text += "（${dataItem.selectCount}）"
            }
            tv.text = text
            tv.setTextColor(resources.getColor(if (dataItem.isSelected) R.color.color_red_ff639b else R.color.color_theme))

            rootView.onClick {
                if (mLastClickArea == pos) {
                    return@onClick
                }

                // 清除当前上一位置的选中效果，并选中当前点击的位置
                mAreaList[mLastClickArea].isSelected = false
                mAreaAdapter.notifyItemChanged(mLastClickArea)
                dataItem.isSelected = true
                mAreaAdapter.notifyItemChanged(pos)

                updateStreetView(dataItem)

                mLastClickArea = pos
            }
        }
        recyclerView2.adapter = mAreaAdapter

        recyclerView3.layoutManager = LinearLayoutManager(this)
        mStreetAdapter = BaseRecyclerViewAdapter(this, mStreetList, R.layout.list_city_item) { rootView, dataItem, pos ->
            val tv = (rootView as TextView)
            tv.text = dataItem.name
            tv.setTextColor(resources.getColor(if (dataItem.isSelected) R.color.color_red_ff639b else R.color.color_theme))

            rootView.onClick {

                if (dataItem.isSelected) { // 已选中，点击后未选中
                    mCityList[mLastClickCity].selectCount--
                    mAreaList[mLastClickArea].selectCount--
                    mResult.remove(dataItem.value)
                } else {
                    mCityList[mLastClickCity].selectCount++
                    mAreaList[mLastClickArea].selectCount++
                    mResult[dataItem.value] = ResultBean(mCityList[mLastClickCity].name, mAreaList[mLastClickArea].name, dataItem.name, mLastClickCity, mLastClickArea, dataItem.value)
                }
                mAreaAdapter.notifyItemChanged(mLastClickArea)
                mCityAdapter.notifyItemChanged(mLastClickCity)

                dataItem.isSelected = !dataItem.isSelected
                mStreetAdapter.notifyItemChanged(pos)


            }
        }
        recyclerView3.adapter = mStreetAdapter
    }


    override fun initListener() {

        /**
         * 打印结果
         */
        tv_menuStyle.onClick {

            loge("\n原始数据---------->")
            mResult.forEach {
                loge(it.value.toString())
            }

            val tmpMap = mutableMapOf<String, MutableList<ResultBean>>()
            mResult.forEach {
                var tmpList = tmpMap[it.value.city]
                if (null == tmpList) {
                    tmpList = mutableListOf()
                    tmpMap[it.value.city] = tmpList
                }
                tmpList.add(it.value)
            }

            loge("\n第一次整理结果---------->")
            tmpMap.forEach {
                loge(it.value.toString())
            }

            val resultList = mutableListOf<ResultParentBean>()
            tmpMap.forEach {
                resultList.add(ResultParentBean(it.key, it.value))
            }
            loge("\n第二次整理结果---------->")
            loge(resultList.toString())
        }
    }

    data class ResultParentBean(
        var name: String = "",
        var list: MutableList<ResultBean>
    )

    /**
     * 更新街道列表（第三列）
     */
    private fun updateStreetView(dataItem: AreaBean) {
        mStreetList.clear()
        mStreetList.addAll(dataItem.value)
        mStreetAdapter.notifyDataSetChanged()
    }

    private fun readJsonFromAssets(fileName: String): String {
        val stringBuilder = StringBuilder()
        try {
            val bufferedReader = BufferedReader(InputStreamReader(assets.open(fileName)))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}