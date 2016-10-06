package com.example.kemo.sunshine.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //idk what is this
        //TODO
        //      1-search for it
        //      2-may ask yehia about it
        View view = inflater.inflate(R.layout.fragment_holder, container, false);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textView,
                createForecastEntry()
        );
        ListView listView = (ListView) view.findViewById(R.id.listView_forecast);
        listView.setAdapter(arrayAdapter);

        view = downloadWeatherData(view);
        //this not working
        //return inflater.inflate(R.layout.fragment_holder, container, false);
        //while this working
        return view;
    }

    private List<String> createForecastEntry() {

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
        return forecastEntry;
    }

    private View downloadWeatherData(View view) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return view;
    }

}
