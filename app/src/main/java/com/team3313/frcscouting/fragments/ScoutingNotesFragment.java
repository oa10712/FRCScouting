package com.team3313.frcscouting.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutingNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutingNotesFragment extends Fragment {
    JSONObject data;

    LinearLayout linearLayout;
    EditText editText;


    public ScoutingNotesFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScoutingNotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoutingNotesFragment newInstance(JSONObject start) {
        ScoutingNotesFragment fragment = new ScoutingNotesFragment();
        fragment.data = start;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.instance.setTitle("Notes for " + getData("match_key", String.class) + " - Watching " + getData("team_key", String.class));
        linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout buttonRow = new LinearLayout(getActivity());
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        Button matchButton = new Button(getActivity());
        matchButton.setText("Match");
        matchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    data.put("notes", editText.getText());
                } catch (JSONException e) {
                }
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, ScoutingMatchFragment.newInstance(data)).commit();
            }
        });
        buttonRow.addView(matchButton);
        Button notesButton = new Button(getActivity());
        notesButton.setText("Notes");
        notesButton.setEnabled(false);
        buttonRow.addView(notesButton);
        linearLayout.addView(buttonRow);

        editText = new EditText(getContext());

        try {
            editText.setText(data.getString("notes"));
        } catch (JSONException e) {
        }

        linearLayout.addView(editText);
        return linearLayout;
    }

    private <T> T getData(String key, Class<T> clazz) {
        try {
            return (T) data.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
