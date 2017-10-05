package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public class Ip implements JsonObj {
    @SerializedName("origin")
    public String ip;

    public String composeString() {
        return "Your IP: " + ip;
    }
}
