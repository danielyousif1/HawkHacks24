package com.nathanespejo.blockchaincharityapp;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DatabaseManager {

    static String jsonString;
    public static List<Charity.CharityData> charityDataList;


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
                        parseJsonResponse(jsonString);
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

    private static void parseJsonResponse(String jsonString) {
        Gson gson = new Gson();
        Charity charity = gson.fromJson(jsonString, Charity.class);

        // Now you can access your data using the Charity object
        charityDataList = charity.getData();
    }
}
