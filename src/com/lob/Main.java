package com.lob;

import com.lob.angellist.AngelListException;
import com.lob.angellist.AngelListService;
import com.lob.angellist.Startup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Main class that reads in a .json file representing a Candidate and uses the RecommendationEngine to get a list of
 * recommended startups for the candidate to apply to.
 */

public class Main {

    private static int[] companyIDs = {60, 63, 112, 119, 167, 179, 194, 236, 281, 287, 312, 433, 469, 516, 537, 630, 631, 688, 684, 674, 2106, 5084, 4876, 4798, 4041, 3473, 2674, 2744, 3082, 2465, 1892, 1618, 1023, 900, 886};


    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("ERROR: Invalid command, must have ONLY one argument!");
            System.exit(1);
        }

        try (FileReader reader = new FileReader(args[0])) {
            AngelListService angelListService = new AngelListService();

            JSONParser parser = new JSONParser();
            JSONObject candidateJSON = (JSONObject) parser.parse(reader);
            System.out.println(candidateJSON.toString());

            Candidate candidate = new Candidate(candidateJSON);
            List<Startup> recommendedStartups = RecommendationEngine.getRecommendedStartups(candidate, angelListService, companyIDs);

            int numStartupsToDisplay = Math.min(10, recommendedStartups.size());

            System.out.println("Hi " + candidate.getName() + ", here are your recommended places to apply, sorted by the quality of the startup: \n");
            for(int i = 0; i < numStartupsToDisplay; i++) {
                Startup startup = recommendedStartups.get(i);
                System.out.println("Company Name: " + startup.getName());
                System.out.println("Company Concept: " + startup.getHighConcept());
                System.out.println("Company URL: " + startup.getCompanyURL());
                System.out.println("Company Quality Score: " + startup.getQuality());
                System.out.print("\n");

            }

            return;

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Cannot find specified file!");
            e.printStackTrace();
        } catch (IOException|ParseException e) {
            System.out.println("ERROR: Cannot parse JSON file!");
            e.printStackTrace();
        } catch (AngelListException e) {
            System.out.println("ERROR: Unexpected error in AngelListService");
            e.printStackTrace();
        }
    }


}
