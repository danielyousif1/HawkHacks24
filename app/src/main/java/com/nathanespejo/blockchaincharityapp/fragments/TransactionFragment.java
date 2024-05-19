package com.nathanespejo.blockchaincharityapp.fragments;

import static com.nathanespejo.blockchaincharityapp.DatabaseAPI.transactionDataList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.nathanespejo.blockchaincharityapp.R;
import com.nathanespejo.blockchaincharityapp.TransactionAdapter;

public class TransactionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        // Get reference to ListView
        ListView listView = view.findViewById(R.id.listView);

        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(), R.layout.transaction_item, transactionDataList);

        listView.setAdapter(transactionAdapter);

        /*// Create a string array to hold the names of charities
        List<String> transactionList = new ArrayList<>();
        for (Transaction.TransactionData transactionData : transactionDataList) {
            transactionList.add(Double.toString(transactionData.getAmount()));
        }

        // Create a string array
        String[] transactionsAmountsArray = new String[transactionList.size()];
        transactionList.toArray(transactionsAmountsArray);


        // Create an ArrayAdapter using the string array and a default list item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, transactionsAmountsArray);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);*/

        return view;
    }
}