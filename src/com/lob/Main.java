package com.lob;

import com.lob.angellist.Startup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("ERROR: Invalid command, must have ONLY one argument!");
            System.exit(1);
        }

        try (FileReader reader = new FileReader(args[0])) {
            JSONParser parser = new JSONParser();
            JSONObject candidateJSON = (JSONObject) parser.parse(reader);
            System.out.println(candidateJSON.toString());

            Candidate candidate = new Candidate(candidateJSON);
            List<Startup> recommendedStartups = getRecommendedStartups(candidate);
            // display recommended startups to standard out

            return;

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Cannot find specified file!");
            e.printStackTrace();
        } catch (IOException|ParseException e) {
            System.out.println("ERROR: Cannot parse JSON file!");
            e.printStackTrace();
        }
    }

}
