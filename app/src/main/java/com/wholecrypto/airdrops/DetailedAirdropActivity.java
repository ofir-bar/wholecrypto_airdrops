package com.wholecrypto.airdrops;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailedAirdropActivity extends AppCompatActivity {

    public static final String EXTRA_COINNAME = "coinName";
    public static final String EXTRA_COINSYMBOL = "coinSymbol";
    public static final String EXTRA_AIRDROP_STARTING_DATE = "airdropStartingDate";
    public static final String EXTRA_AIRDROP_ENDING_DATE = "airdropEndingDate";
    public static final String EXTRA_AIRDROP_DESCRIPTION = "airdropDescription";
    public static final String EXTRA_AIRDROP_REWARD = "airdropReward";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_airdrop);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //get coin name from intent
        String coinName =(String)getIntent().getExtras().get(EXTRA_COINNAME);
        //Set app label as coinname
        actionBar.setTitle(coinName);

        //get values from intent
        String coinSymbol =(String)getIntent().getExtras().get(EXTRA_COINSYMBOL);
        String airdropStartingDate =(String)getIntent().getExtras().get(EXTRA_AIRDROP_STARTING_DATE);
        String airdropEndingDate =(String)getIntent().getExtras().get(EXTRA_AIRDROP_ENDING_DATE);
        String airdropDescription =(String)getIntent().getExtras().get(EXTRA_AIRDROP_DESCRIPTION);
        String airdropReward =(String)getIntent().getExtras().get(EXTRA_AIRDROP_REWARD);

        //populate the textViews with values from intent

       TextView tv_coinName = findViewById(R.id.coin_name);
       tv_coinName.setText(coinName);

        TextView tv_coinSymbol = findViewById(R.id.coin_symbol);
        tv_coinSymbol.setText(coinSymbol);

        TextView tv_airdropStartingDate = findViewById(R.id.airdrop_start_date);
        tv_airdropStartingDate.setText(airdropStartingDate);

        TextView tv_airdropEndingDate = findViewById(R.id.airdrop_end_date);
        tv_airdropEndingDate.setText(airdropEndingDate);

        TextView tv_airdropDescription = findViewById(R.id.airdrop_description);
        tv_airdropDescription.setText(airdropDescription);

        TextView tv_airdropReward = findViewById(R.id.airdrop_reward);
        tv_airdropReward.setText(airdropReward);

        TextView tv_airdropRewardCoinSymbol = findViewById(R.id.airdrop_reward_coin_symbol);
        tv_airdropRewardCoinSymbol.setText("("+coinSymbol+")");

    }
}
