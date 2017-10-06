package com.boronin.a_teams.utilities;

import android.net.Uri;
import android.util.Log;

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

    final static String BASE_URL    = "https://jsonplaceholder.typicode.com";

    final static String POSTS       = "/posts/";
    final static String COMMENTS    = "/comments/";
    final static String USERS       = "/users/";
    final static String PHOTOS      = "/photos/";
    final static String TODOS       = "/todos/";

    public static URL buildUrl(String URL) {
        Uri builtUri = Uri.parse(URL);

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildPostsUrl(String n) {
        return buildUrl(BASE_URL + POSTS + n);
    }

    public static URL buildCommentsUrl(String n) {
        return buildUrl(BASE_URL + COMMENTS + n);
    }

    public static URL buildUsersUrl(String n) {
        return buildUrl(BASE_URL + USERS + n);
    }

    public static URL buildPhotosUrl(String n) {
        return buildUrl(BASE_URL + PHOTOS + n);
    }

    public static URL buildTodosUrl(String n) {
        return buildUrl(BASE_URL + TODOS + n);
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
