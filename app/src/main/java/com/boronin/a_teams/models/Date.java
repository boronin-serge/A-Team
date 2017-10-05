package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public class Date implements JsonObj {
    @SerializedName("now")
    public SubDate nowObj;

    public String composeString() {
        return "Time of request: " + nowObj.time;
    }

    class SubDate {
        @SerializedName("rfc2822")
        public String time;
    }
}

