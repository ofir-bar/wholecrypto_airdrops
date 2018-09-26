package com.wholecrypto.airdrops;


import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AirdropsViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "AirdropsViewHolder";

    public View view;
    public AirdropsViewHolder(View itemView) {

        super(itemView);
        view=itemView;
    }

    /**
    Initialize cards in the active airdrops with airdrops
     @param airdrop = a certain airdrop
     */
    public void setAirdropData(Airdrop airdrop) {

        // initialise card views items and set value in them
        TextView projectName = view.findViewById(R.id.project_name);
        projectName.setText(airdrop.getName());

        TextView symbol = view.findViewById(R.id.symbol);
        symbol.setText(airdrop.getTokenSymbol());

        TextView about = view.findViewById(R.id.about);
        about.setText(airdrop.getAbout());

        final String estimatedValue = airdrop.getEstimatedValue();

        ImageView estimatedValueTag = view.findViewById(R.id.ic_estimated_value);

        estimatedValueTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertWithButton("Airdrop Estimated Value",estimatedValue,"OK",view);
            }
        });


    }

    private void showAlertWithButton(@NonNull String title, @NonNull String message, @NonNull String buttonMessage,View view) {
        new AlertDialog.Builder(view.getContext()).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(buttonMessage, null).create().show();
    }


    }
