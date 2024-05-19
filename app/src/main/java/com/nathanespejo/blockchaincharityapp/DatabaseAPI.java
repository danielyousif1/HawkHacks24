package com.nathanespejo.blockchaincharityapp;


import android.util.Log;

import com.google.gson.Gson;
import com.nathanespejo.blockchaincharityapp.dataclasses.Charity;
import com.nathanespejo.blockchaincharityapp.dataclasses.Transaction;
import com.nathanespejo.blockchaincharityapp.dataclasses.User;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DatabaseAPI {

    static String jsonString;
    public static List<User.UserData> userDataList;
    public static List<Charity.CharityData> charityDataList;
    public static List<Transaction.TransactionData> transactionDataList;

    public static void fetchData(String dataType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://us-east-2.aws.neurelo.com/rest/" + dataType)
                        .get()
                        .addHeader("X-API-KEY", Secrets.NEURELO_KEY)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        jsonString = response.body().string();
                        Log.d("SQL", jsonString);
                        Gson gson = new Gson();

                        //Parse json into classes
                        switch (dataType) {
                            case "Users":
                                User user = gson.fromJson(jsonString, User.class);
                                userDataList = user.getData();
                                break;
                            case "Charities":
                                Charity charity = gson.fromJson(jsonString, Charity.class);
                                charityDataList = charity.getData();
                                break;
                            case "Transactions":
                                Transaction transaction = gson.fromJson(jsonString, Transaction.class);
                                transactionDataList = transaction.getData();
                                break;
                        }
                    } else {
                        // Handle unsuccessful response
                        Log.e("SQL", "Unsuccessful response: " + response.code());
                    }
                } catch (IOException e) {
                    // Handle IO exception
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
