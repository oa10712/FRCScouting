package com.team3313.frcscouting.components;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by oa10712 on 3/15/2018.
 */

public class TeamTableDataAdapter extends TableDataAdapter<JSONObject> {
    public TeamTableDataAdapter(Context context, JSONObject[] data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        JSONObject team = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderTeamNumber(team);
                break;
            case 1:
                renderedView = renderOverall(team);
                break;
        }

        return renderedView;
    }

    private View renderOverall(JSONObject team) {
        TextView view = new TextView(getContext());
        try {
            double score = getOverallScore(team);
            view.setText(score + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    public static double getOverallScore(JSONObject team) throws JSONException {
        return 10.87 * team.getDouble("scale")
                + 75 * team.getDouble("climb")
                + 9.82 * team.getDouble("exchange")
                + 5.6 * team.getDouble("switch")
                + 200 * team.getDouble("autoScale")
                + 23 * team.getDouble("autoSwitch")
                + 5.4 * team.getDouble("cross");
    }


    private View renderTeamNumber(JSONObject team) {
        TextView view = new TextView(getContext());
        try {
            view.setText(team.getString("number").substring(3));
        } catch (JSONException e) {
        }
        return view;
    }
}
