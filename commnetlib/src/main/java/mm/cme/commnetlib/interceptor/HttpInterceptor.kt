package mm.cme.commnetlib.interceptor

import androidx.annotation.NonNull
import mm.cme.commnetlib.exception.ConnectionException
import mm.cme.commnetlib.exception.ResultInvalidException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.BufferedSource

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */

class HttpInterceptor : Interceptor {
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val originalResponse: Response
        originalResponse = try {
            chain.proceed(request)
        } catch (e: Exception) {
            throw ConnectionException()
        }
        if (originalResponse.code != 200) {
            throw ResultInvalidException()
        }
        assert(originalResponse.body != null)
        val source: BufferedSource = originalResponse.body!!.source()
        source.request(Int.MAX_VALUE.toLong())
        val byteString = source.buffer().snapshot().utf8()
        val responseBody = ResponseBody.create(null, byteString)
        return originalResponse.newBuilder().body(responseBody).build()
    }


}
