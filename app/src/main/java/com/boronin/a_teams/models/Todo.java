package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 06.10.17.
 */

public class Todo extends Model {

    @SerializedName("userId")
    public String userId;

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("completed")
    public String completed;

    @Override
    public String getData() {
        return  "userId: "      + userId    + "\n\n" +
                "id: "          + id        + "\n\n" +
                "title: "       + title     + "\n\n" +
                "completed: "   + completed + "\n\n";
    }
}
