package com.wholecrypto.airdrops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.widget.TextView;

public class DetailedAirdropActivity extends AppCompatActivity {

    public static final String EXTRA_PROJECT_NAME_SYMBOL = "projectNameSymbol";
    public static final String EXTRA_RESTRICTIONS = "restrictions";
    public static final String EXTRA_ABOUT = "about";
    public static final String EXTRA_STARTING_DATE = "startingDate";
    public static final String EXTRA_ENDING_DATE = "endingDate";
    public static final String EXTRA_ALL_LINKS_URL = "allLinksURL";
    public static final String EXTRA_ESTIMATED_VALUE = "estimatedValue";
    public static final String EXTRA_CLAIM_INSTRUCTIONS = "claimInstructions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_airdrop);


        Toolbar toolbar = findViewById(R.id.toolbar_detailed_airdrop);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get values from intent
        String projectNameAndSymbol =(String)getIntent().getExtras().get(EXTRA_PROJECT_NAME_SYMBOL);
        String restrictions =(String)getIntent().getExtras().get(EXTRA_RESTRICTIONS);
        String about =(String)getIntent().getExtras().get(EXTRA_ABOUT);
        String startingDate =(String)getIntent().getExtras().get(EXTRA_STARTING_DATE);
        String endingDate =(String)getIntent().getExtras().get(EXTRA_ENDING_DATE);
        String estimatedValue =(String)getIntent().getExtras().get(EXTRA_ESTIMATED_VALUE);
        String socialMedia =(String)getIntent().getExtras().get(EXTRA_ALL_LINKS_URL);

        String claimInstructions =(String)getIntent().getExtras().get(EXTRA_CLAIM_INSTRUCTIONS);

        getSupportActionBar().setTitle(projectNameAndSymbol);

        //populate the textViews with values from intent

       TextView tv_projectNameAndSymbol = findViewById(R.id.symbol);
        tv_projectNameAndSymbol.setText(projectNameAndSymbol);

        TextView tv_restrictions = findViewById(R.id.restrictions);
        tv_restrictions.setText(restrictions);


        TextView tv_about = findViewById(R.id.about);
        tv_about.setText(about);
        Linkify.addLinks(tv_about,Linkify.WEB_URLS);

        TextView tv_startingDate = findViewById(R.id.start_date);
        tv_startingDate.setText(startingDate);

        TextView tv_endingDate = findViewById(R.id.end_date);
        tv_endingDate.setText(endingDate);


        TextView tv_estimatedValue = findViewById(R.id.estimated_value);
        tv_estimatedValue.setText(estimatedValue);


        TextView tv_socialMedia = findViewById(R.id.social_media);
        tv_socialMedia.setText(socialMedia);
        Linkify.addLinks(tv_socialMedia,Linkify.WEB_URLS);


        TextView tv_claimInstructions = findViewById(R.id.steps_to_claim);
        tv_claimInstructions.setText(claimInstructions);
        Linkify.addLinks(tv_claimInstructions,Linkify.WEB_URLS);


    }
}
