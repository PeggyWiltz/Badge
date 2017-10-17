package com.behindthebadge.android.badge.screens;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.behindthebadge.android.badge.R;
import com.behindthebadge.android.badge.ResponsesFragment;
import com.behindthebadge.android.badge.SendMessageFragment;

/**
 * Created by us53216 on 4/26/17.
 */

public class TabPageAdapter extends FragmentStatePagerAdapter {

    private final Context _currentContext;

    public TabPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);

        _currentContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new SendMessageFragment();
                break;
            case 1:
                fragment = new ResponsesFragment();
                break;
            default:
                fragment = new SendMessageFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title;

        switch (position) {
            case 0:
                title = _currentContext.getString(R.string.main_message_tab);
                break;
            case 1:
                title = _currentContext.getString(R.string.main_response_tab);
                break;
            default:
                title = _currentContext.getString(R.string.main_message_tab);
        }

        return title;

    }
}
