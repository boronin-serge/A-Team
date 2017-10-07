package com.boronin.a_teams.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by boronin on 07.10.17.
 */

public abstract class AbstractTabFragment extends Fragment {
    private String title;
    protected View view;
    protected Context context;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
