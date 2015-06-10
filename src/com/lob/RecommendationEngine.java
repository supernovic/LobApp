package com.lob;

import com.lob.angellist.AngelListException;
import com.lob.angellist.AngelListService;
import com.lob.angellist.Startup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vchang on 6/10/15.
 */
public class RecommendationEngine {

    public static List<Startup> getRecommendedStartups(Candidate candidate, AngelListService service, int[] companyIDs) throws AngelListException {
        List<Startup> startupList = new ArrayList<>();
        for (int ID : companyIDs) {
            Startup startup = service.getStartupById(ID);

            startupList.add(startup);
        }

        Collections.sort(startupList, Startup.COMPARE_BY_QUALITY);

        return startupList;
    }
}
