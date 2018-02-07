package br.com.fischborn.classificados.view;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Karen on 23/01/2018.
 */

public class ItemView implements Serializable {
    public ItemView(int mId, String mTitle, String mDescription, double mValue, Uri mPictureId) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mValue = mValue;
        this.mPictureId = mPictureId;
    }

    private int mId;
    private String mTitle;
    private String mDescription;
    private double mValue;
    private Uri mPictureId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getValue() {
        return mValue;
    }

    public void setValue(double value) {
        mValue = value;
    }

    public Uri getPictureId() {
        return mPictureId;
    }

    public void setPictureId(Uri pictureId) {
        mPictureId = pictureId;
    }
}
