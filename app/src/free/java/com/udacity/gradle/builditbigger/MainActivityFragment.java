package com.udacity.gradle.builditbigger;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.erikduisters.javajokes.JavaJoker;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private final JavaJoker javaJoker;
    private InterstitialAd interstitialAd;

    public MainActivityFragment() {
        javaJoker = new JavaJoker();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(getContext(),"ca-app-pub-3940256099942544~3347511713" );

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @OnClick(R.id.button)
    public void tellJoke() {
        //Toast.makeText(getContext(), javaJoker.getJoke(), Toast.LENGTH_SHORT).show();

        /*
        Intent intent = new Intent(getContext(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayActivity.KEY_JOKE, javaJoker.getJoke());

        startActivity(intent);
        */

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }

        /* I don't see the point in displaying a progressBar and an interstitialAd at the same time */

        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute(getContext());
    }
}

