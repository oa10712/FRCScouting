package com.team3313.frcscouting.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;

/**
 * Created by oa10712 on 3/12/2018.
 */

public class TeamMatchesFragment extends Fragment {
    private static final String ARG_TEAM_KEY = "frc3313";
    LinearLayout linearLayout;
    private String teamKey;

    public TeamMatchesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PRFragment.
     */
    public static TeamMatchesFragment newInstance(String param1) {
        TeamMatchesFragment fragment = new TeamMatchesFragment();
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
        MainActivity.instance.setTitle("Team " + teamKey + ", " + DataStore.getTeamField(teamKey, "name", String.class) + " - Matches");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout buttonRow = new LinearLayout(getActivity());
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        Button dataButton = new Button(getActivity());
        dataButton.setText("Data");
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, TeamDataFragment.newInstance(teamKey)).commit();
            }
        });
        buttonRow.addView(dataButton);
        Button matchesButton = new Button(getActivity());
        matchesButton.setText("Matches");
        matchesButton.setClickable(false);
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
        Button prButton = new Button(getActivity());
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
        return linearLayout;
    }

}
