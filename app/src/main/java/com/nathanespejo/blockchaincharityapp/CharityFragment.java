package com.nathanespejo.blockchaincharityapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CharityFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charity, container, false);

        int index = getArguments().getInt("index"); //Get charity class?
        Charity.CharityData charity = DatabaseManager.charityDataList.get(index);

        TextView name = view.findViewById(R.id.name);
        name.setText(charity.getCharityName());
        TextView description = view.findViewById(R.id.description);
        description.setText(charity.getCharityDescL());

        return view;
    }
}