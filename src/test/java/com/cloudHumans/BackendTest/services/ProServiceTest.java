package com.cloudHumans.BackendTest.services;

import com.cloudHumans.BackendTest.entities.InternetTest;
import com.cloudHumans.BackendTest.entities.PastExperience;
import com.cloudHumans.BackendTest.entities.EligibleProject;
import com.cloudHumans.BackendTest.entities.Pro;
import com.cloudHumans.BackendTest.exceptions.UnderAgeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProServiceTest {

    @Test
    void testProEligibleProjectsFirstScenario() {
        Pro pro = new Pro();
        pro.setAge(20);
        pro.setEducationLevel("high_school"); //1
        pro.setPastExperiences(new PastExperience(false, true)); //3
        pro.setInternetTest(new InternetTest(55.0f, 20.0f)); //1
        pro.setWritingScore(0.5); //1
        pro.setReferralCode("token1234"); //1

        ProService proService = new ProService();
        EligibleProject result = proService.proEligibleProjects(pro);

        assertEquals(7, result.getScore());
        assertEquals("determine_schrodinger_cat_is_alive", result.getSelectedProject());
        assertEquals(3, result.getEligibleProjects().size());
        assertEquals(1, result.getIneligibleProjects().size());
    }

    @Test
    void testProEligibleProjectsSecondScenario() {
        Pro pro = new Pro();
        pro.setAge(20);
        pro.setEducationLevel("high_school"); //1
        pro.setPastExperiences(new PastExperience(true, true)); //8
        pro.setInternetTest(new InternetTest(55.0f, 20.0f)); //1
        pro.setWritingScore(0.5); //1
        pro.setReferralCode("token1234"); //1

        ProService proService = new ProService();
        EligibleProject result = proService.proEligibleProjects(pro);

        assertEquals(12, result.getScore());
        assertEquals("determine_schrodinger_cat_is_alive", result.getSelectedProject());
        assertEquals(4, result.getEligibleProjects().size());
        assertEquals(0, result.getIneligibleProjects().size());
    }

    @Test
    void testProEligibleProjectsThirdScenario() {
        Pro pro = new Pro();
        pro.setAge(20);
        pro.setEducationLevel("high_school"); //1
        pro.setPastExperiences(new PastExperience(false, false)); //0
        pro.setInternetTest(new InternetTest(55.0f, 4.0f)); //1 -1 = 0
        pro.setWritingScore(0.2); //-1
        pro.setReferralCode("token1234"); //1

        ProService proService = new ProService();
        EligibleProject result = proService.proEligibleProjects(pro);

        assertEquals(1, result.getScore());
        assertEquals(0, result.getEligibleProjects().size());
        assertEquals(4, result.getIneligibleProjects().size());
    }
    @Test
    void testProEligibleProjectsFifthScenario() {
        Pro pro = new Pro();
        pro.setAge(20);
        pro.setEducationLevel("high_school"); //1
        pro.setPastExperiences(new PastExperience(false, false)); //0
        pro.setInternetTest(new InternetTest(4.0f, 4.0f)); //-1 -1 = -2
        pro.setWritingScore(0.8); //2
        pro.setReferralCode("token1234"); //1

        ProService proService = new ProService();
        EligibleProject result = proService.proEligibleProjects(pro);

        assertEquals(1, result.getScore());
        assertEquals(0, result.getEligibleProjects().size());
        assertEquals(4, result.getIneligibleProjects().size());
    }

    @Test
    void testAgeValidateUnder() {
        ProService proService = new ProService();

        Pro proUnderAge = new Pro();
        proUnderAge.setAge(17);
        assertThrows(UnderAgeException.class, () -> proService.ageValidate(proUnderAge));
    }

    @Test
    void testAgeValidateOver() {
        ProService proService = new ProService();

        Pro proAboveAge = new Pro();
        proAboveAge.setAge(20);
        assertDoesNotThrow(() -> proService.ageValidate(proAboveAge));
    }


    void testScore() {
        Pro pro = new Pro();
        pro.setAge(25);
        pro.setEducationLevel("bachelors_degree_or_high");//2
        pro.setPastExperiences(new PastExperience(true, false));//5
        pro.setInternetTest(new InternetTest(45.0f, 15.0f));
        pro.setWritingScore(0.8);//2
        pro.setReferralCode("token1234");//1

        ProService proService = new ProService();
        Integer result = proService.score(pro);

        assertEquals(12, result);
    }
}