package com.example.fibonacciapp;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class Location {

    private int id;

    private String title;

    private String name;
    @SerializedName("pictures")
    private ImageView image;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public ImageView getImage() {
        return image;
    }
}
