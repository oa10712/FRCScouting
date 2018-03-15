package com.team3313.frcscouting.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;
import com.team3313.frcscouting.fragments.ScoutingMatchFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oa10712 on 3/14/2018.
 */

public class MatchList extends LinearLayout {
    JSONArray matchList;
    ScrollView scrollView;

    public MatchList(Context context, JSONArray list) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        matchList = list;
        scrollView = new ScrollView(getContext());

        addView(createHeader());
        View matches = createMatches();
        scrollView.addView(matches);
        addView(scrollView);
    }

    private View createMatches() {
        TableLayout matchTable = new TableLayout(getContext());

        matchTable.setColumnStretchable(0, true);
        matchTable.setColumnStretchable(1, true);
        matchTable.setColumnStretchable(2, true);
        matchTable.setColumnStretchable(3, true);
        matchTable.setColumnStretchable(4, true);
        matchTable.setColumnStretchable(5, true);
        matchTable.setColumnStretchable(6, true);
        try {
            if (matchList.length() != 0) {
                matchTable.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                for (int i = 0; i < matchList.length(); i++) {
                    JSONObject match = matchList.getJSONObject(i);
                    JSONArray red = match.getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys");
                    JSONArray blue = match.getJSONObject("alliances").getJSONObject("blue").getJSONArray("team_keys");

                    TableRow matchRow = new TableRow(getContext());
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
                            TextViewExtra match = (TextViewExtra) row.getChildAt(0);
                            TextViewExtra team = null;
                            try {
                                team = (TextViewExtra) row.getChildAt(DataStore.config.getInt("station") + 1);
                            } catch (JSONException e) {
                            }
                            JSONObject start = null;
                            try {
                                start = DataStore.matchData.get(match.data, team.data);
                                if (start == null) {
                                    start = new JSONObject("{\"match_key\":\"" + match.data + "\",\"team_key\":\"" + team.data + "\",\"auto\":{\"passedLine\":false,\"switch\":false,\"scale\":false},\"tele\":{\"switch\":0,\"scale\":0,\"exchange\":0,\"climb\":false},\"notes\":\"\"}");
                                }
                            } catch (JSONException e) {
                            }
                            FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, ScoutingMatchFragment.newInstance(start)).commit();
                        }
                    });

                    TextViewExtra matchName = new TextViewExtra(getContext(), match.getString("key").split("_")[1], match.getString("key"));
                    matchRow.addView(matchName);

                    TextViewExtra red1 = new TextViewExtra(getContext(), red.getString(0).substring(3), red.getString(0));
                    if (DataStore.matchData.get(match.getString("key"), red.getString(0)) != null) {
                        red1.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                    matchRow.addView(red1);

                    TextViewExtra red2 = new TextViewExtra(getContext(), red.getString(1).substring(3), red.getString(1));
                    if (DataStore.matchData.get(match.getString("key"), red.getString(1)) != null) {
                        red2.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                    matchRow.addView(red2);

                    TextViewExtra red3 = new TextViewExtra(getContext(), red.getString(2).substring(3), red.getString(2));
                    if (DataStore.matchData.get(match.getString("key"), red.getString(2)) != null) {
                        red3.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                    matchRow.addView(red3);

                    TextViewExtra blue1 = new TextViewExtra(getContext(), blue.getString(0).substring(3), blue.getString(0));
                    if (DataStore.matchData.get(match.getString("key"), blue.getString(0)) != null) {
                        blue1.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                    matchRow.addView(blue1);

                    TextViewExtra blue2 = new TextViewExtra(getContext(), blue.getString(1).substring(3), blue.getString(1));
                    if (DataStore.matchData.get(match.getString("key"), blue.getString(1)) != null) {
                        blue2.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                    matchRow.addView(blue2);

                    TextViewExtra blue3 = new TextViewExtra(getContext(), blue.getString(2).substring(3), blue.getString(2));
                    if (DataStore.matchData.get(match.getString("key"), blue.getString(2)) != null) {
                        blue3.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                    matchRow.addView(blue3);

                    matchTable.addView(matchRow);
                }
            } else {
                TextView textView = new TextView(getContext());
                textView.setText("Unable to load match list data, list was empty");
                return textView;
            }
        } catch (JSONException e) {
            TextView textView = new TextView(getContext());
            textView.setText("Unable to load match list data, json error");
            return textView;
        }
        return matchTable;
    }

    private TableLayout createHeader() {
        TableLayout header = new TableLayout(getContext());

        header.setColumnStretchable(0, true);
        header.setColumnStretchable(1, true);
        header.setColumnStretchable(2, true);
        header.setColumnStretchable(3, true);
        header.setColumnStretchable(4, true);
        header.setColumnStretchable(5, true);
        header.setColumnStretchable(6, true);

        TableRow headerRow = new TableRow(getContext());
        headerRow.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView matchNameHead = new TextView(getContext());
        matchNameHead.setText("Match ID");
        matchNameHead.setTextColor(Color.WHITE);
        headerRow.addView(matchNameHead);

        TextView red1head = new TextView(getContext());
        red1head.setText("Red 1");
        red1head.setTextColor(Color.WHITE);
        headerRow.addView(red1head);

        TextView red2head = new TextView(getContext());
        red2head.setText("Red 2");
        red2head.setTextColor(Color.WHITE);
        headerRow.addView(red2head);

        TextView red3head = new TextView(getContext());
        red3head.setText("Red 3");
        red3head.setTextColor(Color.WHITE);
        headerRow.addView(red3head);

        TextView blue1head = new TextView(getContext());
        blue1head.setText("Blue 1");
        blue1head.setTextColor(Color.WHITE);
        headerRow.addView(blue1head);

        TextView blue2head = new TextView(getContext());
        blue2head.setText("Blue 2");
        blue2head.setTextColor(Color.WHITE);
        headerRow.addView(blue2head);

        TextView blue3head = new TextView(getContext());
        blue3head.setText("Blue 3");
        blue3head.setTextColor(Color.WHITE);
        headerRow.addView(blue3head);

        header.addView(headerRow);
        return header;
    }
}