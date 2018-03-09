package com.team3313.frcscouting;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleFragment extends Fragment {
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    ScrollView scrollView;
    TableLayout tableLayout;
    TextView textView;
    Button button;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Schedule for " + ((MainActivity) getActivity()).getActiveRegional());
        scrollView = new ScrollView(getActivity());
        linearLayout = this.linearLayout();
        tableLayout = new TableLayout(getActivity());
        relativeLayout = this.relativeLayout();
        textView = this.textView();
        button = this.button();

        // ADD VIEWs TO linearLayout

        try {
            if (MainActivity.matchData.length() != 0) {
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

                for (int i = 0; i < MainActivity.matchData.length(); i++) {
                    JSONObject match = MainActivity.matchData.getJSONObject(i);

                    TableRow matchRow = new TableRow(getActivity());

                    TextView matchName = new TextView(getActivity());
                    matchName.setText(match.getString("match_id"));
                    matchRow.addView(matchName);
                    matchName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getActivity().setTitle("Schedule for " + ((MainActivity) getActivity()).getActiveRegional() + " : " + ((TextView) v).getText());
                        }
                    });

                    TextView red1 = new TextView(getActivity());
                    red1.setText(match.getString("red1"));
                    matchRow.addView(red1);

                    TextView red2 = new TextView(getActivity());
                    red2.setText(match.getString("red2"));
                    matchRow.addView(red2);

                    TextView red3 = new TextView(getActivity());
                    red3.setText(match.getString("red3"));
                    matchRow.addView(red3);

                    TextView blue1 = new TextView(getActivity());
                    blue1.setText(match.getString("blue1"));
                    matchRow.addView(blue1);

                    TextView blue2 = new TextView(getActivity());
                    blue2.setText(match.getString("blue2"));
                    matchRow.addView(blue2);

                    TextView blue3 = new TextView(getActivity());
                    blue3.setText(match.getString("blue3"));
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
        linearLayout.addView(button);
        linearLayout.addView(relativeLayout);

        scrollView.addView(linearLayout);
        // SET linearLayout AS CONTENT VIEW

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
     * Create button
     *
     * @return
     */
    Button button() {
        Button button = new Button(getActivity());
        button.setText("Button");
        return button;
    }

    /**
     * Create relative layout
     *
     * @return
     */
    RelativeLayout relativeLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());

        // SET THE SIZE
        relativeLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 100));

        // SET BACKGROUND COLOR JUST TO MAKE LAYOUT VISIBLE
        relativeLayout.setBackgroundColor(Color.GREEN);
        return relativeLayout;
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