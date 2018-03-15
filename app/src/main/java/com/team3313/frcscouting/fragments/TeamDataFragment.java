package com.team3313.frcscouting.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;
import com.team3313.frcscouting.components.TeamButtons;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamDataFragment extends TeamFragment {
    private static final String ARG_TEAM_KEY = "frc3313";
    LinearLayout linearLayout;


    public TeamDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TeamDataFragment.
     */
    public static TeamDataFragment newInstance(String param1) {
        TeamDataFragment fragment = new TeamDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEAM_KEY, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamKey = getArguments().getString(ARG_TEAM_KEY);
        }
        MainActivity.instance.setTitle("Team " + teamKey + ", " + DataStore.getTeamField(teamKey, "name") + " - Data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DataStore.updateTeamStats(teamKey);
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout buttonRow = new TeamButtons(getContext(), this);
        linearLayout.addView(buttonRow);

        GridLayout gridLayout = new GridLayout(getActivity());
        gridLayout.setRowCount(4);
        gridLayout.setColumnCount(5);

        GridLayout.LayoutParams defPars = new GridLayout.LayoutParams();
        defPars.setGravity(Gravity.BOTTOM);

        ImageView botImage = new ImageView(getContext());
        botImage.setImageResource(R.drawable.ic_menu_camera);
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(0, 2);
        params.columnSpec = GridLayout.spec(0, 1);
        params.setGravity(Gravity.BOTTOM);
        botImage.setLayoutParams(params);
        gridLayout.addView(botImage);


        JSONObject teamInfo = null;
        try {
            teamInfo = DataStore.teamData.getJSONObject(teamKey);
        } catch (JSONException e) {
        }

        TextView sclLb = new TextView(getContext());
        sclLb.setText("Scale/g");
        gridLayout.addView(sclLb);

        TextView schlLb = new TextView(getContext());
        schlLb.setText("Switch/g");
        gridLayout.addView(schlLb);

        TextView exchlb = new TextView(getContext());
        exchlb.setText("Exchange/g");
        gridLayout.addView(exchlb);

        TextView climbLb = new TextView(getContext());
        climbLb.setText("% Climb");
        gridLayout.addView(climbLb);

        TextView scaleVal = new TextView(getContext());
        try {
            scaleVal.setText(teamInfo.getDouble("scale") + "");
        } catch (JSONException e) {
            scaleVal.setText("0");
        }
        gridLayout.addView(scaleVal);

        TextView switchVal = new TextView(getContext());
        try {
            switchVal.setText(teamInfo.getDouble("switch") + "");
        } catch (JSONException e) {
            switchVal.setText("0");
        }
        gridLayout.addView(switchVal);

        TextView exchangeVal = new TextView(getContext());
        try {
            exchangeVal.setText(teamInfo.getDouble("exchange") + "");
        } catch (JSONException e) {
            exchangeVal.setText("0");
        }
        gridLayout.addView(exchangeVal);

        TextView climbVal = new TextView(getContext());
        try {
            climbVal.setText(teamInfo.getDouble("climb")*100 + "%");
        } catch (JSONException e) {
            climbVal.setText("0%");
        }
        gridLayout.addView(climbVal);

        TextView playLb = new TextView(getContext());
        playLb.setText("# Played");
        gridLayout.addView(playLb);

        TextView crossLb = new TextView(getContext());
        crossLb.setText("% Cross");
        gridLayout.addView(crossLb);

        TextView correctLb = new TextView(getContext());
        correctLb.setText("% Correct");
        gridLayout.addView(correctLb);

        gridLayout.addView(new Space(getContext()));

        gridLayout.addView(new Space(getContext()));

        TextView playVal = new TextView(getContext());
        try {
            playVal.setText(teamInfo.getInt("played") + "");
        } catch (JSONException e) {
            playVal.setText("0");
        }
        gridLayout.addView(playVal);

        TextView crossVal = new TextView(getContext());
        try {
            crossVal.setText(teamInfo.getDouble("cross")*100 + "%");
        } catch (JSONException e) {
            crossVal.setText("0%");
        }
        gridLayout.addView(crossVal);

        TextView correctVal = new TextView(getContext());
        try {
            correctVal.setText(teamInfo.getDouble("autoSwitch") + "%");
        } catch (JSONException e) {
            correctVal.setText("0%");
        }
        gridLayout.addView(correctVal);

        linearLayout.addView(gridLayout);
        return linearLayout;
    }
}
