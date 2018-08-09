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
                intent.putExtra(DetailedAirdropActivity.EXTRA_COINNAME, airdrop.getCoinSymbol());
                intent.putExtra(DetailedAirdropActivity.EXTRA_COINSYMBOL, airdrop.getCoinSymbol());
                intent.putExtra(DetailedAirdropActivity.EXTRA_AIRDROP_STARTING_DATE, airdrop.getAirdropStartingDate());
                intent.putExtra(DetailedAirdropActivity.EXTRA_AIRDROP_ENDING_DATE, airdrop.getAirdropEndingDate());
                intent.putExtra(DetailedAirdropActivity.EXTRA_AIRDROP_DESCRIPTION, airdrop.getAirdropDescription());

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

        if (!airdropsList.containsKey(key)) {
            //add to the airdropsList
            airdropsList.put(key, airdrop);

            //add key to the keys lists
            keysList.add(key);

            notifyItemInserted(airdropsList.size());
        }

    }

    public void clear(){
        //clear if contain any values
        if(!airdropsList.isEmpty()){
            airdropsList.clear();
            Log.e(TAG,"airdropsList cleared");
        }
        if(!keysList.isEmpty()){
            keysList.clear();
            Log.e(TAG,"keysList cleared");
        }
    }

}
