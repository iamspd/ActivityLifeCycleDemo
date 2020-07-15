package com.example.activity_life_cycle_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class ListActivity extends ListFragment {

    static final Class[] classArray = new Class[]{AIActivity.class, VRActivity.class};
    private static final String TAG = MainActivity.class.getSimpleName();
    ListView list;
    private String[] activities = new String[]{"AI Activity", "VR Activity"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, activities);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(getActivity(), classArray[position]));
    }

}
