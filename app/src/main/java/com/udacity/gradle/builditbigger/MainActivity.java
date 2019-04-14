package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.e.k.m.a.jokesandroid.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static com.e.k.m.a.jokesandroid.JokeActivityFragment.JOKE_EXTRA;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnJokeButtonClickListener {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, getString(R.string.adMob_app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onJokeClick() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    new EndpointAsyncTask().execute(MainActivity.this);
                }
            });
        } else {
            new EndpointAsyncTask().execute(MainActivity.this);
        }
    }

    static class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
        private static final String TAG = "EndpointsTag";
        private final CountDownLatch signal = new CountDownLatch(1);
        private MyApi apiService = null;
        @SuppressLint("StaticFieldLeak")
        private Context context;

        @Override
        protected String doInBackground(Context... params) {
            if (apiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                apiService = builder.build();
            }

            context = params[0];
            String singleJoke = null;
            try {
                singleJoke = apiService.tellJoke().execute().getData();
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: " + e.getMessage());
            }
            return singleJoke;
        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JOKE_EXTRA, s);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            signal.countDown();
        }
    }

}
