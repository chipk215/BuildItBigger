package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

@SuppressLint("StaticFieldLeak")
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "EndpointsAsyncTask";
    private static MyApi myApiService = null;


    @Nullable
    private final IdlingResource mIdlingResource =
            EspressoTestingIdlingResource.getIdlingResource();

    private final ResultsCallback mCallback;

    private final Context mContext;

    private boolean mErrorOccurred;

    @javax.annotation.Nullable
    private final ProgressBar mProgressBar;

    public interface ResultsCallback{
        void jokeResult(String joke);

        void networkErrorOccurred();
    }

    public EndpointsAsyncTask(Context context,  ResultsCallback callback,
                              @Nullable ProgressBar progressBar ) {

        mCallback = callback;
        mProgressBar = progressBar;
        mContext = context;
        mErrorOccurred = false;
    }

    @Override
    protected void onPreExecute(){

        // check network connectivity
        if (! NetworkUtilities.isNetworkAvailable(mContext)){
            mCallback.networkErrorOccurred();
            cancel(true);
        }

        if (mProgressBar != null){
            mProgressBar.setVisibility(View.VISIBLE);
        }

        if (mIdlingResource != null){
            EspressoTestingIdlingResource.increment();
        }
    }

    @Override
    protected String doInBackground(Void... params)  {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            mErrorOccurred = true;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mProgressBar != null){
            mProgressBar.setVisibility(View.GONE);
        }

        if (mIdlingResource!= null) {
            EspressoTestingIdlingResource.decrement();
        }

        if (mErrorOccurred){
            mCallback.networkErrorOccurred();
        }else{
            mCallback.jokeResult(result);
        }

    }


}