package com.nathanespejo.blockchaincharityapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CharityListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charity_list, container, false);

        // Get reference to ListView
        ListView listView = view.findViewById(R.id.charityListView);

        // Create a string array
        String[] charityArray = {"Charity A", "Charity B", "Charity C"};

        // Create an ArrayAdapter using the string array and a default list item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, charityArray);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                CharityFragment fragment = new CharityFragment();
                fragment.setArguments(bundle);
                HomeActivity activity = (HomeActivity) getActivity();
                activity.openCharityFragment(fragment);
            }
        });

        return view;
    }
}