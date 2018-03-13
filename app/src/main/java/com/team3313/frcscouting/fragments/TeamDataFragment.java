package com.team3313.frcscouting.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamDataFragment extends Fragment {
    private static final String ARG_TEAM_KEY = "frc3313";
    LinearLayout linearLayout;
    private String teamKey;


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

        LinearLayout buttonRow = new LinearLayout(getContext());
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        Button dataButton = new Button(getContext());
        dataButton.setText("Data");
        dataButton.setClickable(false);
        buttonRow.addView(dataButton);
        Button matchesButton = new Button(getActivity());
        matchesButton.setText("Matches");
        matchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, TeamMatchesFragment.newInstance(teamKey)).commit();
            }
        });
        buttonRow.addView(matchesButton);
        Button notesButton = new Button(getActivity());
        notesButton.setText("Notes");
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, TeamNotesFragment.newInstance(teamKey)).commit();
            }
        });
        buttonRow.addView(notesButton);
        Button prButton = new Button(getContext());
        prButton.setText("PR");
        prButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, PRFragment.newInstance(teamKey)).commit();
            }
        });
        buttonRow.addView(prButton);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
