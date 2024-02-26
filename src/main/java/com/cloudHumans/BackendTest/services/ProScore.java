package com.cloudHumans.BackendTest.services;

import com.cloudHumans.BackendTest.entities.InternetTest;
import com.cloudHumans.BackendTest.entities.Pro;
import org.springframework.stereotype.Service;

@Service
public class ProScore {

    private static final double DOWNLOAD_SPEED_UPPER = 50.0;
    private static final double DOWNLOAD_SPEED_DOWN = 5.0;
    private static final double UPLOAD_SPEED_UPPER = 50.0;
    private static final double UPLOAD_SPEED_DOWN = 5.0;
    private static final double SCORE_LOWER = 0.3;
    private static final double SCORE_HIGHER = 0.7;
    private static final String VALID_TOKEN = "token1234";

    protected Integer score(Pro pro) {
        Integer score = 0;

        score = calculateEducationLevel(pro, score);
        score = calculatePastExperiences(pro, score);
        score = CalculateInternetSpeed(pro, score);
        score = calculateWritingScore(pro, score);
        score = calculateValidToken(pro, score);

        return score;
    }

    private static Integer calculateEducationLevel(Pro pro, Integer score) {
        // Education
        if (pro.getEducationLevel() != null) {
            if (pro.getEducationLevel().equals("high_school")) {
                score++;

            } else if ((pro.getEducationLevel().equals("bachelors_degree_or_high"))) {
                score += 2;
            }
        }
        return score;
    }

    private static Integer calculatePastExperiences(Pro pro, Integer score) {

        if (pro.getPastExperiences() != null && Boolean.TRUE.equals(pro.getPastExperiences().getSales())) {
            score += 5;
        }
        if (pro.getPastExperiences() != null && Boolean.TRUE.equals(pro.getPastExperiences().getSupport())) {
            score += 3;
        }
        return score;
    }

    private static Integer CalculateInternetSpeed(Pro pro, Integer score) {
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
        return score;
    }

    private static Integer calculateWritingScore(Pro pro, Integer score) {
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
        return score;
    }


    private static Integer calculateValidToken(Pro pro, Integer score) {
        if(pro.getReferralCode() != null) {
            if (VALID_TOKEN.equals(pro.getReferralCode())) {
                score++;
            }
        }
        return score;
    }
}