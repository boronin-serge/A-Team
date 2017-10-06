package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;

import com.boronin.a_teams.models.Model;
import com.google.gson.annotations.SerializedName;

/**
 * Created by boronin on 05.10.17.
 */

public abstract class Card {
    public CardView cardView;
    public Model model;
    public abstract void publishResult();
}
