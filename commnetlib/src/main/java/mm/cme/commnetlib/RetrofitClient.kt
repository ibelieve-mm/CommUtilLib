package mm.cme.commnetlib

import com.readystatesoftware.chuck.ChuckInterceptor
import mm.chenme.lib.commutillib.utils.AppGlobals
import mm.cme.commnetlib.config.BaseUrl
import mm.cme.commnetlib.interceptor.FilterInterceptor
import mm.cme.commnetlib.interceptor.HeaderInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */
object RetrofitClient {
    private const val TimeOut_Read: Long = 6000
    private const val TimeOut_Write: Long = 6000
    private const val TimeOut_Connect: Long = 6000

    private val sslParams = HttpsUtils.getSslSocketFactory() // https证书认证,封装了认证方法,可根据自己公司进行调整;
     fun build(url: String? = null, headers: Map<String, String>? = null): Retrofit {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(TimeOut_Read, TimeUnit.SECONDS)
            .writeTimeout(TimeOut_Write, TimeUnit.SECONDS)
            .connectTimeout(TimeOut_Connect, TimeUnit.SECONDS)
//            .addInterceptor(HttpInterceptor())
            .addInterceptor(HeaderInterceptor(headers))
            .addInterceptor(FilterInterceptor())
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
            .retryOnConnectionFailure(true)
            .sslSocketFactory(sslParams.sSLSocketFactory!!, sslParams.trustManager!!) // https的证书校验
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(ChuckInterceptor(AppGlobals.application()))
        }
        val client = builder.build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(url ?: BaseUrl)
//            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
            .addConverterFactory(MoshiConverterFactory.create()) // 使用 Moshi 解析
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
//    private lateinit var retrofit: Retrofit
//    fun <T> getService(clazz: Class<T>): T = retrofit.create(clazz)
//    fun build(baseUrl: String) {
//        val sslSocketFactory = getSSLContext()
//        if (sslSocketFactory != null) {
//            okHttpBuilder.sslSocketFactory(sslSocketFactory)
//        }
//        retrofit = retrofitBuilder
//            .client(okHttpBuilder.build())
////                .client(RetrofitUrlManager.getInstance().with(okHttpBuilder).build()) // 动态修改baseUrl
//            .baseUrl(baseUrl)
//            .build()
//    }

}