package com.team3313.frcscouting;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by oa10712 on 3/8/2018.
 */

class RESTGetter {

    /**
     * Converts the contents of an InputStream to a String.
     */
    static String readStream(InputStream stream)
            throws IOException {
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public abstract static class HttpRequestTask extends AsyncTask<Void, Void, JSONObject> {
        String urlString;

        HttpRequestTask(String url) {
            urlString = url;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            try {

                URL url = new URL(urlString);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();


                //HttpResponse response = httpclient.execute(httpget);

                if (connection.getResponseCode() == 200) {

                    String server_response = readStream(connection.getInputStream());
                    Log.i("Server response", server_response);
                    return new JSONObject(server_response);
                } else {
                    Log.i("Server response", "Failed to get server response");
                }
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject r) {
            customEnd(r);
        }

        protected abstract void customEnd(JSONObject r);
    }
}
