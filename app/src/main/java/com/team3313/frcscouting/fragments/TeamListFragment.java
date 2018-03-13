package com.team3313.frcscouting.fragments;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;

import org.json.JSONArray;
import org.json.JSONException;


public class TeamListFragment extends Fragment {
    private final static int COLUMNS = 5;
    LinearLayout topLayout;
    View.OnClickListener teamClick;

    public TeamListFragment() {
        teamClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, TeamDataFragment.newInstance((String) ((TextView) v).getText())).commit();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        topLayout = new LinearLayout(getContext());

        TableLayout tableLayout = new TableLayout(getContext());
        DataStore.teamData.length();
        int r = (int) Math.ceil(DataStore.teamData.length() / COLUMNS);
        JSONArray names = DataStore.teamData.names();
        for (int i = 0; i < COLUMNS; i++) {
            tableLayout.setColumnStretchable(i, true);
        }
        for (int i = 0; i < r + 1; i++) {
            TableRow row = new TableRow(getContext());

            for (int c = 0; c < COLUMNS; c++) {
                try {
                    TextView view = new TextView(getContext());
                    view.setText(DataStore.teamData.getJSONObject(names.getString(i * COLUMNS + c)).getString("number"));
                    view.setMinHeight(50);
                    view.setOnClickListener(teamClick);
                    row.addView(view);
                } catch (Exception e) {
                }
            }
            tableLayout.addView(row);
        }
        topLayout.addView(tableLayout);
        return topLayout;
    }
}