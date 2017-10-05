package com.boronin.a_teams;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boronin.a_teams.models.Date;
import com.boronin.a_teams.models.Echo;
import com.boronin.a_teams.models.Ip;
import com.boronin.a_teams.models.JsonObj;
import com.boronin.a_teams.utilities.NetworkUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView ipTextView;
    TextView dateTextView;
    TextView echoTextView;

    EditText editEcho;

    Button sendEchoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipTextView = (TextView) findViewById(R.id.tv_ip);
        dateTextView = (TextView) findViewById(R.id.tv_date);
        echoTextView = (TextView) findViewById(R.id.tv_echo);

        editEcho = (EditText) findViewById(R.id.et_echo);

        sendEchoButton = (Button) findViewById(R.id.sendEchoRequest);

        makeRequests();
    }

    private void makeRequests() {
        URL ipUrl = NetworkUtils.buildIpUrl();
        URL dateUrl = NetworkUtils.buildDateUrl();

        Ip   ipPOJO = new Ip();
        Date datePOJO = new Date();

        new InternetRequestTask(ipTextView, ipPOJO).execute(ipUrl);
        new InternetRequestTask(dateTextView, datePOJO).execute(dateUrl);
    }

    public void sendEcho(View view) {
        String requestData = editEcho.getText().toString();
        URL echoUrl = NetworkUtils.buildEchoUrl(requestData);
        Echo echoPOJO = new Echo();
        new InternetRequestTask(echoTextView, echoPOJO).execute(echoUrl);
    }


    public class InternetRequestTask extends AsyncTask<URL, Void, String> {
        TextView tv;
        JsonObj jsonObj;

        public InternetRequestTask(TextView tv, JsonObj jsonObj) {
            this.tv = tv;
            this.jsonObj = jsonObj;
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL requestUrl = urls[0];
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(requestUrl);
                Log.d("request result", result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String requestResult) {
            super.onPostExecute(requestResult);

            if (!requestResult.equals("") && tv != null) {
                Gson gson = new Gson();
                jsonObj = gson.fromJson(requestResult, jsonObj.getClass());
                tv.setText(jsonObj.composeString());
            }
        }
    }
}
