package com.team3313.frcscouting.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team3313.frcscouting.R;

/**
 * Created by oa10712 on 3/20/2018.
 */

public class StartupFragment extends Fragment {
    LinearLayout top;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        top = new LinearLayout(getActivity());

        TextView info = new TextView(getContext());
        info.setText(R.string.startup_info);

        top.addView(info);
        return top;
    }
}
