package com.team3313.frcscouting;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PitScoutingFragment extends Fragment {

    public PitScoutingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scouting_pit, container, false);
    }

}