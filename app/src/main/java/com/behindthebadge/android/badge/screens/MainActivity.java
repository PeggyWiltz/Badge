package com.behindthebadge.android.badge.screens;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.behindthebadge.android.badge.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);

        TabPageAdapter pageAdapter = new TabPageAdapter(this, getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.main_message_tab);
        tabLayout.getTabAt(1).setText(R.string.main_response_tab);

        viewPager.setAdapter(pageAdapter);
    }

}
