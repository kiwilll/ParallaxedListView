package parallaxedview.kiwilll.com.myapplication.parallaxedview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import parallaxedview.kiwilll.com.myapplication.MainActivity;


public class ParallaxListView extends ListView implements ParallaxListViewHelper.TopCallBack {

    private ParallaxListViewHelper helper;
    private Context context;
    private int top;
    private int lastHeigh;





    public ParallaxListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        this.context = context;

        helper = new ParallaxListViewHelper(context, attrs, this);
        helper.setCallBack(this);
        super.setOnScrollListener(helper);
        setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        helper.setOnScrollListener(l);
    }

    public void addParallaxedHeaderView(View v) {
        super.addHeaderView(v);
        helper.addParallaxedHeaderView(v);
    }

    public void addParallaxedHeaderView(View v, Object data, boolean isSelectable) {
        super.addHeaderView(v, data, isSelectable);
        helper.addParallaxedHeaderView(v, data, isSelectable);
    }

    @Override
    public void callBack(int top, int height) {
        this.top = top;
        this.lastHeigh = height;
    }

    private int startY;
    private int mStartY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                int tmpY = (int) ev.getY();
                int space = tmpY - startY;
                if (Math.abs(space) < 200) {
                    if (space < 0) {//up
                        smoothScrollBy(lastHeigh - top, MainActivity.maxHeight);
                    }
                    if (space > 0) {//xia
                        smoothScrollBy(-top, MainActivity.maxHeight);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int mtmpY = (int) ev.getY();
                int mspace = mtmpY - mStartY;

                if (mspace < 0) {//up
                    if (helper != null)
                        helper.setUp(true);
                }
                if (mspace > 0) {//xia
                    if (helper != null)
                        helper.setUp(false);
                }
                mStartY = mtmpY;
                break;


        }
        return super.dispatchTouchEvent(ev);
    }
}
