package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public class Echo implements JsonObj {
    @SerializedName("args")
    public Data args;

    public String composeString() {
        return "Echo answer: " + args.data;
    }

    class Data {
        @SerializedName("data")
        public String data;
    }
}