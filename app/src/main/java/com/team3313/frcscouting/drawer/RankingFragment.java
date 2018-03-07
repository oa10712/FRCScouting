package com.team3313.frcscouting.drawer;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team3313.frcscouting.R;

public class RankingFragment extends Fragment {

    public RankingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);

        return rootView;
    }

}