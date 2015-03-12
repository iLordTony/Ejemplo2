package lordtony.ejemplo2;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;

import lordtony.ejemplo2.fragments.MainFragment;
import lordtony.ejemplo2.fragments.TermsFragment;


public class MainActivity extends ActionBarActivity  implements ListView.OnItemClickListener {

    private ListView drawer_list;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle drawer_toggle;
    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = this.getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            Log.e("Tag90", "Entro al if, en mainActivity");
        }


        drawer_list = (ListView)findViewById(R.id.left_drawer);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ArrayAdapter<String> drawer_adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item,
                getResources().getStringArray(R.array.array_drawer_options));

        drawer_list.setAdapter(drawer_adapter);
        drawer_list.setOnItemClickListener(this);

        drawer_toggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        drawer_layout.setDrawerListener(drawer_toggle);

        selectItem(0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawer_toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawer_toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer_toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectItem(int position){
        Fragment frag;

        if(position == 0){
            frag = new MainFragment();
        }else{
            frag = new TermsFragment();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, frag)
                .commit();

        drawer_list.setItemChecked(position, true);
        setTitle(drawer_list.getItemAtPosition(position).toString());
        drawer_layout.closeDrawer(drawer_list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }
}
