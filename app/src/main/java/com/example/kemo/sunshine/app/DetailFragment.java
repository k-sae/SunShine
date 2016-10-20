package com.example.kemo.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import android.support.v7.widget.ShareActionProvider;

/**
 * A simple {@link Fragment} subclass.

 */
public class DetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public DetailFragment() {
        // Required empty public constructor
       setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detailfragment, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
       /* ShareActionProvider mShareActionProvider = new ShareActionProvider(getContext());
        mShareActionProvider.setShareIntent(createShareIntent());
        MenuItemCompat.setActionProvider(menuItem, mShareActionProvider);*/
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if(shareActionProvider != null)
        {
            Log.e("tag", "Iam in");
            shareActionProvider.setShareIntent(createShareIntent());
        }
        else
        {
            Log.e("tag", "Share Action Provider is null?");
        }
    }

    private static final String FORECAST_SHARE_HASH = "#SunShineApp";
    private  String mForcaststr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

            Intent intent = getActivity().getIntent();
            TextView textView = (TextView) view.findViewById(R.id.data_textView);
            mForcaststr = intent.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(mForcaststr);
        return view;
    }
    private Intent createShareIntent()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mForcaststr + FORECAST_SHARE_HASH);
        return shareIntent;
    }

}
