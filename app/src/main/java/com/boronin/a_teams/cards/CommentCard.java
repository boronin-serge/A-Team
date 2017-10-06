package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Comments;
import com.boronin.a_teams.models.Model;

/**
 * Created by boronin on 06.10.17.
 */

public class CommentCard extends Card {
    private TextView commentContent;

    public CommentCard(CardView cardView) {
        this.cardView = cardView;
        commentContent = cardView.findViewById(R.id.comment);
        model = new Comments();
    }


    @Override
    public void publishResult() {
        commentContent.setText(model.getData());
    }
}
