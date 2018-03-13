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
    public static JSONObject teamData = new JSONObject();

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
        if (matches != "") {
            try {
                matchData = new JSONArray(matches);
            } catch (JSONException e) {
                e.printStackTrace();
                matchData = new JSONArray();
            }
        } else {
            //this one reads matches from the ndgf regional
            RESTGetter.HttpsRequestTaskArray task = new RESTGetter.HttpsRequestTaskArray("https://www.team3313.com/regional/" + MainActivity.instance.getActiveRegional() + "/schedule") {
                @Override
                protected void customEnd(JSONArray r) {
                    matchData = r;
                    writeToFile(matchData.toString(), "regional-matches.json");
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
            RESTGetter.HttpRequestTaskArray task = new RESTGetter.HttpRequestTaskArray("https://www.team3313.com/regional/" + MainActivity.instance.getActiveRegional() + "/teams") {
                @Override
                protected void customEnd(JSONArray r) {
                    System.out.println("Teams.json was empty");
                    System.out.println(r.toString());
                }
            };
            task.execute("X-TBA-Auth-Key:IdxoRao9PllsmPXPcOq9lcNU3o3zQAN6Tg3gflC9VCw1Wvj4pfqzV1Gmfiks0T9o");
        }

/*        RESTGetter.HttpsSubmitTask t = new RESTGetter.HttpsSubmitTask("https://team3313.com/scouting/pit/frc3313", "{\n" +
                "  \"social\": [\n" +
                "    {\n" +
                "      \"site\": \"team3313.com\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"site\": \"Twitter\",\n" +
                "      \"handle\": \"@team3313\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"awards\": {\n" +
                "    \"chairmans\": true,\n" +
                "    \"woodie\": false,\n" +
                "    \"deans\": false\n" +
                "  }\n" +
                "}") {

            @Override
            protected void customEnd(String r) {
                System.out.println(r);
                Toast.makeText(MainActivity.instance, r, Toast.LENGTH_LONG).show();
            }
        };
        t.execute("Authentication:yT^#N*X#I&XNFfin3 fGISfmeygfai8mfgm6i*64m8I6GMO863I8");*/
        RESTGetter.HttpRequestTask r = new RESTGetter.HttpRequestTask("https://team3313.com/teams/frc33313") {
            @Override
            protected void customEnd(JSONObject r) {
                System.out.printf("Writing to log");
                System.out.println(r.toString());
            }
        };

        r.execute("Authentication:yT^#N*X#I&XNFfin3 fGISfmeygfai8mfgm6i*64m8I6GMO863I8");
    }


    public static void manualRefresh() {
        //this one reads matches from the ndgf regional
        RESTGetter.HttpsRequestTaskArray task = new RESTGetter.HttpsRequestTaskArray("https://www.team3313.com/regional/" + MainActivity.instance.getActiveRegional() + "/schedule") {
            @Override
            protected void customEnd(JSONArray r) {
                matchData = r;
                writeToFile(matchData.toString(), "regional-matches.json");
            }
        };
        task.execute();

        task = new RESTGetter.HttpsRequestTaskArray("https://www.team3313.com/regional/" + MainActivity.instance.getActiveRegional() + "/teams") {
            @Override
            protected void customEnd(final JSONArray r) {
                System.out.println("Teams.json was empty");
                for (int i = 0; i < r.length(); i++) {


                    RESTGetter.HttpsRequestTask team = null;
                    try {
                        final int finalI = i;
                        team = new RESTGetter.HttpsRequestTask("https://team3313.com/teams/" + r.getString(finalI)) {
                            @Override
                            protected void customEnd(JSONObject ret) {
                                try {
                                    teamData.put(r.getString(finalI), ret);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    team.execute("Authentication:yT^#N*X#I&XNFfin3 fGISfmeygfai8mfgm6i*64m8I6GMO863I8");
                }
                System.out.println(teamData.toString());
            }
        };
        task.execute("X-TBA-Auth-Key:IdxoRao9PllsmPXPcOq9lcNU3o3zQAN6Tg3gflC9VCw1Wvj4pfqzV1Gmfiks0T9o");
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

    public static String getTeamField(String teamKey, String key, Class clazz) {
        try {
            if (clazz == String.class) {
                return teamData.getJSONObject(teamKey).getString(key);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "N/A";
    }
}
