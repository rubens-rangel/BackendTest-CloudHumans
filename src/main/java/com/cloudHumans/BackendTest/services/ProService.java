package com.cloudHumans.BackendTest.services;

import com.cloudHumans.BackendTest.entities.InternetTest;
import com.cloudHumans.BackendTest.entities.EligibleProject;
import com.cloudHumans.BackendTest.entities.Pro;
import com.cloudHumans.BackendTest.exceptions.UnderAgeException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProService {
    private static final int MIN_AGE = 18;
    private static final double DOWNLOAD_SPEED_UPPER = 50.0;
    private static final double DOWNLOAD_SPEED_DOWN = 5.0;
    private static final double UPLOAD_SPEED_UPPER = 50.0;
    private static final double UPLOAD_SPEED_DOWN = 5.0;
    private static final double SCORE_LOWER = 0.3;
    private static final double SCORE_HIGHER = 0.7;
    private static final String VALID_TOKEN = "token1234";

    public EligibleProject proEligibleProjects(Pro pro){
        ageValidate(pro);
        Integer calculated_score = score(pro);

        List<String> eligibleProjects = new ArrayList<>();
        List<String> ineligibleProjects = new ArrayList<>();
        String selectedProject = null;

        if (calculated_score > 10) {
            eligibleProjects.add("calculate_dark_matter_nasa");
        } else {
            ineligibleProjects.add("calculate_dark_matter_nasa");
        }

        if (calculated_score > 5) {
            eligibleProjects.add("determine_schrodinger_cat_is_alive");
            selectedProject = "determine_schrodinger_cat_is_alive";
        } else {
            ineligibleProjects.add("determine_schrodinger_cat_is_alive");
        }

        if (calculated_score > 3) {
            eligibleProjects.add("support_users_from_xyz");
        } else {
            ineligibleProjects.add("support_users_from_xyz");
        }

        if (calculated_score > 2) {
            eligibleProjects.add("collect_information_for_xpto");
        } else {
            ineligibleProjects.add("collect_information_for_xpto");
        }

        return new EligibleProject(calculated_score, selectedProject, eligibleProjects, ineligibleProjects);
    }

    protected void ageValidate(Pro pro) {
        if (pro.getAge() < MIN_AGE) {
            throw new UnderAgeException("Pro is ineligible to be paired with any project");
        }
    }

    protected Integer score(Pro pro) {
        Integer score = 0;

        // Education
        if (pro.getEducationLevel() != null) {
            if (pro.getEducationLevel().equals("high_school")) {
                score++;


            } else if ((pro.getEducationLevel().equals("bachelors_degree_or_high"))) {
                score += 2;
            }
        }

        score = calculatePastExperiences(pro, score);

        // Internet Speed
        InternetTest internetTest = pro.getInternetTest();
        if (internetTest != null) {
            Float downloadSpeed = internetTest.getDownloadSpeed();

            if (downloadSpeed != null) {
                if (downloadSpeed > DOWNLOAD_SPEED_UPPER) {
                    score++;
                } else if (downloadSpeed < DOWNLOAD_SPEED_DOWN) {
                    score--;
                }
            }
        }
        if (internetTest != null) {
            Float uploadSpeed = internetTest.getUploadSpeed();

            if (uploadSpeed != null) {
                if (uploadSpeed > UPLOAD_SPEED_UPPER) {
                    score++;
                } else if (uploadSpeed < UPLOAD_SPEED_DOWN) {
                    score--;
                }
            }
        }

        // Writhing Score
        Double writingScore = pro.getWritingScore();
        if (writingScore != null) {
            if (writingScore < SCORE_LOWER) {
                score--;
            } else if (writingScore >= SCORE_HIGHER) {
                score += 2;
            } else {
                score++;
            }
        }

        // Valid Token
        if (VALID_TOKEN.equals(pro.getReferralCode())) {
            score++;
        }

        return score;
    }

    private static Integer calculatePastExperiences(Pro pro, Integer score) {
        // Experience
        if (pro.getPastExperiences() != null && Boolean.TRUE.equals(pro.getPastExperiences().getSales())) {
            score += 5;
        }
        if (pro.getPastExperiences()!= null && Boolean.TRUE.equals(pro.getPastExperiences().getSupport())) {
            score += 3;
        }
        return score;
    }
}
