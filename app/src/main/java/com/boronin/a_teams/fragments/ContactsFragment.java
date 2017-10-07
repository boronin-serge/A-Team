package com.boronin.a_teams.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boronin.a_teams.R;

/**
 * Created by boronin on 07.10.17.
 */

public class ContactsFragment extends AbstractTabFragment {
    public static final int LAYOUT = R.layout.contacts_fragment;

    public static ContactsFragment getInstance(Context context) {
        ContactsFragment fragment = new ContactsFragment();
        fragment.setTitle(context.getString(R.string.contacts_tab_label));
        fragment.context = context;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
