package lordtony.ejemplo2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import lordtony.ejemplo2.AlbumDetailActivity;
import lordtony.ejemplo2.R;
import lordtony.ejemplo2.data.CustomAdapter;
import lordtony.ejemplo2.models.Album;


public class AlbumListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_list, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container_list);
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

        CustomAdapter adapter = new CustomAdapter(getActivity(), albums, true);

        setListAdapter(adapter);

        Log.e("Tag90", "AlbumListFragment");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Album clicked_album = (Album)l.getItemAtPosition(position);

        Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.ALBUM_TYPE, clicked_album.getAlbumType());
        intent.putExtra(AlbumDetailActivity.ALBUM_TITLE, clicked_album.getAlbumTitle());

        startActivity(intent);

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
