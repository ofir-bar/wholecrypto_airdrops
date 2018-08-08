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

    public void setAirdropData(Airdrop airdrop)
    {

        // initialise card views items and set value in them
        TextView coinName = view.findViewById(R.id.coin_name);
        coinName.setText(airdrop.getCoinName());

        TextView coinSymbol = view.findViewById(R.id.coin_symbol);
        coinSymbol.setText(airdrop.getCoinSymbol());

        TextView airdropStartingDate = view.findViewById(R.id.airdrop_start_date);
        airdropStartingDate.setText(airdrop.getAirdropStartingDate());

        TextView airdropEndingDate = view.findViewById(R.id.airdrop_end_date);
        airdropEndingDate.setText(airdrop.getAirdropEndingDate());

        TextView airdropDescription = view.findViewById(R.id.airdrop_description);
        airdropDescription.setText(airdrop.getAirdropDescription());

    }

    }
