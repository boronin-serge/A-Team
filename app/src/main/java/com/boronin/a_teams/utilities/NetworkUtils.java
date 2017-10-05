package com.boronin.a_teams.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by boronin on 04.10.17.
 */

public class NetworkUtils {

    final static String PREFIX = "https://";
    final static String BASE_URL = "httpbin.org";

    final static String IP_QUERY = "/ip";
    final static String CURRENT_TIME = "now.";  // To paste before BASE_URL
    final static String ECHO = "/anything";

    public static URL buildUrl(String URL) {
        Uri builtUri = Uri.parse(URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildIpUrl() {
        return buildUrl(PREFIX + BASE_URL + IP_QUERY);
    }

    public static URL buildDateUrl() {
        return buildUrl(PREFIX + CURRENT_TIME + BASE_URL);
    }

    public static URL buildEchoUrl(String data) {
        Uri builtUri = Uri.parse(PREFIX + BASE_URL + ECHO)
                .buildUpon()
                .appendQueryParameter("data", data)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
