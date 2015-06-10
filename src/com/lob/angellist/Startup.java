package com.lob.angellist;

import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Victor on 6/9/2015.
 */
public class Startup {

    private Long ID;
    private String name;
    private Long quality;
    private String description;
    private String highConcept;
    private String companyURL;
    private Date createDate;
    private List<String> locations = new ArrayList<>();
    private String companySizeRange;

    public Startup(JSONObject startupJSON) throws ParseException {
        this.ID =  (Long) startupJSON.get("id");
        this.name = (String) startupJSON.get("name");
        this.quality = (Long) startupJSON.get("quality");
        this.description = (String) startupJSON.get("product_desc");
        this.highConcept = (String) startupJSON.get("high_concept");
        this.companyURL = (String) startupJSON.get("company_url");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.createDate = sdf.parse((String) startupJSON.get("created_at"));

//        JSONArray locationsTag = (JSONArray) startupJSON.get("locations:");
//        for (Object location : locationsTag) {
//            JSONObject locationJSON = (JSONObject) location;
//            String locationName = (String)locationJSON.get("name");
//            locations.add(locationName);
//        }
        this.companySizeRange = (String) startupJSON.get("company_size");
    }

    public static Comparator<Startup> COMPARE_BY_QUALITY = new Comparator<Startup>() {
        public int compare(Startup s1, Startup s2) {
            return s1.quality.compareTo(s2.quality);
        }
    };

    @Override
    public String toString() {
        return "[Name: " + name + ", high_concept: " + highConcept + ", company_URL: "
                + companyURL + "]";
    }

}
