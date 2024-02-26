package com.cloudHumans.BackendTest.services;

import com.cloudHumans.BackendTest.entities.EligibleProject;
import com.cloudHumans.BackendTest.entities.Pro;
import com.cloudHumans.BackendTest.exceptions.UnderAgeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProEligibleProjects {
    private static final int MIN_AGE = 18;
    private static final String PROJECT_ONE = "calculate_dark_matter_nasa";
    private static final String PROJECT_TWO = "determine_schrodinger_cat_is_alive";
    private static final String PROJECT_THREE = "support_users_from_xyz";
    private static final String PROJECT_FOUR = "collect_information_for_xpto";

    @Autowired
    private ProScore proScore;

    public EligibleProject getEligibleProjects(Pro pro) {
        ageValidate(pro);
        Integer calculated_score = proScore.score(pro);

        List<String> eligibleProjects = new ArrayList<>();
        List<String> ineligibleProjects = new ArrayList<>();
        String selectedProject = null;

        if (calculated_score > 10) {
            eligibleProjects.add(PROJECT_ONE);
            selectedProject = PROJECT_ONE;
        } else {
            ineligibleProjects.add(PROJECT_ONE);
        }

        if (calculated_score > 5) {
            eligibleProjects.add(PROJECT_TWO);
            selectedProject = PROJECT_TWO;
        } else {
            ineligibleProjects.add(PROJECT_TWO);
        }

        if (calculated_score > 3) {
            eligibleProjects.add(PROJECT_THREE);
            selectedProject = PROJECT_THREE;
        } else {
            ineligibleProjects.add(PROJECT_THREE);
        }

        if (calculated_score > 2) {
            eligibleProjects.add(PROJECT_FOUR);
            selectedProject = PROJECT_FOUR;
        } else {
            ineligibleProjects.add(PROJECT_FOUR);
        }

        return new EligibleProject(calculated_score, selectedProject, eligibleProjects, ineligibleProjects);
    }

    protected void ageValidate(Pro pro) {
        if (pro.getAge() < MIN_AGE) {
            throw new UnderAgeException("Pro is ineligible to be paired with any project");
        }
    }
}
