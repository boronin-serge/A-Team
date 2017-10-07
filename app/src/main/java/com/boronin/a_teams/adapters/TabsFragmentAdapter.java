package com.boronin.a_teams.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.boronin.a_teams.fragments.AbstractTabFragment;
import com.boronin.a_teams.fragments.CardsFragment;
import com.boronin.a_teams.fragments.ContactsFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boronin on 07.10.17.
 */

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }


    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();

        tabs.put(0, CardsFragment.getInstance(context));
        tabs.put(1, ContactsFragment.getInstance(context));
    }
}