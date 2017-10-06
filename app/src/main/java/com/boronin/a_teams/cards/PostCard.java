package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Model;
import com.boronin.a_teams.models.Posts;

/**
 * Created by boronin on 06.10.17.
 */

public class PostCard extends Card {
    private TextView postContent;

    public PostCard(CardView cardView) {
        this.cardView = cardView;
        postContent = cardView.findViewById(R.id.post);
        model = new Posts();
    }


    @Override
    public void publishResult() {
        postContent.setText(model.getData());
    }
}
