package lordtony.ejemplo2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import lordtony.ejemplo2.fragments.MainFragment;
import lordtony.ejemplo2.fragments.TermsFragment;


public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener {

    private ListView drawer_list;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle drawer_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_list = (ListView)findViewById(R.id.left_drawer);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ArrayAdapter<String> drawer_adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item,
                getResources().getStringArray(R.array.array_drawer_options));
        drawer_list.setAdapter(drawer_adapter);
        drawer_list.setOnItemClickListener(this);
        selectItem(0);
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
