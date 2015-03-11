package lordtony.ejemplo2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lordtony.ejemplo2.R;
import lordtony.ejemplo2.data.CustomPagerAdapter;


public class MainFragment extends Fragment {
    private ViewPager view_pager;
    private CustomPagerAdapter adapter;

    @Override //Talvez tenga que añadir arobaNullable antes de viewGroup y bundle
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        view_pager = (ViewPager) view.findViewById(R.id.pager);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new CustomPagerAdapter(getActivity().getSupportFragmentManager());
        view_pager.setAdapter(adapter);

        Log.e("Tag90", "MainFragment");
    }

}
