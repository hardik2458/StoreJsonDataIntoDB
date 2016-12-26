package com.example.dadabhagwan.storejsondataintodb;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dadabhagwan on 11/16/2016.
 */

public class Actors implements Parcelable {
    private String name;
    private String description;
    private String dob;
    private String country;
    private String height;
    private String spouse;
    private String children;
    private String image;
    public Actors() {

    }

    //Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getSpouse() {
        return spouse;
    }
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
    public String getChildren() {
        return children;
    }
    public void setChildren(String children) {
        this.children = children;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.dob);
        dest.writeString(this.country);
        dest.writeString(this.height);
        dest.writeString(this.spouse);
        dest.writeString(this.children);
        dest.writeString(this.image);
    }

    protected Actors(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.dob = in.readString();
        this.country = in.readString();
        this.height = in.readString();
        this.spouse = in.readString();
        this.children = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Actors> CREATOR = new Parcelable.Creator<Actors>() {
        @Override
        public Actors createFromParcel(Parcel source) {
            return new Actors(source);
        }

        @Override
        public Actors[] newArray(int size) {
            return new Actors[size];
        }
    };
}