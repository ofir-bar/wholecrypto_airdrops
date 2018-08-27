package com.wholecrypto.airdrops;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AirdropsViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "AirdropsViewHolder";

    public View view;
    public AirdropsViewHolder(View itemView) {

        super(itemView);
        view=itemView;
    }

    public void setAirdropData(Airdrop airdrop) {

        // initialise card views items and set value in them
        TextView projectName = view.findViewById(R.id.project_name);
        projectName.setText(airdrop.getProjectName());

        TextView symbol = view.findViewById(R.id.symbol);
        symbol.setText(airdrop.getTokenSymbol());

        TextView about = view.findViewById(R.id.about);
        about.setText(airdrop.getAbout());

        TextView time_end = view.findViewById(R.id.time_end);
        time_end.setText(airdrop.getEndDate());

        //Calculate and set token worth
        TextView tokenWorthText = view.findViewById(R.id.worth);
        //Calculate the estimate worth of 1 Token
        double tokenWorth = Double.valueOf(airdrop.getPricePerToken() ) * Double.valueOf(airdrop.getTokensDistribute());

        tokenWorthText.setText(String.valueOf(tokenWorth));




    }


    }
