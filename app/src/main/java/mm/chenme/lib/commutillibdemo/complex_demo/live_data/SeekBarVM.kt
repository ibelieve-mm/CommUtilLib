package mm.chenme.lib.commutillibdemo.complex_demo.live_data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/10
 * Email：ibelieve1210@163.com
 */
class SeekBarVM(app:Application) : AndroidViewModel(app) {

    val seekBarValue: MutableLiveData<Int> = MutableLiveData()
}