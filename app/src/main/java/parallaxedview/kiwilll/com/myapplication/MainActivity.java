package parallaxedview.kiwilll.com.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import parallaxedview.kiwilll.com.myapplication.parallaxedview.ParallaxListView;
import parallaxedview.kiwilll.com.myapplication.parallaxedview.adapter.ListViewAdapterTheme;


public class MainActivity extends Activity {
    private ListViewAdapterTheme mAdapter;
    private ParallaxListView mThemeListView;
    public static int maxHeight = 900;
    public static int minHeight = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThemeListView = (ParallaxListView) findViewById(R.id.zhuti_list);
        mAdapter = new ListViewAdapterTheme(this, 40, mThemeListView);
        mThemeListView.setAdapter(mAdapter);
    }


}
