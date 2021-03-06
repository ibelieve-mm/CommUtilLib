package mm.chenme.lib.commutillibdemo.complex_demo.netdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillib.utils.st
import mm.cme.commnetlib.config.Net_OK

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */

class DemoViewModel(application: Application) : AndroidViewModel(application) {

    private val mHomeRepository = DemoRepository()
    private lateinit var aActivity: DemoActivity

    /*
     * 如果需要使用 activity 实例，通过该方法传递
     */
    fun bindActivity(activity: DemoActivity) {
        aActivity = activity
    }

    fun queryWeather(): LiveData<Weather?> {
        val mlvWeather = MediatorLiveData<Weather?>()
        return Transformations.switchMap(mHomeRepository.queryWeather()) {
            if (Net_OK == it.resultCode) {
                mlvWeather.value = it.data
            } else {
                mlvWeather.value = null
                st(it.resultDesc)
                aActivity.dataEmpty()
            }
            mlvWeather
        }
    }

    fun resetLotteryTime(): LiveData<Boolean> {
        val mlvWeather = MediatorLiveData<Boolean>()
        return Transformations.switchMap(mHomeRepository.resetLotteryTime()) {
            if (Net_OK == it.resultCode) {
                mlvWeather.value = true
            } else {
                mlvWeather.value = null
                st(it.resultDesc)
                aActivity.dataEmpty()
            }
            mlvWeather
        }
    }
    fun resetLotteryCount(): LiveData<Boolean> {
        val mlvWeather = MediatorLiveData<Boolean>()
        return Transformations.switchMap(mHomeRepository.resetLotteryCount()) {
            if (Net_OK == it.resultCode) {
                mlvWeather.value = true
            } else {
                mlvWeather.value = null
                st(it.resultDesc)
                aActivity.dataEmpty()
            }
            mlvWeather
        }
    }


}



