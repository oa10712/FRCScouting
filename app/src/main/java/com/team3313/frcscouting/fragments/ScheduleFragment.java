package com.team3313.frcscouting.fragments;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleFragment extends Fragment {
    LinearLayout linearLayout;
    ScrollView scrollView;
    TableLayout tableLayout;
    TextView textView;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Schedule for " + ((MainActivity) getActivity()).getActiveRegional());
        scrollView = new ScrollView(getActivity());
        linearLayout = this.linearLayout();
        tableLayout = new TableLayout(getActivity());
        tableLayout.setColumnStretchable(0, true);
        tableLayout.setColumnStretchable(1, true);
        tableLayout.setColumnStretchable(2, true);
        tableLayout.setColumnStretchable(3, true);
        tableLayout.setColumnStretchable(4, true);
        tableLayout.setColumnStretchable(5, true);
        tableLayout.setColumnStretchable(6, true);
        textView = this.textView();

        // ADD VIEWs TO topLayout

        try {
            if (DataStore.matchData.length() != 0) {
                tableLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                TableRow header = new TableRow(getActivity());

                TextView matchNameHead = new TextView(getActivity());
                matchNameHead.setText("Match ID");
                header.addView(matchNameHead);

                TextView red1head = new TextView(getActivity());
                red1head.setText("Red 1");
                header.addView(red1head);

                TextView red2head = new TextView(getActivity());
                red2head.setText("Red 2");
                header.addView(red2head);

                TextView red3head = new TextView(getActivity());
                red3head.setText("Red 3");
                header.addView(red3head);

                TextView blue1head = new TextView(getActivity());
                blue1head.setText("Blue 1");
                header.addView(blue1head);

                TextView blue2head = new TextView(getActivity());
                blue2head.setText("Blue 2");
                header.addView(blue2head);

                TextView blue3head = new TextView(getActivity());
                blue3head.setText("Blue 3");
                header.addView(blue3head);

                tableLayout.addView(header);

                for (int i = 0; i < DataStore.matchData.length(); i++) {
                    JSONObject match = DataStore.matchData.getJSONObject(i);
                    JSONArray red = match.getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys");
                    JSONArray blue = match.getJSONObject("alliances").getJSONObject("blue").getJSONArray("team_keys");

                    TableRow matchRow = new TableRow(getActivity());
                    matchRow.setMinimumHeight(75);

                    TextView matchName = new TextView(getActivity());
                    matchName.setText(match.getString("key"));
                    matchRow.addView(matchName);

                    TextView red1 = new TextView(getActivity());
                    red1.setText(red.getString(0));
                    matchRow.addView(red1);

                    TextView red2 = new TextView(getActivity());
                    red2.setText(red.getString(1));
                    matchRow.addView(red2);

                    TextView red3 = new TextView(getActivity());
                    red3.setText(red.getString(2));
                    matchRow.addView(red3);

                    TextView blue1 = new TextView(getActivity());
                    blue1.setText(blue.getString(0));
                    matchRow.addView(blue1);

                    TextView blue2 = new TextView(getActivity());
                    blue2.setText(blue.getString(1));
                    matchRow.addView(blue2);

                    TextView blue3 = new TextView(getActivity());
                    blue3.setText(blue.getString(2));
                    matchRow.addView(blue3);

                    tableLayout.addView(matchRow);
                }
                linearLayout.addView(tableLayout);
            } else {
                linearLayout.addView(textView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            linearLayout.addView(textView);
        }

        scrollView.addView(linearLayout);
        // SET topLayout AS CONTENT VIEW

        return scrollView;
    }

    /*
     * Create textview
     */
    TextView textView() {
        TextView textView = new TextView(getActivity());
        textView.setText("Match data did not load");
        return textView;
    }

    /**
     * Create linear layout
     *
     * @return
     */
    LinearLayout linearLayout() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }
}