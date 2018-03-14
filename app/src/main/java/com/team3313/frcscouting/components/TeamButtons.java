package com.team3313.frcscouting.components;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;
import com.team3313.frcscouting.fragments.TeamDataFragment;
import com.team3313.frcscouting.fragments.TeamFragment;
import com.team3313.frcscouting.fragments.TeamMatchesFragment;
import com.team3313.frcscouting.fragments.TeamNotesFragment;
import com.team3313.frcscouting.fragments.TeamPRFragment;

/**
 * Created by oa10712 on 3/13/2018.
 */

public class TeamButtons extends LinearLayout {
    public TeamButtons(Context context, final TeamFragment fragment) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);
        Button dataButton = new Button(getContext());
        dataButton.setText("Data");
        if (fragment instanceof TeamDataFragment) {
            dataButton.setEnabled(false);
        } else {
            dataButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, TeamDataFragment.newInstance(fragment.teamKey)).commit();
                }
            });
        }
        addView(dataButton);
        Button matchesButton = new Button(fragment.getActivity());
        matchesButton.setText("Matches");
        if (fragment instanceof TeamMatchesFragment) {
            matchesButton.setEnabled(false);
        } else {
            matchesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                    TeamMatchesFragment schedule = TeamMatchesFragment.newInstance(fragment.teamKey);
                    fragmentManager.beginTransaction().replace(R.id.content_frame, schedule).commit();
                }
            });
        }
        addView(matchesButton);
        Button notesButton = new Button(fragment.getActivity());
        notesButton.setText("Notes");
        if (fragment instanceof TeamNotesFragment) {
            notesButton.setEnabled(false);
        } else {
            notesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, TeamNotesFragment.newInstance(fragment.teamKey)).commit();
                }
            });
        }
        addView(notesButton);
        Button prButton = new Button(getContext());
        prButton.setText("PR");
        if (fragment instanceof TeamPRFragment) {
            prButton.setEnabled(false);
        } else {
            prButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, TeamPRFragment.newInstance(fragment.teamKey)).commit();
                }
            });
        }
        addView(prButton);
    }
}
