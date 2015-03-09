package lordtony.ejemplo2.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;

import lordtony.ejemplo2.models.Album;
import lordtony.ejemplo2.R;

/**
 * Created by USER on 08/03/2015.
 */
public class CustomAdapter extends ArrayAdapter<Album>{
    ArrayList<Album> data;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Album> objects){
        super(context, -1, objects);
        this.data = objects;
        this.inflater = LayoutInflater.from(context);
    }

    private static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView subtitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Album current_album = data.get(position);
        String album_type = current_album.getAlbumType();

        int img_resource = 0;
        if(album_type.equals(Album.WEEZER_ALBUM)){
            img_resource = R.drawable.album1;
        }else{
            img_resource = R.drawable.album2;
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();

            holder.img= (ImageView) convertView.findViewById(R.id.img_row);
            holder.title = (TextView) convertView.findViewById(R.id.txt_row_title);
            holder.subtitle = (TextView) convertView.findViewById(R.id.txt_row_subtitle);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.title.setText(current_album.getAlbumTitle());
        holder.subtitle.setText(album_type);
        holder.img.setImageResource(img_resource);

        return convertView;
    }
}
