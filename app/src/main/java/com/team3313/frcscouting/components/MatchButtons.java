package com.team3313.frcscouting.components;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;
import com.team3313.frcscouting.fragments.ScoutingFragment;
import com.team3313.frcscouting.fragments.ScoutingMatchFragment;
import com.team3313.frcscouting.fragments.ScoutingNotesFragment;

import org.json.JSONException;

/**
 * Created by 3313 on 3/13/2018.
 */

public class MatchButtons extends LinearLayout {

    public MatchButtons(Context context, final ScoutingFragment fragment) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);
        Button matchButton = new Button(fragment.getActivity());
        matchButton.setText("Match");
        if (fragment instanceof ScoutingNotesFragment) {
            final ScoutingNotesFragment sm = (ScoutingNotesFragment) fragment;
            matchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        sm.data.put("notes", sm.editText.getText());
                    } catch (JSONException e) {
                    }
                    FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, ScoutingMatchFragment.newInstance(sm.data)).commit();
                }
            });
        } else {
            matchButton.setEnabled(false);
        }
        addView(matchButton);
        Button notesButton = new Button(fragment.getActivity());
        notesButton.setText("Notes");
        if (fragment instanceof ScoutingMatchFragment) {
            final ScoutingMatchFragment sm = (ScoutingMatchFragment) fragment;
            notesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        fragment.data.getJSONObject("auto").put("switch", sm.autoSwitchBox.isChecked());
                        fragment.data.getJSONObject("auto").put("scale", sm.autoScaleBox.isChecked());
                        fragment.data.getJSONObject("auto").put("passedLine", sm.autoCrossBox.isChecked());

                        fragment.data.getJSONObject("tele").put("scale", sm.scalePicker.getValue());
                        fragment.data.getJSONObject("tele").put("switch", sm.switchPicker.getValue());
                        fragment.data.getJSONObject("tele").put("exchange", sm.exchangePicker.getValue());
                        fragment.data.getJSONObject("tele").put("climb", sm.teleClimbBox.isChecked());
                    } catch (JSONException e) {
                    }
                    FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, ScoutingNotesFragment.newInstance(fragment.data)).commit();
                }
            });
        } else {
            notesButton.setEnabled(false);
        }
        addView(notesButton);
    }
}
