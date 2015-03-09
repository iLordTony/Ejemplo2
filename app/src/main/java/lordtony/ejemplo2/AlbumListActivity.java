package lordtony.ejemplo2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import lordtony.ejemplo2.data.CustomAdapter;
import lordtony.ejemplo2.models.Album;


public class AlbumListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

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

        CustomAdapter adapter = new CustomAdapter(this, albums);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
