package com.team3313.frcscouting.fragments;

/**
 * Created by oa10712 on 3/7/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team3313.frcscouting.DataStore;
import com.team3313.frcscouting.components.TeamTableDataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class RankingFragment extends Fragment {
    private static final String[] TABLE_HEADERS = {"Team Number", "Qualification Points", "Auto Formula", "Tele Formula", "Overall Formula"};
    SortableTableView<JSONObject> table;

    public RankingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        JSONArray names = DataStore.teamData.names();
        JSONObject[] teams = new JSONObject[names.length()];
        for (int i = 0; i < names.length(); i++) {
            try {
                DataStore.updateTeamStats(names.getString(i));
                teams[i] = DataStore.teamData.getJSONObject(names.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        table = new SortableTableView<JSONObject>(getContext());
        table.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));
        table.setColumnCount(5);
        table.setDataAdapter(new TeamTableDataAdapter(getContext(), teams));
        table.setColumnComparator(0, new TeamNumberComparator());

        return table;
    }


    public static class TeamNumberComparator implements java.util.Comparator<JSONObject> {
        @Override
        public int compare(JSONObject o1, JSONObject o2) {
            try {
                int first = Integer.decode(o1.getString("number").substring(3));
                int second = Integer.decode(o2.getString("number").substring(3));
                return first - second;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}