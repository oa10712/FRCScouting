package com.team3313.frcscouting.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        MainActivity.instance.setTitle("Team " + teamKey + ", " + DataStore.getTeamField(teamKey, "name", String.class) + " - Data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout buttonRow = new TeamButtons(getContext(), this);
        linearLayout.addView(buttonRow);

        GridLayout gridLayout = new GridLayout(getActivity());
        gridLayout.setRowCount(4);
        gridLayout.setColumnCount(5);

        ImageView botImage = new ImageView(getContext());
        botImage.setImageResource(R.drawable.ic_menu_camera);
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(0, 2);
        params.columnSpec = GridLayout.spec(0, 1);
        botImage.setLayoutParams(params);
        gridLayout.addView(botImage);

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
        scaleVal.setText("##.#");
        gridLayout.addView(scaleVal);

        TextView switchVal = new TextView(getContext());
        switchVal.setText("##.#");
        gridLayout.addView(switchVal);

        TextView exchangeVal = new TextView(getContext());
        exchangeVal.setText("##.#");
        gridLayout.addView(exchangeVal);

        TextView climbVal = new TextView(getContext());
        climbVal.setText("##%");
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
        playVal.setText("##");
        gridLayout.addView(playVal);

        TextView crossVal = new TextView(getContext());
        crossVal.setText("##%");
        gridLayout.addView(crossVal);

        TextView correctVal = new TextView(getContext());
        correctVal.setText("##%");
        gridLayout.addView(correctVal);

        linearLayout.addView(gridLayout);
        return linearLayout;
    }
}
