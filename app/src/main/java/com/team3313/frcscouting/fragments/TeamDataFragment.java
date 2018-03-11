package com.team3313.frcscouting.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team3313.frcscouting.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamDataFragment extends Fragment {
    private static final String ARG_TEAM_KEY = "frc3313";

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
        MainActivity.instance.setTitle("Team #" + teamKey + " - ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());

        textView.setText(teamKey);
        return textView;
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
