package com.team3313.frcscouting.fragments;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
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
import com.team3313.frcscouting.R;

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
                header.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                TextView matchNameHead = new TextView(getActivity());
                matchNameHead.setText("Match ID");
                matchNameHead.setTextColor(Color.WHITE);
                header.addView(matchNameHead);

                TextView red1head = new TextView(getActivity());
                red1head.setText("Red 1");
                red1head.setTextColor(Color.WHITE);
                header.addView(red1head);

                TextView red2head = new TextView(getActivity());
                red2head.setText("Red 2");
                red2head.setTextColor(Color.WHITE);
                header.addView(red2head);

                TextView red3head = new TextView(getActivity());
                red3head.setText("Red 3");
                red3head.setTextColor(Color.WHITE);
                header.addView(red3head);

                TextView blue1head = new TextView(getActivity());
                blue1head.setText("Blue 1");
                blue1head.setTextColor(Color.WHITE);
                header.addView(blue1head);

                TextView blue2head = new TextView(getActivity());
                blue2head.setText("Blue 2");
                blue2head.setTextColor(Color.WHITE);
                header.addView(blue2head);

                TextView blue3head = new TextView(getActivity());
                blue3head.setText("Blue 3");
                blue3head.setTextColor(Color.WHITE);
                header.addView(blue3head);

                tableLayout.addView(header);

                for (int i = 0; i < DataStore.matchData.length(); i++) {
                    JSONObject match = DataStore.matchData.getJSONObject(i);
                    JSONArray red = match.getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys");
                    JSONArray blue = match.getJSONObject("alliances").getJSONObject("blue").getJSONArray("team_keys");

                    TableRow matchRow = new TableRow(getActivity());
                    if (i % 2 == 0) {
                        matchRow.setBackgroundColor(getResources().getColor(R.color.colorAltRow));
                    }
                    matchRow.setClickable(true);
                    matchRow.setMinimumHeight(75);
                    matchRow.setGravity(Gravity.CENTER_VERTICAL);
                    matchRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TableRow row = (TableRow) v;
                            TextView match = (TextView) row.getChildAt(0);
                            TextView team = null;
                            try {
                                team = (TextView) row.getChildAt(DataStore.config.getInt("station") + 1);
                            } catch (JSONException e) {
                            }
                            JSONObject start = null;
                            try {
                                start = new JSONObject("{\"match_key\":\"" + match.getText() + "\",\"team_key\":\"" + team.getText() + "\",\"auto\":{\"passedLine\":false,\"switch\":false,\"scale\":false},\"tele\":{\"switch\":0,\"scale\":0,\"exchange\":0,\"climb\":false},\"notes\":\"\"}");
                            } catch (JSONException e) {
                            }
                            FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, ScoutingMatchFragment.newInstance(start)).commit();
                        }
                    });

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