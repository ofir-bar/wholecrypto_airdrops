package com.wholecrypto.airdrops;
import android.support.annotation.Keep;

@Keep
public class Airdrop {

    private String projectName;
    private String projectCategory;
    private String allLinksURL;
    private String startDate;
    private String endDate;
    private String tokenSymbol;
    private String tokensDistribute;
    private String pricePerToken;
    private String kycOrWhitelist;
    private String restrictions;
    private String about;
    private boolean isPremium;
    private String sentBy;
    private boolean approvedAirdrop;
    private String claimInstructions;



    public Airdrop(String projectName, String projectCategory, String allLinksURL, String startDate, String endDate, String tokenSymbol, String tokensDistribute, String pricePerToken, String kycOrWhitelist, String restrictions, String about, boolean isPremium, String sentBy, boolean approvedAirdrop, String claimInstructions) {
        this.projectName = projectName;
        this.projectCategory = projectCategory;
        this.allLinksURL = allLinksURL;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tokenSymbol = tokenSymbol;
        this.tokensDistribute = tokensDistribute;
        this.pricePerToken = pricePerToken;
        this.kycOrWhitelist = kycOrWhitelist;
        this.restrictions = restrictions;
        this.about = about;
        this.isPremium = isPremium;
        this.sentBy = sentBy;
        this.approvedAirdrop = approvedAirdrop;
        this.claimInstructions = claimInstructions;

    }

    public Airdrop(){}  //Required for firebase

    public String getProjectName() {
        return projectName;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public String getAllLinksURL() {
        return allLinksURL;
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

    public String getTokensDistribute() {
        return tokensDistribute;
    }

    public String getPricePerToken() {
        return pricePerToken;
    }

    public String getKycOrWhitelist() {
        return kycOrWhitelist;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public String getAbout() {
        return about;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public String getSentBy() {
        return sentBy;
    }

    public boolean isApprovedAirdrop() {
        return approvedAirdrop;
    }

    public String getClaimInstructions() {
        return claimInstructions;
    }

}
