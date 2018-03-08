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
import android.widget.TextView;

public class ScheduleFragment extends Fragment {
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    ScrollView scrollView;
    TextView textView;
    Button button;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scrollView = new ScrollView(getActivity());
        linearLayout = this.linearLayout();
        relativeLayout = this.relativeLayout();
        textView = this.textView();
        button = this.button();

        // ADD VIEWs TO linearLayout


        linearLayout.addView(textView);
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
        textView.setText(((MainActivity) getActivity()).regional);
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