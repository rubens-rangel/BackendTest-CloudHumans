package com.cloudHumans.BackendTest.services;

import com.cloudHumans.BackendTest.entities.InternetTest;
import com.cloudHumans.BackendTest.entities.PastExperience;
import com.cloudHumans.BackendTest.entities.Pro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProScoreTest {

    @Test
    void testScore() {
        Pro pro = new Pro();
        pro.setAge(25);
        pro.setEducationLevel("bachelors_degree_or_high");//2
        pro.setPastExperiences(new PastExperience(true, false));//5
        pro.setInternetTest(new InternetTest(45.0f, 15.0f));
        pro.setWritingScore(0.8);//2
        pro.setReferralCode("token1234");//1

        ProScore proService = new ProScore();
        Integer result = proService.score(pro);

        assertEquals(10, result);
    }
}