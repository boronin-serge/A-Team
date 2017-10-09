package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Posts;

/**
 * Created by boronin on 06.10.17.
 */

public class PostCard extends Card {
    private TextView postContent;
    private ProgressBar progressBar;

    public PostCard(CardView cardView) {
        this.cardView = cardView;
        postContent = cardView.findViewById(R.id.post_response);
        progressBar = cardView.findViewById(R.id.postProgressBar);
        model = new Posts();
    }

    public void setContentText(String content) {
        this.postContent.setText(content);
    }

    @Override
    public void publishResult() {
        postContent.setText(model.getData());
        progressBar.setVisibility(View.INVISIBLE);
    }
}
