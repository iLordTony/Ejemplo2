package lordtony.ejemplo2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lordtony.ejemplo2.R;
import lordtony.ejemplo2.data.CustomPagerAdapter;
import lordtony.ejemplo2.tabs.SlidingTabLayout;


public class MainFragment extends Fragment {
    private ViewPager view_pager;
    private CustomPagerAdapter adapter;
    private SlidingTabLayout mSlidingTabLayout;

    @Override //Talvez tenga que añadir arobaNullable antes de viewGroup y bundle
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Tag90", "onCreatedView");
        View view = inflater.inflate(R.layout.fragment_main, null);
        view_pager = (ViewPager) view.findViewById(R.id.pager);

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> nombres = new ArrayList<String>();
        for (String name: getResources().getStringArray(R.array.name_tabs)){
            nombres.add(name);
        }

        Log.e("Tag90", "onActivityCreated");
        adapter = new CustomPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.setNombres(nombres);
        view_pager.setAdapter(adapter);

        mSlidingTabLayout.setViewPager(view_pager);


    }

}
