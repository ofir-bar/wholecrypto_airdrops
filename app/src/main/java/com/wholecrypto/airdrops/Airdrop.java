package com.wholecrypto.airdrops;
import android.support.annotation.Keep;

@Keep
public class Airdrop {

    private String projectName;
    private String projectCategory;
    private String websiteURL;
    private String socialMediaURL;
    private String whitePaperURL;
    private String bountyURL;
    private String airdropFormURL;
    private String startDate;
    private String endDate;
    private String tokenSymbol;
    private String platform;
    private String tokensDistribute;
    private String pricePerToken;
    private String kycOrWhitelist;
    private String restrictions;
    private String about;
    private boolean isPremium;
    private boolean isTermsApproved;
    private String sentBy;
    private boolean approvedAirdrop;

    public Airdrop(String projectName, String projectCategory, String websiteURL, String socialMediaURL, String whitePaperURL, String bountyURL, String airdropFormURL, String startDate, String endDate, String tokenSymbol, String platform, String tokensDistribute, String pricePerToken, String kycOrWhitelist, String restrictions, String about, boolean isPremium, boolean isTermsApproved, String sentBy, boolean approvedAirdrop) {
        this.projectName = projectName;
        this.projectCategory = projectCategory;
        this.websiteURL = websiteURL;
        this.socialMediaURL = socialMediaURL;
        this.whitePaperURL = whitePaperURL;
        this.bountyURL = bountyURL;
        this.airdropFormURL = airdropFormURL;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tokenSymbol = tokenSymbol;
        this.platform = platform;
        this.tokensDistribute = tokensDistribute;
        this.pricePerToken = pricePerToken;
        this.kycOrWhitelist = kycOrWhitelist;
        this.restrictions = restrictions;
        this.about = about;
        this.isPremium = isPremium;
        this.isTermsApproved = isTermsApproved;
        this.sentBy = sentBy;
        this.approvedAirdrop = approvedAirdrop;
    }

    public Airdrop(){}  //Required for firebase

    public String getProjectName() {
        return projectName;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public String getSocialMediaURL() {
        return socialMediaURL;
    }

    public String getWhitePaperURL() {
        return whitePaperURL;
    }

    public String getBountyURL() {
        return bountyURL;
    }

    public String getAirdropFormURL() {
        return airdropFormURL;
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

    public String getPlatform() {
        return platform;
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

    public boolean isTermsApproved() {
        return isTermsApproved;
    }

    public String getSentBy() {
        return sentBy;
    }

    public boolean isApprovedAirdrop() {
        return approvedAirdrop;
    }
}
