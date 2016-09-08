package parallaxedview.kiwilll.com.myapplication.parallaxedview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import parallaxedview.kiwilll.com.myapplication.MainActivity;
import parallaxedview.kiwilll.com.myapplication.R;

public abstract class ParallaxedView {
    static public boolean isAPI11 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    protected WeakReference<View> view;
    private WeakReference<View> nextView;
    private WeakReference<TextView> maskView;
    protected int lastOffset;
    protected List<Animation> animations;

    abstract protected void translatePreICS(View view, float offset);

    public ParallaxedView(View view) {
        this.lastOffset = 0;
        this.animations = new ArrayList<Animation>();
        this.view = new WeakReference<View>(view);
    }

    public boolean is(View v) {
        return (v != null && view != null && view.get() != null && view.get().equals(v));
    }

    private boolean isNext(View v) {
        return (v != null && nextView != null && nextView.get() != null && nextView.get().equals(v));
    }

    boolean up;

    public void setUp(boolean up) {
        this.up = up;
    }

    int temp = 0;

    public void setTop(int top, View view) {
        if (view != null && top != 0) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            int newheght = 0;

            if (isNext(view) || nextView == null || nextView.get() == null) {
                if (nextView == null)
                    setNextView(view);
                if (up)//up
                {
                    newheght = (int) ((float) (MainActivity.maxHeight - MainActivity.minHeight) * (float) top / (float) MainActivity.maxHeight) + MainActivity.minHeight;

                } else //down
                {
                    newheght = MainActivity.maxHeight - (int) ((float) (MainActivity.maxHeight - MainActivity.minHeight) * (float) (MainActivity.maxHeight - top) / (float) MainActivity.maxHeight);
                }
                params.height = newheght;
                view.setLayoutParams(params);
            } else {
                if (nextView.get() != null)
                    nextView.get().setTranslationY(0f);

                view.setTranslationY(0f);
                setNextView(view);
            }

            temp = top;
        }


        float scale = (float) top / (float) MainActivity.maxHeight;
        if (maskView != null && maskView.get() != null)
            maskView.get().setAlpha(Math.max((1f - scale), 0.3f));


    }


    @SuppressLint("NewApi")
    public void setOffset(float offset) {
        View view = this.view.get();
        if (view != null)
            if (isAPI11) {
                view.setTranslationY(offset);
            } else {
                translatePreICS(view, offset);
            }
    }

    public void setAlpha(float alpha) {
        View view = this.view.get();
        if (view != null)
            if (isAPI11) {
                view.setAlpha(alpha);
            } else {
                alphaPreICS(view, alpha);
            }
    }

    protected synchronized void addAnimation(Animation animation) {
        animations.add(animation);
    }

    protected void alphaPreICS(View view, float alpha) {
        addAnimation(new AlphaAnimation(alpha, alpha));
    }

    protected synchronized void animateNow() {
        View view = this.view.get();
        if (view != null) {
            AnimationSet set = new AnimationSet(true);
            for (Animation animation : animations)
                if (animation != null)
                    set.addAnimation(animation);
            set.setDuration(0);
            set.setFillAfter(true);
            view.setAnimation(set);
            set.start();
            animations.clear();
        }
    }

    public void setView(View view) {
        this.view = new WeakReference<View>(view);
    }

    public void setNextView(View view) {
        this.nextView = new WeakReference<View>(view);
        TextView textView = (TextView) view.findViewById(R.id.zhuti_mask_item);
        this.maskView = new WeakReference<TextView>(textView);
    }
}
