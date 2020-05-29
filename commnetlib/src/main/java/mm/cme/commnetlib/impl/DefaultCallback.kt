package mm.cme.commnetlib.impl

import androidx.lifecycle.MutableLiveData
import mm.cme.commnetlib.config.Net_Err
import mm.cme.commnetlib.resp.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */
class DefaultCallback<T>(private val respLiveData: MutableLiveData<BaseResponse<T>>) : Callback<BaseResponse<T>> {
    override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        respLiveData.value = BaseResponse(Net_Err, "网络错误")
    }

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        if (response.isSuccessful) {
            val respBody = response.body()
            if (null != respBody) {
                respLiveData.value = respBody
            } else {
                respLiveData.value = BaseResponse(Net_Err, "网络错误")
            }
        } else {
            respLiveData.value = BaseResponse(Net_Err, "网络错误")
        }
    }
}
