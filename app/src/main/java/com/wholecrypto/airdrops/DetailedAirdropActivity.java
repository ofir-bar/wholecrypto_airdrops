package com.wholecrypto.airdrops;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailedAirdropActivity extends AppCompatActivity {

    public static final String EXTRA_PROJECT_NAME_SYMBOL = "projectNameSymbol";
    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_RESTRICTIONS = "restrictions";
    public static final String EXTRA_ABOUT = "about";
    public static final String EXTRA_STARTING_DATE = "startingDate";
    public static final String EXTRA_ENDING_DATE = "endingDate";
    public static final String EXTRA_TOKENS_DISTRIBUTED = "tokensDistributed";
    public static final String EXTRA_PRICE_PER_TOKEN = "pricePerToken";
    public static final String EXTRA_SOCIAL_MEDIA = "socialMedia";
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
        String category =(String)getIntent().getExtras().get(EXTRA_CATEGORY);
        String restrictions =(String)getIntent().getExtras().get(EXTRA_RESTRICTIONS);
        String about =(String)getIntent().getExtras().get(EXTRA_ABOUT);
        String startingDate =(String)getIntent().getExtras().get(EXTRA_STARTING_DATE);
        String endingDate =(String)getIntent().getExtras().get(EXTRA_ENDING_DATE);
        String tokensDistributed =(String)getIntent().getExtras().get(EXTRA_TOKENS_DISTRIBUTED);
        String pricePerToken =(String)getIntent().getExtras().get(EXTRA_PRICE_PER_TOKEN);
        String socialMedia =(String)getIntent().getExtras().get(EXTRA_SOCIAL_MEDIA);
        String claimInstructions =(String)getIntent().getExtras().get(EXTRA_CLAIM_INSTRUCTIONS);

        toolbar.setTitle(projectNameAndSymbol);

        //populate the textViews with values from intent

       TextView tv_projectNameAndSymbol = findViewById(R.id.symbol);
        tv_projectNameAndSymbol.setText(projectNameAndSymbol);

        TextView tv_category = findViewById(R.id.category);
        tv_category.setText(category);

        TextView tv_restrictions = findViewById(R.id.restrictions);
        tv_restrictions.setText(restrictions);

        TextView tv_about = findViewById(R.id.about);
        tv_about.setText(about);

        TextView tv_startingDate = findViewById(R.id.start_date);
        tv_startingDate.setText(startingDate);

        TextView tv_endingDate = findViewById(R.id.end_date);
        tv_endingDate.setText(endingDate);

        TextView tv_tokensDistributed = findViewById(R.id.token_distributed);
        tv_tokensDistributed.setText(tokensDistributed);

        TextView tv_pricePerToken = findViewById(R.id.token_worth);
        tv_pricePerToken.setText(pricePerToken);

        TextView tv_socialMedia = findViewById(R.id.social_media);
        tv_socialMedia.setText(socialMedia);

        TextView tv_claimInstructions = findViewById(R.id.steps_to_claim);
        tv_claimInstructions.setText(claimInstructions);


    }
}
