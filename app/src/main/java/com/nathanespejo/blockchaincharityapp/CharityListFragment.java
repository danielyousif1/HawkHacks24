package com.nathanespejo.blockchaincharityapp;

import static com.nathanespejo.blockchaincharityapp.DatabaseManager.charityDataList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

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


        // Create a string array to hold the names of charities
        List<String> charityNames = new ArrayList<>();
        for (Charity.CharityData charityData : charityDataList) {
            charityNames.add(charityData.getCharityName());
        }

        // Create a string array
        String[] charityNamesArray = new String[charityNames.size()];
        charityNames.toArray(charityNamesArray);


        // Create an ArrayAdapter using the string array and a default list item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, charityNamesArray);

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