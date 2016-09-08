package parallaxedview.kiwilll.com.myapplication.parallaxedview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import parallaxedview.kiwilll.com.myapplication.MainActivity;
import parallaxedview.kiwilll.com.myapplication.R;
import parallaxedview.kiwilll.com.myapplication.parallaxedview.ParallaxListView;

/**
 * @author 何维
 * @version 1.0
 * @date 2015-9-8
 * @desc 适配器
 */
public class ListViewAdapterTheme extends BaseAdapter {

    private Context context;
    private AbsListView.LayoutParams params2;
    OneViewHolder holder;
    int count;
    int maxHeight;
    int minHeight;
    private ListView listView;

    public ListViewAdapterTheme(Context context, int count, ParallaxListView listView) {
        this.listView = listView;
        this.context = context;
        this.count = count;
        maxHeight = MainActivity.maxHeight;
        minHeight = MainActivity.minHeight;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        holder = new OneViewHolder(context);
        convertView = holder.view;
        if (position < listView.getFirstVisiblePosition()) {
            holder.mask.setAlpha(0.3f);
            params2 = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, maxHeight);
            convertView.setLayoutParams(params2);
        } else {
            holder.mask.setAlpha(1f);
            params2 = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, minHeight);
            convertView.setLayoutParams(params2);
        }


        if (position == 0) {
            holder.mask.setAlpha(0.3f);
            params2 = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, maxHeight);
            convertView.setLayoutParams(params2);
        }

        convertView.setTag(holder);


        holder.mainPicView.setImageResource(R.mipmap.demo);

        return convertView;
    }

    Timer timer;

    private class OneViewHolder {

        ImageView mainPicView;
        TextView mask;
        View view;


        public OneViewHolder(Context context) {
            init();
        }

        public void init() {
            view = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            mainPicView = (ImageView) view.findViewById(R.id.item_theme_list_mainpic);
            mask = (TextView) view.findViewById(R.id.zhuti_mask_item);

        }

    }


}
