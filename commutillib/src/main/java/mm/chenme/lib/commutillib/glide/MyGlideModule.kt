package mm.chenme.lib.commutillib.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import mm.chenme.lib.commutillib.constant.RAMConstants.MB256
import mm.chenme.lib.commutillib.utils.logi
import java.io.File
import java.io.InputStream

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/4
 * Email：ibelieve1210@163.com
 */

@GlideModule
class MyGlideModule : AppGlideModule() {

    companion object {
        fun clearMemoryCache(context: Context) {
            GlideApp.get(context).clearMemory()
        }
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val cachedDirName = "glide"
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, cachedDirName, MB256))
        logi("NIMGlideModule apply options, disk cached path=" + context.externalCacheDir + File.separator + cachedDirName, MyGlideModule::class.java.simpleName)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }
}