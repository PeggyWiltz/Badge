package com.behindthebadge.android.badge.screens;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.behindthebadge.android.badge.R;
import com.behindthebadge.android.badge.dbHelper.DatabaseHelper;
import com.behindthebadge.android.badge.dbHelper.Event;
import com.behindthebadge.android.badge.dbHelper.Responder;
import com.behindthebadge.android.badge.dbHelper.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    DatabaseHelper db;

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

        db = new DatabaseHelper(getApplicationContext());

        // Creating events
        Event event1 = new Event("Wyoming", "2/15/2018");
        Event event2 = new Event("Chicago", "3/22/2018");
        Event event3 = new Event("Houston", "5/25/2018");
        Event event4 = new Event("Florida", "7/15/2018");

        // Inserting tags in db
        long event1_id = db.createEvent(event1);
        long event2_id = db.createEvent(event2);
        long event3_id = db.createEvent(event3);
        long event4_id = db.createEvent(event4);

        Log.d("Event Count", "Event Count: " + db.getAllEvents().size());

        Log.d("Get Events", "Getting All Events");
        List<Event> allEvents = db.getAllEvents();
        for(Event event : allEvents) {
            Log.d("Events", event.getName() + " " + event.getDate());
        }

        // Creating responders
        Responder resp1 = new Responder("Steve Dallas", "425-555-1212");
        Responder resp2 = new Responder("Milo Blom", "425-555-2323");
        Responder resp3 = new Responder("Opus", "425-555-3434");
        Responder resp4 = new Responder("Roscoe P. Coltrane", "425-555-5656");

        // Inserting responders in db
        long resp1_id = db.createResponder(resp1);
        long resp2_id = db.createResponder(resp2);
        long resp3_id = db.createResponder(resp3);
        long resp4_id = db.createResponder(resp4);

        Log.e("Responder Count", "Responder count: " + db.getAllResponders().size());

        // Getting all responder names
        Log.d("Get Responders", "Getting All Responders");

        List<Responder> allResponders = db.getAllResponders();
        for (Responder responder : allResponders) {
            Log.d("Responder: ", responder.getName() + " " + responder.getPhone());
        }

        //Creating responses
        Response r1 = new Response();
        Response r2 = new Response();
        Response r3 = new Response();
        Response r4 = new Response();

        //Inserting responses into db
        long r1_id = db.createResponse(r1, resp1_id);
        long r2_id = db.createResponse(r1, resp2_id);
        long r3_id = db.createRes

        // Don't forget to close database connection
        db.closeDB();
    }


}
