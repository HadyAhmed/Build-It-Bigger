package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

public class MainActivityFragment extends Fragment {

    private OnJokeButtonClickListener onJokeButtonClickListener;
    private FragmentMainBinding mainBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onJokeButtonClickListener = (OnJokeButtonClickListener) context;
        } catch (ClassCastException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mainBinding.adView.loadAd(adRequest);

        mainBinding.jokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onJokeButtonClickListener.onJokeClick();
            }
        });

        return mainBinding.getRoot();
    }

    public MainActivityFragment() {
    }

    public interface OnJokeButtonClickListener {
        void onJokeClick();
    }
}
