package lordtony.ejemplo2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import lordtony.ejemplo2.data.CustomPagerAdapter;


public class MainActivity extends FragmentActivity {
    private ViewPager view_pager;
    private CustomPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new CustomPagerAdapter(getSupportFragmentManager());
        view_pager = (ViewPager)findViewById(R.id.pager);
        view_pager.setAdapter(adapter);
        Log.e("Tag90", "Main");
    }


}
