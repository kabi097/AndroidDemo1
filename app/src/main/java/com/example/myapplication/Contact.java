package com.example.myapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name = "";
    private int sound;
    private int image;

    public Contact(String name, int sound, int image) {
        this.name = name;
        this.sound = sound;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
