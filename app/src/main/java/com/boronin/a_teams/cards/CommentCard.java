package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Comments;

/**
 * Created by boronin on 06.10.17.
 */

public class CommentCard extends Card {
    private TextView commentContent;
    private ProgressBar progressBar;

    public CommentCard(CardView cardView) {
        this.cardView = cardView;
        commentContent = cardView.findViewById(R.id.comment_response);
        progressBar = cardView.findViewById(R.id.commentsProgressBar);
        model = new Comments();
    }

    public void setContentText(String content) {
        this.commentContent.setText(content);
    }

    @Override
    public void publishResult() {
        commentContent.setText(model.getData());
        progressBar.setVisibility(View.INVISIBLE);
    }
}
