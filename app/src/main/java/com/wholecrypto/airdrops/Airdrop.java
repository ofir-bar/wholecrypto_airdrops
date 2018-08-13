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

    public Airdrop(){}  //Required for firebase

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

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getIcoName() {
        return icoName;
    }

    public void setIcoName(String icoName) {
        this.icoName = icoName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSocialMediaURL() {
        return socialMediaURL;
    }

    public void setSocialMediaURL(String socialMediaURL) {
        this.socialMediaURL = socialMediaURL;
    }

    public String getAirdropFormLink() {
        return airdropFormLink;
    }

    public void setAirdropFormLink(String airdropFormLink) {
        this.airdropFormLink = airdropFormLink;
    }

    public String getAirdropImage() {
        return airdropImage;
    }

    public void setAirdropImage(String airdropImage) {
        this.airdropImage = airdropImage;
    }

    public double getAirdropAmount() {
        return airdropAmount;
    }

    public void setAirdropAmount(double airdropAmount) {
        this.airdropAmount = airdropAmount;
    }

    public String getCountryOfOperation() {
        return countryOfOperation;
    }

    public void setCountryOfOperation(String countryOfOperation) {
        this.countryOfOperation = countryOfOperation;
    }

    public setDate getIcoStartDate() {
        return icoStartDate;
    }

    public void setIcoStartDate(setDate icoStartDate) {
        this.icoStartDate = icoStartDate;
    }

    public setDate getIcoEndDate() {
        return icoEndDate;
    }

    public void setIcoEndDate(setDate icoEndDate) {
        this.icoEndDate = icoEndDate;
    }

    public String getWhitepaperURL() {
        return whitepaperURL;
    }

    public void setWhitepaperURL(String whitepaperURL) {
        this.whitepaperURL = whitepaperURL;
    }

    public String getBountyURL() {
        return bountyURL;
    }

    public void setBountyURL(String bountyURL) {
        this.bountyURL = bountyURL;
    }

    public String getTokenTicker() {
        return tokenTicker;
    }

    public void setTokenTicker(String tokenTicker) {
        this.tokenTicker = tokenTicker;
    }

    public String getPlatformAndTokenType() {
        return platformAndTokenType;
    }

    public void setPlatformAndTokenType(String platformAndTokenType) {
        this.platformAndTokenType = platformAndTokenType;
    }

    public double getTokenPrice() {
        return tokenPrice;
    }

    public void setTokenPrice(double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public boolean isApprovedAirdrop() {
        return approvedAirdrop;
    }

    public void setApprovedAirdrop(boolean approvedAirdrop) {
        this.approvedAirdrop = approvedAirdrop;
    }

    public String getSentByPhone() {
        return sentByPhone;
    }

    public void setSentByPhone(String sentByPhone) {
        this.sentByPhone = sentByPhone;
    }
}
