package lordtony.ejemplo2.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import lordtony.ejemplo2.fragments.AlbumGridFragment;
import lordtony.ejemplo2.fragments.AlbumListFragment;
import lordtony.ejemplo2.fragments.PlacesMapFragment;

/**
 * Created by USER on 09/03/2015.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter{

    private Fragment[] fragments;
    private ArrayList<String> nombres;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{
                new AlbumListFragment(),
                new AlbumGridFragment(),
                new PlacesMapFragment()
        };


        Log.e("Tag90:", "CustomPagerAdapter");
    }

    public void setNombres(ArrayList<String> nombres){
        this.nombres = nombres;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position){

        return nombres.get(position);
    }

}
