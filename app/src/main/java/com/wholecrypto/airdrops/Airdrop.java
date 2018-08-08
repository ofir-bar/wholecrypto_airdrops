package com.wholecrypto.airdrops;
import android.support.annotation.Keep;

@Keep
public class Airdrop {
    private String coinName;
    private String coinSymbol;
    private String airdropStartingDate;
    private String airdropEndingDate;
    private String airdropDescription;
    private boolean approvedAirdrop;
    private String sentBy;

    public Airdrop(){}  //Required for firebase

    public Airdrop(String coinName, String coinSymbol, String airdropStartingDate, String airdropEndingDate, String airdropDescription,boolean approvedAirdrop,String sentBy) {

        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.airdropStartingDate = airdropStartingDate;
        this.airdropEndingDate = airdropEndingDate;
        this.airdropDescription = airdropDescription;
        this.approvedAirdrop = approvedAirdrop;
        this.sentBy = sentBy;
    }



    public String getCoinName() {

        return coinName;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public String getAirdropStartingDate() {
        return airdropStartingDate;
    }

    public String getAirdropEndingDate() {
        return airdropEndingDate;
    }

    public String getAirdropDescription() {
        return airdropDescription;
    }

    public boolean isApprovedAirdrop() {
        return approvedAirdrop;
    }

    public String getSentBy() {
        return sentBy;
    }

}
