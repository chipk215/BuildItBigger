package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivityFragment extends MainActivityBaseFragment {

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    private String mJoke;

    @Nullable
    private final IdlingResource mIdlingResource =
            EspressoTestingIdlingResource.getIdlingResource();

    private InterstitialAd mInterstitialAd;
    public MainActivityFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = super.onCreateView(inflater, container, savedInstanceState);

        //Interstitial Setup
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(AD_UNIT_ID);

        // view for banner ad
        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        // Set an AdListener.
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                if (mIdlingResource != null){
                  //  EspressoTestingIdlingResource.decrement();
                }
            }

            @Override
            public void onAdClosed() {

                requestNewInterstitial();;
                sendDisplayRequest(mJoke);

            }
        });

        requestNewInterstitial();

        return root;
    }




    @Override
    public void jokeResult(String joke) {
        if (mErrorLayout.getVisibility() == View.VISIBLE){
            mErrorLayout.setVisibility(View.GONE);
        }

        insertInterstitial();
        mJoke = joke;

    }

    private void requestNewInterstitial(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void insertInterstitial(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            if (mIdlingResource != null){
             //   EspressoTestingIdlingResource.increment();
            }
        }else{
            getJoke();
        }
    }


}
