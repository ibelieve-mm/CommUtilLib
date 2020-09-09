package mm.chenme.lib.commutillibdemo.complex_demo.netdemo

import mm.chenme.lib.commutillibdemo.consts.Values.Token
import mm.cme.commnetlib.resp.BaseResponse
import retrofit2.Call
import retrofit2.http.*


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

    @FormUrlEncoded
    @POST("api/turntableLottery/resetCountdownExpire")
    fun resetLotteryTime(@Field("token") token: String = Token): Call<BaseResponse<Any?>>

    @FormUrlEncoded
    @POST("api/turntableLottery/resetFreeNum")
    fun resetLotteryCount(@Field("token") token: String = Token): Call<BaseResponse<Any?>>

}