package com.wholecrypto.airdrops;
import android.support.annotation.Keep;

@Keep
public class Airdrop {
    private boolean isPremium;
    private String icoName;
    private String category;
    private String websiteURL;
    private String about;
    private String socialMediaURL;
    private String airdropFormLink;
    private String airdropImage;
    private double airdropAmount;
    private String countryOfOperation;
    private setDate icoStartDate;
    private setDate icoEndDate;
    private String whitepaperURL;
    private String bountyURL;
    private String tokenTicker;
    private String platformAndTokenType;
    private double tokenPrice;
    private String restrictions;
    private String contactEmail;
    private boolean approvedAirdrop;
    private String sentByPhone;
    private String check;

    public Airdrop(){}  //Required for firebase

    public Airdrop(String check){
        this.check = check;
    }

    public Airdrop(boolean isPremium, String icoName, String category, String websiteURL, String about, String socialMediaURL, String airdropFormLink, String airdropImage, double airdropAmount, String countryOfOperation, setDate icoStartDate, setDate icoEndDate, String whitepaperURL, String bountyURL, String tokenTicker, String platformAndTokenType, double tokenPrice, String restrictions, String contactEmail, boolean approvedAirdrop, String sentByPhone) {
        this.isPremium = isPremium;
        this.icoName = icoName;
        this.category = category;
        this.websiteURL = websiteURL;
        this.about = about;
        this.socialMediaURL = socialMediaURL;
        this.airdropFormLink = airdropFormLink;
        this.airdropImage = airdropImage;
        this.airdropAmount = airdropAmount;
        this.countryOfOperation = countryOfOperation;
        this.icoStartDate = icoStartDate;
        this.icoEndDate = icoEndDate;
        this.whitepaperURL = whitepaperURL;
        this.bountyURL = bountyURL;
        this.tokenTicker = tokenTicker;
        this.platformAndTokenType = platformAndTokenType;
        this.tokenPrice = tokenPrice;
        this.restrictions = restrictions;
        this.contactEmail = contactEmail;
        this.approvedAirdrop = approvedAirdrop;
        this.sentByPhone = sentByPhone;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public String getIcoName() {
        return icoName;
    }

    public String getCategory() {
        return category;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public String getAbout() {
        return about;
    }

    public String getSocialMediaURL() {
        return socialMediaURL;
    }

    public String getAirdropFormLink() {
        return airdropFormLink;
    }

    public String getAirdropImage() {
        return airdropImage;
    }

    public double getAirdropAmount() {
        return airdropAmount;
    }

    public String getCountryOfOperation() {
        return countryOfOperation;
    }

    public setDate getIcoStartDate() {
        return icoStartDate;
    }

    public setDate getIcoEndDate() {
        return icoEndDate;
    }

    public String getWhitepaperURL() {
        return whitepaperURL;
    }

    public String getBountyURL() {
        return bountyURL;
    }

    public String getTokenTicker() {
        return tokenTicker;
    }

    public String getPlatformAndTokenType() {
        return platformAndTokenType;
    }

    public double getTokenPrice() {
        return tokenPrice;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public boolean isApprovedAirdrop() {
        return approvedAirdrop;
    }

    public String getSentByPhone() {
        return sentByPhone;
    }
}
