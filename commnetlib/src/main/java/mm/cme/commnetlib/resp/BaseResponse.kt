package mm.cme.commnetlib.resp

import com.google.gson.annotations.SerializedName

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */
data class BaseResponse<T>(
    @SerializedName("result") var resultCode: Int = 0,
    @SerializedName("message") var resultDesc: String = "",
    @SerializedName("data") var data: T? = null

//    @SerializedName("code") var resultCode: Int = 0,
//    @SerializedName("msg") var resultDesc: String = "",
//    @SerializedName("data") var data: T? = null
)
