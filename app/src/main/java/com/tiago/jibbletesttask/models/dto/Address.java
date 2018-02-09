package com.tiago.jibbletesttask.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class Address {
    @Expose
    @SerializedName("geo")
    private Address.Geo geo;
    @Expose
    @SerializedName("zipcode")
    private String zipcode;
    @Expose
    @SerializedName("city")
    private String city;
    @Expose
    @SerializedName("suite")
    private String suite;
    @Expose
    @SerializedName("street")
    private String street;

    public Address.Geo getGeo() {
        return geo;
    }

    public void setGeo(Address.Geo geo) {
        this.geo = geo;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public static class Geo {
        @Expose
        @SerializedName("lng")
        private String lng;
        @Expose
        @SerializedName("lat")
        private String lat;

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}