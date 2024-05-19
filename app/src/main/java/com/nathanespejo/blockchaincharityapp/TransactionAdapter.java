package com.nathanespejo.blockchaincharityapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nathanespejo.blockchaincharityapp.dataclasses.Charity;
import com.nathanespejo.blockchaincharityapp.dataclasses.Transaction;
import com.nathanespejo.blockchaincharityapp.dataclasses.User;

import java.util.List;

public class TransactionAdapter extends ArrayAdapter<Transaction.TransactionData> {

    private Context mContext;
    private int mResource;

    public TransactionAdapter(@NonNull Context context, int resource, @NonNull List<Transaction.TransactionData> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =  LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView charityNameText = convertView.findViewById(R.id.charityName);
        TextView userText = convertView.findViewById(R.id.sender);
        TextView amountText = convertView.findViewById(R.id.amount);

        List<Transaction.TransactionData> transactionsList = DatabaseAPI.transactionDataList;

        String charityName = null;
        for (Charity.CharityData charity : DatabaseAPI.charityDataList) {
            if (charity.getCharityID() == transactionsList.get(position).getCharityID()) {
                charityName = DatabaseAPI.charityDataList.get(position).getCharityName();
                break;
            }
        }
        String userName = null;
        for (User.UserData user : DatabaseAPI.userDataList) {
            if (user.getUserID() == transactionsList.get(position).getUserID()) {
                userName = user.getFname() + " " + user.getLname();
                break;
            }
        }

        charityNameText.setText(charityName);
        userText.setText("Sender: " + userName);
        amountText.setText("Amount: NEAR$" + Double.toString(transactionsList.get(position).getAmount()));

        return convertView;
    }
}
