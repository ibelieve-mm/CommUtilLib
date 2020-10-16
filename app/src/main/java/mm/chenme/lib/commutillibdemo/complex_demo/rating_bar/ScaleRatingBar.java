package mm.chenme.lib.commutillibdemo.complex_demo.rating_bar;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import mm.chenme.lib.commutillibdemo.R;


/**
 * Created by willy on 2017/5/5.
 */

public class ScaleRatingBar extends AnimationRatingBar {

    // Control animation speed
    private static final long ANIMATION_DELAY = 15;

    public ScaleRatingBar(Context context) {
        super(context);
    }

    public ScaleRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void emptyRatingBar() {
        // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler.removeCallbacksAndMessages(mRunnableToken);
        }

        long delay = 0;
        for (final PartialView view : mPartialViews) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setEmpty();
                }
            }, delay += 5);
        }
    }

    @Override
    protected void fillRatingBar(final float rating) {
        // Need to remove all previous runnable to prevent emptyRatingBar and fillRatingBar out of sync
        if (mRunnable != null) {
            mHandler.removeCallbacksAndMessages(mRunnableToken);
        }

        if (getRating() <= 1) {
            colorEnd = Color.parseColor("#f35959");
        } else if (getRating() < 5) {
            colorEnd = Color.parseColor("#f98e2d");
        } else {
            colorEnd = Color.parseColor("#ffd900");
        }

        for (final PartialView partialView : mPartialViews) {
            final int ratingViewId = (int) partialView.getTag();
            final double maxIntOfRating = Math.ceil(rating);

            if (ratingViewId > maxIntOfRating) {
                partialView.setEmpty();
                continue;
            }

            mRunnable = getAnimationRunnable(rating, partialView, ratingViewId, maxIntOfRating);
            postRunnable(mRunnable, ANIMATION_DELAY);
        }
    }

    private int mCurrentColor = Color.parseColor("#ffd900");
    int colorEnd = mCurrentColor;

    @NonNull
    private Runnable getAnimationRunnable(final float rating, final PartialView partialView, final int ratingViewId, final double maxIntOfRating) {
        return new Runnable() {
            @Override
            public void run() {
                if (ratingViewId == maxIntOfRating) {
                    partialView.setPartialFilled(rating);
                } else {
                    partialView.setFilled();
                }

//                if (ratingViewId == rating) {
                Animation scaleUp = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
                Animation scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
                partialView.startAnimation(scaleUp);
                partialView.startAnimation(scaleDown);


//                    f35959
                // f98e2d
//                    ffd900

                if (rating <= 1) {
                    colorEnd = Color.parseColor("#f35959");
                } else if (rating > 3) {
                    colorEnd = Color.parseColor("#ffd900");

                } else {
                    colorEnd = Color.parseColor("#f98e2d");

                }
                ObjectAnimator anim = ObjectAnimator.ofInt(new TextView(getContext()), "textColor", mCurrentColor, colorEnd);
                anim.setEvaluator(new ArgbEvaluator());
                anim.setDuration(200);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        partialView.getFilledView().setColorFilter((int) animation.getAnimatedValue());
                    }
                });

                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mCurrentColor = colorEnd;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                anim.start();
//                }
            }
        };
    }


}

