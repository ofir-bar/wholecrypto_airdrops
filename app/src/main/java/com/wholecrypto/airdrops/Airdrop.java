package com.wholecrypto.airdrops;
import android.support.annotation.Keep;

@Keep
public class Airdrop {

    private String name;
    private String links; // Add links to that
    private String startDate;
    private String endDate;
    private String tokenSymbol;
    private String restrictions;
    private String about; // Add links to that
    private boolean approved;
    private String claimInstructions;
    private String estimatedValue;
    private String contactEmail;

//Note:
    //Class must have getters in order to work with firebase. without getter the field will not be shown in firebase db.

    public Airdrop(String name, String links, String startDate, String endDate, String tokenSymbol, String restrictions, String about, String claimInstructions,String estimatedValue, String contactEmail) {
        this.name = name;
        this.links = links;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tokenSymbol = tokenSymbol;
        this.restrictions = restrictions;
        this.about = about;
        this.claimInstructions = claimInstructions;
        this.estimatedValue = estimatedValue;
        this.contactEmail = contactEmail;
        this.approved = false;
    }

    public Airdrop(){}  //Required for Firebase

    public String getName() {
        return name;
    }

    public String getLinks() {
        return links;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public String getAbout() {
        return about;
    }

    public boolean isApproved() {
        return approved;
    }

    public String getEstimatedValue() {
        return estimatedValue;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getClaimInstructions() {
        return claimInstructions;
    }
}
