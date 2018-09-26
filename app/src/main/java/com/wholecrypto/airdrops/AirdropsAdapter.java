package com.wholecrypto.airdrops;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AirdropsAdapter extends RecyclerView.Adapter<AirdropsViewHolder>{

    private static final String TAG = "AirdropsAdapter";

    private LinkedHashMap<String,Airdrop> airdropsList = new LinkedHashMap<String, Airdrop>();
    private ArrayList<String> keysList = new ArrayList<>();
    //use arrayMap to get the data and key

    public AirdropsAdapter(){
        airdropsList = new LinkedHashMap<>();
    }

    @Override
    public AirdropsViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //inflating card view item
        View v = inflater.inflate(R.layout.airdrop_card,parent,false);

        return new AirdropsViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final AirdropsViewHolder holder, int position){

        String itemKey = keysList.get(position);
        final Airdrop airdrop = airdropsList.get(itemKey);
        holder.setAirdropData(airdrop);

        //set a new activity if card is clicked and pass information via intent
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedAirdropActivity.class);


                //Gathers all details from the tapped airdrop and pass it to the airdrop-in detail activity
                intent.putExtra(DetailedAirdropActivity.EXTRA_PROJECT_NAME_SYMBOL, airdrop.getName());
                intent.putExtra(DetailedAirdropActivity.EXTRA_RESTRICTIONS, airdrop.getRestrictions());
                intent.putExtra(DetailedAirdropActivity.EXTRA_ABOUT, airdrop.getAbout());
                intent.putExtra(DetailedAirdropActivity.EXTRA_STARTING_DATE, airdrop.getStartDate());
                intent.putExtra(DetailedAirdropActivity.EXTRA_ENDING_DATE, airdrop.getEndDate());
                intent.putExtra(DetailedAirdropActivity.EXTRA_ALL_LINKS_URL, airdrop.getLinks());
                intent.putExtra(DetailedAirdropActivity.EXTRA_ESTIMATED_VALUE, airdrop.getEstimatedValue());
                intent.putExtra(DetailedAirdropActivity.EXTRA_CLAIM_INSTRUCTIONS, airdrop.getClaimInstructions());


                //Start Activity with details on a specific airdrop
                v.getContext().startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return airdropsList.size();
    }



    public void addAirdropItem(String key,Airdrop airdrop) {
        Log.d(TAG,"AirdropsAdapter: addAirdropItem");

        if (!airdropsList.containsKey(key)) {
            //add to the airdropsList
            airdropsList.put(key, airdrop);

            //add key to the keys lists
            keysList.add(key);

            notifyItemInserted(airdropsList.size());
        }

    }

}
