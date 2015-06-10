package com.lob.angellist;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor on 6/9/2015.
 */
public class AngelListService {

    public static Startup getStartupById(int companyID) throws AngelListException {
        String apiURLString = AngelListServiceRoutes.API_BASE_PATH + AngelListServiceRoutes.STARTUPS
                + companyID;
        Startup startup;

        try {
            URL apiURL = new URL(apiURLString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) apiURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream());

            JSONParser parser = new JSONParser();

            JSONObject responseJSON = (JSONObject) parser.parse(reader);
            startup = new Startup(responseJSON);
        } catch (Exception e) {
            throw new AngelListException(e);
        }

        return startup;
    }
}
