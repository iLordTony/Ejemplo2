package lordtony.ejemplo2;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import lordtony.ejemplo2.data.CustomPagerAdapter;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, ActionBar.TabListener{
    private ViewPager view_pager;
    private CustomPagerAdapter adapter;
    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CustomPagerAdapter(getSupportFragmentManager());

        bar = getActionBar();
        if (bar != null) {
            bar.removeAllTabs();
        }
        if (bar != null) {
            bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        if (bar != null) {
            bar.addTab(bar.newTab().setText("Lista").setTabListener(this));
        }
        if (bar != null) {
            bar.addTab(bar != null ? bar.newTab().setText("Grid").setTabListener(this) : null);
        }

        view_pager = (ViewPager)findViewById(R.id.pager);
        view_pager.setAdapter(adapter);
        view_pager.setOnPageChangeListener(this);

        Log.e("Tag90", "Main");
    }


    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        bar.setSelectedNavigationItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        view_pager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }
}
