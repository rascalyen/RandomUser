package com.example.yen.ru.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yenhuang on 1/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Parcelable {

    @JsonProperty("gender")
    private String gender;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("email")
    private String email;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("registered")
    private String registered;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("cell")
    private String cell;
    @JsonProperty("picture")
    private Picture picture;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.email);
        dest.writeString(this.dob);
        dest.writeString(this.registered);
        dest.writeString(this.phone);
        dest.writeString(this.cell);
        dest.writeParcelable(this.picture, flags);
    }

    public Result() {
    }

    protected Result(Parcel in) {
        this.gender = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.email = in.readString();
        this.dob = in.readString();
        this.registered = in.readString();
        this.phone = in.readString();
        this.cell = in.readString();
        this.picture = in.readParcelable(Picture.class.getClassLoader());
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

}
