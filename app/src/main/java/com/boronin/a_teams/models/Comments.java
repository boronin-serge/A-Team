package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public class Comments extends Model {
    @SerializedName("postId")
    public String postId;

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("body")
    public String body;

    public String getData() {
        return  "postId: "  + postId    + "\n\n" +
                "id: "      + id        + "\n\n" +
                "email: "   + email     + "\n\n" +
                "body: "    + body      + "\n\n";
    }

}