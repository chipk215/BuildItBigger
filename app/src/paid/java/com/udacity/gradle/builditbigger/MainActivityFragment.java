package com.udacity.gradle.builditbigger;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.keyeswest.jokeviewer.JokeViewerMainActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements
        EndpointsAsyncTask.ResultsCallback, View.OnClickListener {
    private final static String TAG="MainActivityFragment";

    private final static int REQUEST_DISPLAY_JOKE = 0;

    private ProgressBar mProgressSpinner;

    private boolean mMoreJokes;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);



        Button jokeButton = root.findViewById(R.id.joke_btn);
        jokeButton.setOnClickListener(this);

        mProgressSpinner = root.findViewById(R.id.progressBar);
        mProgressSpinner.setVisibility(View.GONE);

        return root;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.joke_btn:
                getJoke();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DISPLAY_JOKE){
            if (data == null){
                return;
            }
            mMoreJokes = JokeViewerMainActivity.moreJokes(data);
            Log.d(TAG, "More Jokes:" + Boolean.toString(mMoreJokes));
            if (mMoreJokes){
                getJoke();

            }
        }
    }

    private void sendDisplayRequest(String joke){
        Intent intent = JokeViewerMainActivity.newIntent(getContext(),joke);
        startActivityForResult(intent, REQUEST_DISPLAY_JOKE);
    }

    private void getJoke(){
        new EndpointsAsyncTask(this, mProgressSpinner).execute();
    }

    @Override
    public void jokeResult(String joke) {
        sendDisplayRequest(joke);
    }

    @Override
    public void networkErrorOccurred() {

    }
}
