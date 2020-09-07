package mm.chenme.lib.commutillibdemo.complex_demo.surface_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import mm.chenme.lib.commutillib.utils.drawable
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/5
 * Email：ibelieve1210@163.com
 */
class MySurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private var mHolder: SurfaceHolder = holder // 用于控制 SurfaceView
    private lateinit var mThread: Thread // 声明一个线程

    @Volatile
    private var mFlag = false // 线程运行的标识，用于控制线程
    private var mCanvas: Canvas? = null // 画布
    private var mCircleRadius = 10f


    private val mPaintBg = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        mHolder.addCallback(this)
        mPaint.color = Color.BLUE
        mPaint.strokeWidth = 10f
        mPaint.style = Paint.Style.FILL

        mPaintBg.color = Color.WHITE
    }

    /**
     * 当 SurfaceView 的视图发生改变的时候，调用此函数
     */
    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    /**
     * 当 SurfaceView 销毁的时候，调用此函数
     */
    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mFlag = false
        mHolder.removeCallback(this)
    }

    /**
     * 当 SurfaceView 创建的时候，调用此函数
     */
    override fun surfaceCreated(holder: SurfaceHolder?) {
        mThread = Thread(Runnable {
            while (mFlag) {
                try {
                    synchronized(mHolder) {
                        Thread.sleep(30) // 让线程休眠一会儿
                        drawCircle()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
//                        mHolder.unlockCanvasAndPost(mCanvas) // 结束锁定画图，并提交改变
                }
            }
        })
        mFlag = true
        mThread.start()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        surfaceDestroyed(mHolder)
        return super.onKeyUp(keyCode, event)
    }

    private fun drawCircle() {
        mCanvas = mHolder.lockCanvas()

        if (null != mCanvas) {


            if (mCircleRadius >= (width / 10f)) {
                mCircleRadius = 0f
            } else {
                mCircleRadius++
            }

            mCanvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaintBg)

            val pic = (drawable(R.mipmap.tmp128) as BitmapDrawable).bitmap
            mCanvas?.drawBitmap(pic, 0f, 0f, mPaint)

            for (i in 0 until 5) {
                for (j in 0 until 8) {
                    mCanvas?.drawCircle(width / 5f * i + width / 10f, height / 8f * j + height / 16f, mCircleRadius, mPaint)
                }
            }
            mHolder.unlockCanvasAndPost(mCanvas) // 完成画画，把画布显示在屏幕上
        }
    }
}