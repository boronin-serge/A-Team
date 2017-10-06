package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public class Posts extends Model {

    @SerializedName("userId")
    public String userId;

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("body")
    public String body;

    @Override
    public String getData() {
        return  "userId: "  +  userId   + "\n\n" +
                "id: "      + id        + "\n\n" +
                "title: "   + title     + "\n\n" +
                "body: "    + body      + "\n\n";
    }
}