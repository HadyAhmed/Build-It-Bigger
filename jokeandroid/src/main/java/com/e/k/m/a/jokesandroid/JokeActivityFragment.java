package com.e.k.m.a.jokesandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public static final String JOKE_EXTRA = "joke";

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        String str = Objects.requireNonNull(getActivity()).getIntent().getStringExtra(JOKE_EXTRA);
        TextView textView = view.findViewById(R.id.tv_joke);
        textView.setText(str);
        return view;

    }
}
