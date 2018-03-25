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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.keyeswest.jokeviewer.JokeViewerMainActivity;

public abstract class MainActivityBaseFragment extends Fragment implements
        EndpointsAsyncTask.ResultsCallback, View.OnClickListener  {

    protected final static String TAG="MainActivityFragment";

    protected final static int REQUEST_DISPLAY_JOKE = 0;

    protected ProgressBar mProgressSpinner;

    protected boolean mMoreJokes;


    protected LinearLayout mErrorLayout;


    protected TextView mErrorText;

    protected Button mRetryButton;


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.joke_btn:
                getJoke();
                break;
            case R.id.error_btn_retry:
                getJoke();
                break;

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
        new EndpointsAsyncTask(getContext(),this, mProgressSpinner).execute();
    }

    @Override
    public void jokeResult(String joke) {
        if (mErrorLayout.getVisibility() == View.VISIBLE){
            mErrorLayout.setVisibility(View.GONE);
        }
        sendDisplayRequest(joke);
    }

    @Override
    public void networkErrorOccurred() {
        if (mErrorLayout.getVisibility() == View.GONE){
            mErrorText.setText(getResources().getString(R.string.internet_error));
            mErrorLayout.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeButton = root.findViewById(R.id.joke_btn);
        jokeButton.setOnClickListener(this);

        mProgressSpinner = root.findViewById(R.id.progressBar);
        mProgressSpinner.setVisibility(View.GONE);

        mErrorLayout = root.findViewById(R.id.error_layout);
        mErrorText = root.findViewById(R.id.error_txt_cause);
        mRetryButton = root.findViewById(R.id.error_btn_retry);
        mRetryButton.setOnClickListener(this);
        mErrorLayout.setVisibility(View.GONE);

        return root;
    }
}
