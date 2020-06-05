package mm.chenme.lib.commutillib.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.*
import com.qmuiteam.qmui.kotlin.dip
import mm.chenme.lib.commutillib.R
import mm.chenme.lib.commutillib.glide.GlideApp
import mm.chenme.lib.commutillib.glide.GlideRequest

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/4
 * Email：ibelieve1210@163.com
 */

/*
 * 想实现效果(图片窗宽比不变形)：
 * 1. 填充满整个控件，长的一边被裁剪掉：
 *     ①isUserCenterCrop：true
 *     ②ImageView 自身的 scaleType ：任意（可不填）
 *
 * 2. 控件不被裁剪，短的一边留白：
 *     ①isUserCenterCrop：false
 *     ②ImageView 的 scaleType：
 *         fitCenter：长图，水平居中；宽图，垂直居中
 *         不传使用默认值：长图，水平居中；宽图，垂直居中
 *         centerInside：长图，水平居中；宽图，垂直居中
 *         fitStart：长图，靠左；宽图，靠上
 *         fitEnd：长图，靠右；宽图，靠下
 */
fun ImageView.show(
    any: Any?,
    @DrawableRes placeholder: Int = R.drawable.qmui_icon_switch_normal,
    @DrawableRes error: Int = R.drawable.qmui_icon_switch_normal,
    isUserCenterCrop: Boolean = true,
    isCloseAnim: Boolean = false,
    radiusDp: Float = 0f
) {
    if (null == any) {
        return
    }
    var glideRequest = get(any)

    if (isCloseAnim) {
        glideRequest = glideRequest.dontAnimate()
    }

    /*
     * 注意点：
     *     ①FitCenter()、CenterInside() 效果与默认效果一样；
     *     ②scaleType的变形一定要在角度变形前面。
     */
    val transforms = mutableListOf<Transformation<Bitmap>>()
    if (isUserCenterCrop) {
        transforms.add(CenterCrop())
    }
    if (radiusDp > 0) {
        transforms.add(RoundedCorners(dip(radiusDp)))
    }
    glideRequest = glideRequest.transform(*(transforms.toTypedArray()))

    glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(placeholder)
        .error(error)
        .into(this)
}

/**
 * 加载圆形图片
 */
fun ImageView.circle(
    any: Any?,
    @DrawableRes placeholder: Int = R.drawable.qmui_icon_switch_normal,
    @DrawableRes error: Int = R.drawable.qmui_icon_switch_normal,
    isCloseAnim: Boolean = false
) {
    if (null == any) {
        return
    }
    var glideRequest = get(any)
    if (isCloseAnim) {
        glideRequest = glideRequest.dontAnimate()
    }
    glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
        .transform(CenterInside(), CircleCrop())
        .placeholder(placeholder)
        .error(error)
        .into(this)
}


fun ImageView.load(init: GlideWrapper.() -> Unit) {
    val wrap = GlideWrapper()
    wrap.init()
    execute(wrap)
}


private fun execute(wrapper: GlideWrapper) {
    wrapper.imageView?.let {
        val request = it.get(wrapper.url).placeholder(wrapper.placeholder).error(wrapper.error)
        wrapper.transform?.apply { request.transform(this) }
        request.into(it)
    }
}

class GlideWrapper {
    var url: String? = null
    var imageView: ImageView? = null
    var placeholder: Int = R.drawable.qmui_icon_switch_normal
    var error: Int = R.drawable.qmui_icon_switch_normal
    var transform: Transformation<Bitmap>? = null
}

fun ImageView.get(any: Any?): GlideRequest<Drawable> = GlideApp.with(context).load(any)
//
//fun ImageView.get(file: File): GlideRequest<Drawable> = GlideApp.with(context).load(file).dontAnimate()
//
//fun ImageView.get(@DrawableRes drawResId: Int): GlideRequest<Drawable> = GlideApp.with(context).load(drawResId).dontAnimate()
//
//fun ImageView.get(uri: Uri): GlideRequest<Drawable> = GlideApp.with(context).load(uri).dontAnimate()
//
//fun ImageView.get(bitmap: Bitmap): GlideRequest<Drawable> = GlideApp.with(context).load(bitmap).dontAnimate()