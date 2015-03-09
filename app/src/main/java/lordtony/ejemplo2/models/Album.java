package lordtony.ejemplo2.models;

/**
 * Created by USER on 07/03/2015.
 */
public class Album {
    public final static String WEEZER_ALBUM = "Weezer";
    public final static String BLUR_ALBUM = "Blur";
    private String album_type = "Weezer";
    private String album_title;

    public Album(String album_title, String album_type){
        this.album_title = album_title;
        this.album_type = album_type;
    }

    public String getAlbumTitle(){
        return album_title;
    }
    public void setAlbumTitle(String album_title){
        this.album_title = album_title;
    }

    public String getAlbumType(){
        return album_type;
    }
    public void setAlbumType(String album_type){
        this.album_type = album_type;
    }
}
