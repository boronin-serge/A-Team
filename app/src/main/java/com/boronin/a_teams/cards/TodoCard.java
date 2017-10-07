package com.boronin.a_teams.cards;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Todo;

/**
 * Created by boronin on 06.10.17.
 */

public class TodoCard extends Card {
    private TextView todoContent;
    private ProgressBar progressBar;
    private ImageView updateButton;

    public TodoCard(CardView cardView) {
        this.todoContent = cardView.findViewById(R.id.todos);
        progressBar = cardView.findViewById(R.id.todoProgressBar);
        updateButton = cardView.findViewById(R.id.todoUpdateButton);
        model = new Todo();
    }

    @Override
    public void publishResult() {
        progressBar.setVisibility(View.INVISIBLE);
        updateButton.setVisibility(View.VISIBLE);
        todoContent.setText(model.getData());
    }
}
