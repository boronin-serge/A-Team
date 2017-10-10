package com.boronin.a_teams.cards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.boronin.a_teams.DownloadImageTask;
import com.boronin.a_teams.R;
import com.boronin.a_teams.models.Photo;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by boronin on 07.10.17.
 */

public class PhotoCard extends Card {
    private ImageView photoContent;
    private ProgressBar progressBar;
    private ImageView updateButton;

    public PhotoCard(CardView cardView) {
        this.cardView = cardView;
        photoContent = cardView.findViewById(R.id.photo);
        progressBar = cardView.findViewById(R.id.photoProgressBar);
        updateButton = cardView.findViewById(R.id.photoUpdateButton);
        model = new Photo();
    }

    @Override
    public void publishResult() {
        String imageUrl = model.getData();
        new DownloadImageTask(photoContent).execute(imageUrl);
        progressBar.setVisibility(View.INVISIBLE);
        updateButton.setVisibility(View.VISIBLE);
    }
}
