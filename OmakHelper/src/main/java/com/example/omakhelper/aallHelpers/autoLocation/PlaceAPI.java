package com.example.omakhelper.aallHelpers.autoLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class PlaceAPI {

    private static final String TAG = PlaceAPI.class.getSimpleName();

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";

    // private static final String API_KEY = "YOUR_API_KEY";
    //private static final String API_KEY = "AIzaSyCNMqJm2isTFlX4o-v4KG0pa7Kp6TK_KX4";
    private static final String API_KEY = "AIzaSyBiQuY4ElS0IsS3drP_RCX4OOdLgvxn8NE";
    // private static final String API_KEY = "AIzaSyA8S9rUxOl5bs8dLU1YTrATRQF1N3VuREs";
    //AIzaSyAL0GfDGUwGNQM-D9NVJSW1bEzmZQr6JgU

    public ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();

        try {

            // "types":["locality","political","geocode"]},

            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);

            //  sb.append("&types=(cities)");
            //sb.append("&components=country:in");
            // sb.append("&types=(locality)");
            //  sb.append("&types=(geocode)");
            sb.append("&sensor=true");
            // for abbrrebiation
            sb.append("abbrebiation=state");
            // sb.append("&components=country:as");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder

            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            return resultList;
        } catch (IOException e) {
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");
            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
        }

        return resultList;
    }
}
