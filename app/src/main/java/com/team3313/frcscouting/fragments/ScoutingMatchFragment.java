package com.team3313.frcscouting.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.team3313.frcscouting.MainActivity;
import com.team3313.frcscouting.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoutingMatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoutingMatchFragment extends Fragment {

    LinearLayout linearLayout;
    // TODO: Rename and change types of parameters
    private JSONObject data;


    public ScoutingMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param start Parameter 1.
     * @return A new instance of fragment ScoutingMatchFragment.
     */
    public static Fragment newInstance(JSONObject start) {
        ScoutingMatchFragment fragment = new ScoutingMatchFragment();
        fragment.data = start;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.instance.setTitle("Match " + getData("match_key", String.class) + " - Watching " + getData("team_key", String.class));
        linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout buttonRow = new LinearLayout(getActivity());
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        Button matchButton = new Button(getActivity());
        matchButton.setText("Match");
        matchButton.setClickable(false);
        buttonRow.addView(matchButton);
        Button notesButton = new Button(getActivity());
        notesButton.setText("Notes");
        notesButton.setClickable(true);
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.instance.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, ScoutingNotesFragment.newInstance(data)).commit();
            }
        });
        buttonRow.addView(notesButton);
        linearLayout.addView(buttonRow);
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
