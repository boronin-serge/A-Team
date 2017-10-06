package com.boronin.a_teams.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public class Users extends Model {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("userName")
    public String userName;

    @SerializedName("email")
    public String email;

    @SerializedName("address")
    public Adress address;

    @SerializedName("phone")
    public String phone;

    @SerializedName("company")
    public Company company;

    private class Company {
        @SerializedName("name")
        public String name;

        @SerializedName("catchPhrase")
        public String catchPhrase;

        @SerializedName("bs")
        public String bs;
    }

    private class Adress {
        @SerializedName("street")
        public String street;

        @SerializedName("suite")
        public String suite;

        @SerializedName("city")
        public String city;

        @SerializedName("zipcode")
        public String zipcode;

        @SerializedName("geo")
        public Geo geo;

        private class Geo {
            @SerializedName("lat")
            public String lat;

            @SerializedName("lng")
            public String lng;
        }
    }

    @Override
    public String getData() {
        return  "User "         + id        + ": \n\n" +
                "name: "        + name      + "\n" +
                "userName: "    + userName  + "\n" +
                "email: "       + email     + "\n\n";
    }


}