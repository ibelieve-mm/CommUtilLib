package mm.chenme.lib.commutillibdemo.complex_demo.staggered_grid;

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/10/23
 * Email：ibelieve1210@163.com
 */

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AutoScrollImageView extends ViewGroup {
    public static final int ORIENTATION_LEFT = 1;
    public static final int ORIENTATION_RIGHT = 2;
    public static final int ORIENTATION_TOP = 3;
    public static final int ORIENTATION_BOTTOM = 4;
    private final int ANIM_DURATION = 1500;
    private ImageView imageView1;
    private ImageView imageView2;
    private int width = 0;
    private int height = 0;
    private int translation = 0;
    private boolean isPlaying = false;
    private int orientation = ORIENTATION_TOP;
    private AnimatorSet set;

    public AutoScrollImageView(Context context) {
        this(context, null);
    }

    public AutoScrollImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        imageView1 = new ImageView(context);
        addView(imageView1);
        imageView2 = new ImageView(context);
        addView(imageView2);
    }

    public void play(int resId1, int resId2, int orientation) {
        imageView1.setImageResource(resId1);
        imageView2.setImageResource(resId2);
        this.orientation = orientation;
        isPlaying = true;
    }

    private void startAnim() {
        if (!isPlaying) {
            return;
        }

        if (set != null) {
            set.cancel();
            set = null;
        }

        String propertyName = "translationX";
        if (orientation == ORIENTATION_LEFT) {
            propertyName = "translationX";
            translation = -width;
        } else if (orientation == ORIENTATION_RIGHT) {
            propertyName = "translationX";
            translation = width;
        } else if (orientation == ORIENTATION_TOP) {
            propertyName = "translationY";
            translation = -height;
        } else if (orientation == ORIENTATION_BOTTOM) {
            propertyName = "translationY";
            translation = height;
        }

        LinearInterpolator interpolator = new LinearInterpolator();
        AnimatorSet animatorSet1 = new AnimatorSet();
        Animator anim11 = ObjectAnimator.ofFloat(imageView1, propertyName, 0, translation);
        anim11.setDuration(ANIM_DURATION);
        anim11.setInterpolator(interpolator);

        Animator anim12 = ObjectAnimator.ofFloat(imageView1, propertyName, -translation, 0);
        anim12.setDuration(ANIM_DURATION);
        anim12.setInterpolator(interpolator);
        animatorSet1.playSequentially(anim11, anim12);

        AnimatorSet animatorSet2 = new AnimatorSet();
        Animator anim21 = ObjectAnimator.ofFloat(imageView2, propertyName, -translation, 0);
        anim21.setDuration(ANIM_DURATION);
        anim21.setInterpolator(interpolator);

        Animator anim22 = ObjectAnimator.ofFloat(imageView2, propertyName, 0, translation);
        anim22.setDuration(ANIM_DURATION);
        anim22.setInterpolator(interpolator);
        animatorSet2.playSequentially(anim21, anim22);

        set = new AnimatorSet();
        set.playTogether(animatorSet1, animatorSet2);
        set.start();
        set.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animation.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
    }

    public void stopAnim() {
        isPlaying = false;

        if (set != null) {
            set.cancel();
            set = null;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        height = (int) (widthSize / 4.5);
        width = height * 7;

        int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), heightSpec);
        setMeasuredDimension(widthMeasureSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        imageView1.layout(0, 0, width, height);
        imageView2.layout(0, 0, width, height);
        startAnim();
    }
}