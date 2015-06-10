package com.lob;

import org.json.simple.JSONObject;

/**
 * Created by Victor Chang on 6/9/2015.
 */

public class Candidate {

    private String name;
    private Long age;
    private String role;
    private Long desiredSalary;

    public Candidate(JSONObject candidateJSON) {
        this.name = (String) candidateJSON.get("name");
        this.age = (Long) candidateJSON.get("age");
        this.role = (String) candidateJSON.get("role");
        this.desiredSalary = (Long) candidateJSON.get("salary");
    }

}
