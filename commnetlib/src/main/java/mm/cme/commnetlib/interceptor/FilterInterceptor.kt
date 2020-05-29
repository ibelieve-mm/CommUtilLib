package mm.cme.commnetlib.interceptor

import android.text.TextUtils
import androidx.annotation.NonNull
import mm.cme.commnetlib.config.KEY
import mm.cme.commnetlib.config.KEY_WEATHER
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */

class FilterInterceptor : Interceptor {
    @NonNull
    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpBuilder = originalRequest.url.newBuilder()
        val headers = originalRequest.headers
        if (headers.size > 0) {
            val platform = headers["platform"]
            if (!platform.isNullOrEmpty()) {
                if(platform=="android"){
                    httpBuilder.addQueryParameter(KEY,KEY_WEATHER)
                }
            }
        }
        val requestBuilder = originalRequest.newBuilder()
//            .removeHeader(HttpConfig.HTTP_REQUEST_TYPE_KEY)
            .url(httpBuilder.build())
        return chain.proceed(requestBuilder.build())
    }
}