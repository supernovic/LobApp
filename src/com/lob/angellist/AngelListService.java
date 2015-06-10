package com.lob.angellist;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by Victor on 6/9/2015.
 */
public class AngelListService {

    public Startup getStartupById(int companyID) {
        String apiURLString = AngelListServiceRoute.API_BASE_PATH + AngelListServiceRoute.STARTUPS
                + companyID;
        URL apiURL = new URL(apiURLString);
        HttpURLConnection httpURLConnection = apiURL.openConnection();
        httpURLConnection.setRequestMethod("GET");
        InputStreamReader reader = new InputStreamReader(httpURLConnection.getInputStream());

        JSONParser parser = new JSONParser();

        JSONObject responseJSON = (JSONObject) parser.parse(reader);
        Startup startup = new Startup(responseJSON);
        return startup;
    }
}
