package edu.calpoly.android.databinding_gyllenhaalorgosling;

import android.support.annotation.DrawableRes;

public class Hottie {

    @DrawableRes
    private int mImageRes;
    private String mFirstName;
    private String mLastName;

    public Hottie(@DrawableRes int image, String firstName, String lastName) {
        this.mImageRes = image;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    @DrawableRes
    public int getImageRes() {
        return mImageRes;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    protected static final int JAKE_WINNER = 0;
    protected static final int RYAN_WINNER = 1;
    protected static final int CHEATER = 2;

    protected static Hottie[] HOTTIES = {new Hottie(R.drawable.gyllenhaal_0, "Jake", "Gyllenhaal"),
            new Hottie(R.drawable.gosling_0, "Ryan", "Gosling"),
            new Hottie(R.drawable.gruber_0, "Hans", "Gruber")};
}
