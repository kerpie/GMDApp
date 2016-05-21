package com.example.application;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContainerActivity extends AppCompatActivity implements ItemViewHolder.HandleEvent{

    public static final String TAG = ContainerActivity.class.getSimpleName();

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

    @Override
    public void itemPressed(long id) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container, DetailFragment.newInstance(id))
                .addToBackStack(null)
                .commit();
    }
}
