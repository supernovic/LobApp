package com.lob.angellist;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Startup class representing a Startup company on AngelList, parsed from a JSONObject.
 *
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

        JSONArray locationsTag = (JSONArray) startupJSON.get("locations");
        if (locationsTag != null && !locationsTag.isEmpty()) {
            for (int i = 0; i < locationsTag.size(); i++) {
                JSONObject locationJSON = (JSONObject) locationsTag.get(i);
                String locationName = (String) locationJSON.get("name");
                locations.add(locationName);
            }
        }
        this.companySizeRange = (String) startupJSON.get("company_size");
    }

    public static Comparator<Startup> COMPARE_BY_QUALITY = new Comparator<Startup>() {
        public int compare(Startup s1, Startup s2) {
            return s1.quality.compareTo(s2.quality);
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighConcept() {
        return highConcept;
    }

    public void setHighConcept(String highConcept) {
        this.highConcept = highConcept;
    }

    public String getCompanyURL() {
        return companyURL;
    }

    public void setCompanyURL(String companyURL) {
        this.companyURL = companyURL;
    }

    public String getCompanySizeRange() {
        return companySizeRange;
    }

    public void setCompanySizeRange(String companySizeRange) {
        this.companySizeRange = companySizeRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuality() {
        return quality;
    }

    public void setQuality(Long quality) {
        this.quality = quality;
    }
}
