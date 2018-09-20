package com.wholecrypto.airdrops;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class TermsOfServiceActivity extends AppCompatActivity {

    private static final String TAG = "TermsOfServiceActivity";

    // Remote Config keys
    private static final String TERMS_OF_SERVICE_KEY = "terms_of_service";
    private static final String LOADING_TERMS_OF_SERVICE_KEY = "loading_terms_of_service";


    private TextView mTermsOfServiceTextView;


    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terms_of_service);
        Toolbar toolbar = findViewById(R.id.toolbar_terms_of_service);
        setSupportActionBar(toolbar);
        setTitle(getResources().getText(R.string.terms_of_service_title));


        //Reference the Terms of Service TextView and assign a default value to it
        mTermsOfServiceTextView = findViewById(R.id.terms_of_service_text_view);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        //Enable developer mode
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        //Try to fetch data for the TextView, from the Firebase Service
        fetchTermsOfService();

    }


    private void fetchTermsOfService() {
        mTermsOfServiceTextView.setText(mFirebaseRemoteConfig.getString(LOADING_TERMS_OF_SERVICE_KEY));

        long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        // [START fetch_config_with_callback]
        // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
        // will use fetch data from the Remote Config service, rather than cached parameter values,
        // if cached parameter values are more than cacheExpiration seconds old.
        // See Best Practices in the README for more information.
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG,"Fetch Succeed");
                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Log.e(TAG,"Fetch Failed");

                        }
                        displayTermsOfService();
                    }
                });
        // [END fetch_config_with_callback]
    }

    /**
     * Display a welcome message in all caps if welcome_message_caps is set to true. Otherwise,
     * display a welcome message as fetched from welcome_message.
     */
    // [START display_welcome_message]
    private void displayTermsOfService() {
        // [START get_config_values]
        String termsOfServiceMessage = mFirebaseRemoteConfig.getString(TERMS_OF_SERVICE_KEY);
        //Replace all occurrences of <br> to a new line.
        // This is a workaround because we can't do new line directly from Firebase Remote Config
        String termsOfServiceMessageWithNewLines = termsOfServiceMessage.replaceAll("<br>","\n");
        mTermsOfServiceTextView.setText(termsOfServiceMessageWithNewLines);
    }





}
