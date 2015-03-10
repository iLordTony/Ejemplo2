package lordtony.ejemplo2;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.util.ArrayList;

import lordtony.ejemplo2.data.CustomAdapter;
import lordtony.ejemplo2.models.Album;


public class AlbumListActivity extends ListActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        initSwipeOptions();

        ListView list = getListView();
        ArrayList<Album> albums = new ArrayList<Album>();

        for(String album : getResources().getStringArray(R.array.array_albums_weezer)){
            Album one_album = new Album(album, Album.WEEZER_ALBUM);
            albums.add(one_album);
        }
        for(String album : getResources().getStringArray(R.array.array_albums_blur)){
            Album one_album = new Album(album, Album.BLUR_ALBUM);
            albums.add(one_album);
        }

        CustomAdapter adapter = new CustomAdapter(this, albums, true);
        setListAdapter(adapter);
    }

    private void initSwipeOptions() {
        swipeLayout.setOnRefreshListener(this);
        setAppearance();
    }

    private void setAppearance() {
        swipeLayout.setColorScheme(android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 4000);
    }
}
