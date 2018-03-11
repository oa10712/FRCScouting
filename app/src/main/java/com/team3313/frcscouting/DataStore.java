package com.team3313.frcscouting;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;

/**
 * Created by oa10712 on 3/9/2018.
 */

public class DataStore {
    public static JSONArray matchData;
    public static JSONObject config;
    public static JSONObject teamData;

    static void initLoad() {
        String configString = readFromFile("config.json");
        if (!Objects.equals(configString, "")) {
            try {
                config = new JSONObject(configString);
            } catch (JSONException e) {
                config = new JSONObject();
                e.printStackTrace();
            }
        } else {
            config = new JSONObject();
        }
        String matches = readFromFile("regional-matches.json");
        if (!Objects.equals(matches, "")) {
            try {
                matchData = new JSONArray(matches);
            } catch (JSONException e) {
                e.printStackTrace();
                matchData = new JSONArray();
            }
        } else {
            //this one reads matches from the ndgf regional
            RESTGetter.HttpRequestTask task = new RESTGetter.HttpRequestTask("https://www.team3313.com/api/schedule/read_regional.php?regional=" + MainActivity.instance.getActiveRegional()) {
                @Override
                protected void customEnd(JSONObject r) {
                    try {
                        matchData = r.getJSONArray("records");
                        writeToFile(matchData.toString(), "regional-matches.json");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        matchData = new JSONArray();
                    }
                }
            };
            task.execute();
        }
        String teamString = readFromFile("teams.json");
        if (!Objects.equals(teamString, "")) {
            try {
                teamData = new JSONObject(teamString);
            } catch (JSONException e) {
                teamData = new JSONObject();
                e.printStackTrace();
            }
        } else {
            RESTGetter.HttpRequestTask task = new RESTGetter.HttpRequestTask("https://thebluealliance.com/api/v3/event/" + MainActivity.instance.getActiveRegional() + "/teams/keys") {
                @Override
                protected void customEnd(JSONObject r) {
                    System.out.printf("Writing to log");
                    System.out.println(r.toString());
                }
            };
            task.execute();
        }
        RESTGetter.HttpSubmitTask t = new RESTGetter.HttpSubmitTask("https://www.team3313.com/api/echo.php", matchData.toString()) {
            @Override
            protected void customEnd(String r) {
                System.out.printf("Writing to log: ");
                System.out.println(r);
            }
        };
        t.execute();
    }


    private static void writeToFile(String data, String filename) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(MainActivity.instance.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private static String readFromFile(String filename) {

        String ret = "";

        try {
            InputStream inputStream = MainActivity.instance.openFileInput(filename);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public static void saveConfig() {
        writeToFile(config.toString(), "config.json");
    }

}
