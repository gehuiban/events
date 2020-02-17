package com.gehuiban.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gehuiban.events.api.EventsUpdater;
import com.gehuiban.events.api.Guide;
import com.gehuiban.events.ui.GuideAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Guide> guidesList;
    private GuideAdapter guideAdapter;
    private TextView pleaseWait;
    private RecyclerView eventsRecyclerView;
    private Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsRecyclerView = findViewById(R.id.events_list);
        eventsRecyclerView.setVisibility(View.GONE);
        pleaseWait = findViewById(R.id.please_wait);
        retry = findViewById(R.id.retry);
        retry.setVisibility(View.GONE);
        pleaseWait.setVisibility(View.VISIBLE);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        guideAdapter = new GuideAdapter();
        eventsRecyclerView.setAdapter(guideAdapter);
        if (!isNetworkAvailable(this)) {
            Toast.makeText(this, "Network not available!", Toast.LENGTH_SHORT).show();
            retry.setVisibility(View.VISIBLE);
            pleaseWait.setVisibility(View.GONE);
        } else {
            getGuides();
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGuides();
            }
        });
    }

    /**
     * @param context is the app context
     * @return if network connection is available or not.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Get Guides from Server and update the view.
     */
    private void getGuides() {
        EventsUpdater updater = new EventsUpdater(new EventsUpdater.OnFindDriveFolder() {
            @Override
            public void onFeedUpdated(ArrayList<Guide> guides) {
                if (guides == null || guides.size() < 1) {
                    retry.setVisibility(View.VISIBLE);
                    return;
                }
                pleaseWait.setVisibility(View.GONE);
                retry.setVisibility(View.GONE);
                eventsRecyclerView.setVisibility(View.VISIBLE);
                guidesList = guides;
                guideAdapter.clearData();
                guideAdapter.addGuides(guidesList);
            }
        });
        updater.execute();
    }
}
