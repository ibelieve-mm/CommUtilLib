package mm.chenme.lib.commutillibdemo.complex_demo.netdemo

import mm.cme.commnetlib.resp.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */

open interface DemoApi {

    @GET("weatherindex")
    fun queryWeather(@Query("city") cityName: String = "CH010100"): Call<BaseResponse<Weather?>>
}