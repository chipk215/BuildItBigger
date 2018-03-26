package com.keyeswest.jokeviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokeViewerMainActivity extends AppCompatActivity {

    public final static String JOKE_KEY = "com.keyeswest.jokeviewer.joke_key";

    public static Intent newIntent(Context packageContext, String joke){
        Intent intent = new Intent(packageContext, JokeViewerMainActivity.class);
        intent.putExtra(JOKE_KEY, joke);

        return intent;
    }


    private String mJoke="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_viewer_main_activity);

        if (savedInstanceState != null) {
            mJoke = savedInstanceState.getString(JOKE_KEY);
        } else {
            if (getIntent() != null) {
                mJoke = getIntent().getStringExtra(JOKE_KEY);
            }
        }


        JokeViewerMainActivityFragment mainFragment = JokeViewerMainActivityFragment.newInstance(mJoke);
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
