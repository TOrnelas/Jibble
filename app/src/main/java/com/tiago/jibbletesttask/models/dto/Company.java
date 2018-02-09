package com.tiago.jibbletesttask.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class Company {

    @Expose
    @SerializedName("bs")
    private String bs;
    @Expose
    @SerializedName("catchPhrase")
    private String catchphrase;
    @Expose
    @SerializedName("name")
    private String name;

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public void setCatchphrase(String catchphrase) {
        this.catchphrase = catchphrase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}