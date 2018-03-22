package com.keyeswest.jokeviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public final static String JOKE_KEY = "com.keyeswest.jokeviewer.joke_key";

    public static Intent newIntent(Context packageContext, String joke){
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(JOKE_KEY, joke);

        return intent;
    }


    private String mJoke="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mJoke = savedInstanceState.getString(JOKE_KEY);
        } else {
            if (getIntent() != null) {
                mJoke = getIntent().getStringExtra(JOKE_KEY);
            }
        }

        MainActivityFragment mainFragment = MainActivityFragment.newInstance(mJoke);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.joke_container, mainFragment)
                .commit();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(JOKE_KEY, mJoke);

    }


}
