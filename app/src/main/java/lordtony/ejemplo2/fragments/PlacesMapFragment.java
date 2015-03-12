package lordtony.ejemplo2.fragments;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import lordtony.ejemplo2.models.Place;

/**
 * Created by USER on 11/03/2015.
 */
public class PlacesMapFragment extends SupportMapFragment {
    private GoogleMap map;
    private HashMap<String, Marker> places_marker_map = new HashMap<String, Marker>();
    public static final LatLng ESCOM = new LatLng(19.504574, -99.146674);
    private final static String PLACES_FILE_NAME = "albums.json";


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = getMap();
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ESCOM, 16));
        map.getUiSettings().setZoomControlsEnabled(false);

        ArrayList<Place> places = loadPlacesFromAssets();
        for(Place place : places){
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(place.getLatLng())
                    .title(place.getTitle())
                    .snippet(place.getDescription())
                    .icon(BitmapDescriptorFactory.fromResource(place.getResourceMarker())));

            places_marker_map.put(place.getTitle(), marker);
        }
    }

    private ArrayList<Place> loadPlacesFromAssets() {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(PLACES_FILE_NAME)));
            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = builder.toString();
        Gson gson = new Gson();

        return new ArrayList<Place>(Arrays.asList(gson.fromJson(json, Place[].class)));
    }

}
