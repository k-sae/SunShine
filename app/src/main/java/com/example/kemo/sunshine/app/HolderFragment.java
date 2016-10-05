package com.example.kemo.sunshine.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class HolderFragment extends Fragment {

    public HolderFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //idk what is this
        //TODO
        //      1-search for it
        //      2-may ask yehia about it
        View view = inflater.inflate(R.layout.fragment_holder, container,false);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textView,
                createForecastEntry()
                );
        ListView listView = (ListView) view.findViewById(R.id.listView_forecast);
        listView.setAdapter(arrayAdapter);
        //this not working
        //return inflater.inflate(R.layout.fragment_holder, container, false);
        //while this working
        return view;
    }
    private List<String> createForecastEntry()
    {

        List<String> forecastEntry = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            forecastEntry.add("Mon 6/23 - Sunny - 31/17");
            forecastEntry.add("Tue 6/24 - Foggy - 21/8");

            forecastEntry.add("Wed 6/25 - Cloudy - 22/17");
            forecastEntry.add("Thurs 6/26 - Rainy - 18/11");

            forecastEntry.add("Fri 6/27 - Foggy - 21/10");

            forecastEntry.add("Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18");

            forecastEntry.add("Sun 6/29 - Sunny - 20/7");
        }
        return  forecastEntry;
    }

}
