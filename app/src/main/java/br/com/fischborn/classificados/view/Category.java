package br.com.fischborn.classificados.view;

import java.io.Serializable;

/**
 * Created by Karen on 24/01/2018.
 */

public class Category implements Serializable{
    private int mId;
    private String mName;

    public Category(int mId, String mName){
        this.mId = mId;
        this.mName = mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}
