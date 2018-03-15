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
                renderedView = renderQP(team);
                break;
            case 2:
                renderedView = renderAuto(team);
                break;
            case 3:
                renderedView = renderTele(team);
                break;
            case 4:
                renderedView = renderOverall(team);
                break;
        }

        return renderedView;
    }

    private View renderOverall(JSONObject team) {
        TextView view = new TextView(getContext());
        view.setText("Formale Value");
        return view;
    }

    private View renderTele(JSONObject team) {
        TextView view = new TextView(getContext());
        view.setText("Formale Value");
        return view;
    }

    private View renderAuto(JSONObject team) {
        TextView view = new TextView(getContext());
        view.setText("Formale Value");
        return view;
    }

    private View renderQP(JSONObject team) {
        TextView view = new TextView(getContext());
        view.setText("N/A");
        return view;
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
