package com.example.application;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContainerActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .replace(R.id.container, ListFragment.newInstance())
                .commit();
    }
}
