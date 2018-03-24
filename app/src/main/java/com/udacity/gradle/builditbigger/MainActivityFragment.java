package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.keyeswest.jokeviewer.JokeViewerMainActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements
        EndpointsAsyncTask.ResultsCallback, View.OnClickListener {
    private final static String TAG="MainActivityFragment";

    private final static int REQUEST_DISPLAY_JOKE = 0;

    private boolean mMoreJokes;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Button jokeButton = root.findViewById(R.id.joke_btn);
        jokeButton.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.joke_btn:
                new EndpointsAsyncTask(getContext(), this).execute();
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
                new EndpointsAsyncTask(getContext(), this).execute();

            }
        }
    }

    private void sendDisplayRequest(String joke){
        Intent intent = JokeViewerMainActivity.newIntent(getContext(),joke);
        startActivityForResult(intent, REQUEST_DISPLAY_JOKE);
    }

    @Override
    public void jokeResult(String joke) {
        sendDisplayRequest(joke);
    }

    @Override
    public void networkErrorOccurred() {

    }
}
