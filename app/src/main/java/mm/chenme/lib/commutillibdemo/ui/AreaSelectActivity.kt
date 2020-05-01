package mm.chenme.lib.commutillibdemo.ui

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_area_select.*
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.base.BaseFragmentActivity
import mm.chenme.lib.commutillibdemo.model.*
import mm.chenme.lib.commutillibdemo.utils.parseJson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2020/5/1
 * Email：ibelieve1210@163.com
 */
class AreaSelectActivity : BaseFragmentActivity() {


    override fun loadContentView(): Int = R.layout.activity_area_select

    private val mCityList = mutableListOf<CityBean>()
    private val mAreaList = mutableListOf<AreaBean>()
    private val mStreetList = mutableListOf<StreetBean>()

    private lateinit var mCityAdapter: BaseRecyclerViewAdapter<CityBean>
    private lateinit var mAreaAdapter: BaseRecyclerViewAdapter<AreaBean>
    private lateinit var mStreetAdapter: BaseRecyclerViewAdapter<StreetBean>

    private var mLastClickCity = 0
    private var mLastClickArea = 0

    private var mResult = mutableMapOf<Long, ResultBean>() // 用于保存结果

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
                    mResult[dataItem.value] = ResultBean(mCityList[mLastClickCity].name, mAreaList[mLastClickArea].name, dataItem.name, dataItem.value)
                }
                mAreaAdapter.notifyItemChanged(mLastClickArea)
                mCityAdapter.notifyItemChanged(mLastClickCity)

                dataItem.isSelected = !dataItem.isSelected
                mStreetAdapter.notifyItemChanged(pos)


                /**
                 * 打印结果
                 */
                mResult.forEach {
                    loge(it.value.toString())
                }
            }
        }
        recyclerView3.adapter = mStreetAdapter
    }

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