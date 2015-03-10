package lordtony.ejemplo2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;

import lordtony.ejemplo2.AlbumDetailActivity;
import lordtony.ejemplo2.R;
import lordtony.ejemplo2.data.CustomAdapter;
import lordtony.ejemplo2.models.Album;


public class AlbumGridFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    private SwipeRefreshLayout swipeLayout;
    private GridView grid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_album_grid, null);
        grid = (GridView) view.findViewById(R.id.grid_albums);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_grid);
        initSwipeOptions();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Album> albums = new ArrayList<Album>();

        for(String album : getResources().getStringArray(R.array.array_albums_weezer)){
            Album one_album = new Album(album, Album.WEEZER_ALBUM);
            albums.add(one_album);
        }
        for(String album : getResources().getStringArray(R.array.array_albums_blur)){
            Album one_album = new Album(album, Album.BLUR_ALBUM);
            albums.add(one_album);
        }

        CustomAdapter adapter = new CustomAdapter(getActivity(), albums, false);

        grid.setOnItemClickListener(this);
        grid.setAdapter(adapter);

        Log.e("Tag90", "AlbumGridFragment");
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Album clicked_album = (Album)grid.getItemAtPosition(position);
        Log.e("Tag90", "Entro al click");

        Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.ALBUM_TYPE, clicked_album.getAlbumType());
        intent.putExtra(AlbumDetailActivity.ALBUM_TITLE, clicked_album.getAlbumTitle());

        startActivity(intent);
    }
}
