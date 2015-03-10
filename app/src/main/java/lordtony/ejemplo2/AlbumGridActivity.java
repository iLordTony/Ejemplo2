package lordtony.ejemplo2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import java.util.ArrayList;

import lordtony.ejemplo2.data.CustomAdapter;
import lordtony.ejemplo2.models.Album;


public class AlbumGridActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeLayout;
    private GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_album_grid);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_grid);
        initSwipeOptions();

        grid = (GridView)findViewById(R.id.grid_albums);
        ArrayList<Album> albums = new ArrayList<Album>();

        for(String album : getResources().getStringArray(R.array.array_albums_weezer)){
            Album one_album = new Album(album, Album.WEEZER_ALBUM);
            albums.add(one_album);
        }
        for(String album : getResources().getStringArray(R.array.array_albums_blur)){
            Album one_album = new Album(album, Album.BLUR_ALBUM);
            albums.add(one_album);
        }

        CustomAdapter adapter = new CustomAdapter(this, albums, false);
        grid.setAdapter(adapter);
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
