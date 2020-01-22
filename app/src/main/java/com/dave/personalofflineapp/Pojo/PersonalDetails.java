package com.dave.personalofflineapp.Pojo;

public class PersonalDetails {

    private String mFirstName;
    private String mLastName;
    private String mPhone;
    private byte[] mImage;

    public PersonalDetails(String mFirstName, String mLastName, String mPhone, byte[] mImage) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mPhone = mPhone;
        this.mImage = mImage;
    }

    public PersonalDetails() {
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }
}
