package com.boronin.a_teams.models;

import com.boronin.a_teams.models.Model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 07.10.17.
 */

public class Photo extends Model {

    @SerializedName("albumId")
    public String albumId;

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;

    @Override
    public String getData() {
        return url;
    }
}
