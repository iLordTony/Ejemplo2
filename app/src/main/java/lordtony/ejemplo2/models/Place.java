package lordtony.ejemplo2.models;

import com.google.android.gms.maps.model.LatLng;

import lordtony.ejemplo2.R;

/**
 * Created by USER on 11/03/2015.
 */
public class Place {

    private int type;
    private String title;
    private double[] location;
    private String description;

    public final static int TYPE_OTHER = 0;
    public final static int TYPE_WEEZER= 1;
    public final static int TYPE_BLUR = 2;

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getResourceMarker() {
        int resource = 0;
        switch (type) {
            case TYPE_OTHER:
                resource = R.drawable.marker_other;
                break;
            case TYPE_WEEZER:
                resource = R.drawable.marker_weezer;
                break;
            case TYPE_BLUR:
                resource = R.drawable.marker_blur;
                break;
        }
        return resource;
    }
    public double[] getLocation() {
        return location;
    }
    public LatLng getLatLng() {
        return new LatLng(location[0], location[1]);
    }
    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Type: " + type +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nLocation: " + location[0] + "," + location[1];
    }

}
