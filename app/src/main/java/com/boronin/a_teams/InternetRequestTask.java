package com.boronin.a_teams;

import android.os.AsyncTask;
import android.util.Log;

import com.boronin.a_teams.cards.Card;
import com.boronin.a_teams.utilities.NetworkUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

/**
 * Created by boronin on 06.10.17.
 */

public class InternetRequestTask extends AsyncTask<URL, Void, String> {
    Card card;

    public InternetRequestTask(Card card) {
        this.card = card;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL requestUrl = urls[0];
        String result = null;
        try {
            result = NetworkUtils.getResponseFromHttpUrl(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String requestResult) {
        super.onPostExecute(requestResult);

        if (requestResult != null) {
            Gson gson = new Gson();
            card.model = gson.fromJson(requestResult, card.model.getClass());
            card.publishResult();
        }
    }
}
