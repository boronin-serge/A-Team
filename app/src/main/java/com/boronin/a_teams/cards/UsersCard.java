package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Users;

/**
 * Created by boronin on 06.10.17.
 */

public class UsersCard extends Card {
    private String response;
    private TextView userContent;
    private ProgressBar progressBar;
    private ImageView updateButton;
    private int count;

    public UsersCard(CardView cardView, Integer count) {
        this.cardView = cardView;
        this.userContent = cardView.findViewById(R.id.user_content);
        this.count = count;
        response = new String("");
        progressBar = cardView.findViewById(R.id.usersProgressBar);
        updateButton = cardView.findViewById(R.id.usersUpdateButton);
        model = new Users();
    }

    public void publishResult() {
        count--;
        response += model.getData();
        if (count == 0) {
            progressBar.setVisibility(View.INVISIBLE);
            updateButton.setVisibility(View.VISIBLE);
            userContent.setText(response);
            response = "";
        }
        Log.d("result: ", String.valueOf(count));

    }
}
