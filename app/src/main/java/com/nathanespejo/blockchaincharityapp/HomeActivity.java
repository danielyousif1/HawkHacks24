package com.nathanespejo.blockchaincharityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Open charitylist frag
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CharityListFragment())
                .commit();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch(tab.getPosition()){
                    case 0:
                        fragment = new CharityListFragment();
                    case 1:
                        return;
                    case 2:
                        return;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void openCharityFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}