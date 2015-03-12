package lordtony.ejemplo2.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import lordtony.ejemplo2.fragments.AlbumGridFragment;
import lordtony.ejemplo2.fragments.AlbumListFragment;
import lordtony.ejemplo2.fragments.PlacesMapFragment;

/**
 * Created by USER on 09/03/2015.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter{

    private Fragment[] fragments;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[]{
                new AlbumListFragment(),
                new AlbumGridFragment(),
                new PlacesMapFragment()
        };
        Log.e("Tag90:", "CustomPagerAdapter");
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
