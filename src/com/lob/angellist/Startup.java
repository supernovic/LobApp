package com.lob.angellist;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 6/9/2015.
 */
public class Startup {

    private int ID;
    private String name;
    private int quality;
    private String description;
    private String highConcept;
    private String companyURL;
    private Date createDate;
    private List<String> locations = new ArrayList<>();
    private String companySizeRange;

    public Startup(JSONObject startupJSON) {
        this.ID =  startupJSON.get("id");
        this.name = (String) startupJSON.get("name");
        this.quality = (Long) startupJSON.get("quality");
        this.description = (String) startupJSON.get("product_desc");
        this.highConcept = (String) startupJSON.get("high_concept");
        this.companyURL = (String) startupJSON.get("company_url");
        this.createDate = (Date) startupJSON.get("created_at");
        JSONArray locationsTag = (JSONArray) startupJSON.get("locations:");
        for (Object location : locationsTag) {
            JSONObject locationJSON = (JSONObject) location;
            String locationName = (String)locationJSON.get("name");
            locations.add(locationName);
        }
        this.companySizeRange = (String) startupJSON.get("company_size");
    }

}
