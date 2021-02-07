package mm.cme.commnetlib.resp

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */

//// 使用 Gson 解析数据
//data class BaseResponse<T>(
//    @SerializedName("result") var resultCode: Int = 0,
//    @SerializedName("message") var resultDesc: String = "",
//    @SerializedName("data") var data: T? = null
//
////    @SerializedName("code") var resultCode: Int = 0,
////    @SerializedName("msg") var resultDesc: String = "",
////    @SerializedName("data") var data: T? = null
//)


// 使用 Gson 解析数据
data class BaseResponse<T>(
    @field:Json(name = "result") var resultCode: Int = 0,
    @field:Json(name = "message") var resultDesc: String = "",
    @field:Json(name = "data") var data: T? = null,

//    @SerializedName("code") var resultCode: Int = 0,
//    @SerializedName("msg") var resultDesc: String = "",
//    @SerializedName("data") var data: T? = null
)
