package com.gehuiban.events.api;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class EventsUpdater extends AsyncTask<String, Integer, ArrayList<Guide>> {

    private static String URL = "https://guidebook.com/service/v2/upcomingGuides/";
    private static String URL_GUIDE = "https://guidebook.com/service/v2";
    private OnFindDriveFolder onFeedUpdated;

    /**
     *  Interface to update the UI when the feed is updated
     */
    public interface OnFindDriveFolder {
        /**
         *
         * @param guides is the list of guides received
         */
        void onFeedUpdated(ArrayList<Guide> guides);
    }

    public EventsUpdater(OnFindDriveFolder onFeedUpdated) {
        this.onFeedUpdated = onFeedUpdated;
    }

    @Override
    protected ArrayList<Guide> doInBackground(String... params) {
        ArrayList<Guide> guides = new ArrayList<>();
        String response = queryUrl(URL);
        Gson gson = new Gson();
        Events results = gson.fromJson(response, Events.class);
        if (results != null && results.getData() != null){
            for (Guide guide : results.getData()) {
                response = queryUrl(URL_GUIDE + guide.getUrl().replace("guide", "guides"));
                Guide guideById = gson.fromJson(response, Guide.class);
                guides.add(guideById);
            }
        }

        return guides;
    }

    @Override
    protected void onPostExecute(ArrayList<Guide> guides) {
        Log.d("List OnPostExecute", "list size post:" + (guides == null ? 0 : guides.size()));
        if (onFeedUpdated != null) {
            onFeedUpdated.onFeedUpdated(guides);
        }
    }

    public static String queryUrl(String urlString) {
        InputStream stream;
        String response = "";
        try {
            URL url = new URL(urlString);
            Log.i("URL_EVENTS","urlString:" + urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            stream = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return response;
        }
    }
}
