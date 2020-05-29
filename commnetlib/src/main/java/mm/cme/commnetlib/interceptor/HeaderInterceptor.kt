package mm.cme.commnetlib.interceptor

import mm.chenme.lib.commutillib.utils.appVersionName
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */

class HeaderInterceptor(private val headers: Map<String, String>?) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (headers != null && headers.isNotEmpty()) {
            headers.forEach {
                builder.addHeader(it.key, it.value).build()
            }
        }
        builder.addHeader("platform", "android")
            .addHeader("version", appVersionName)
        //请求信息
        return chain.proceed(builder.build())
    }

}