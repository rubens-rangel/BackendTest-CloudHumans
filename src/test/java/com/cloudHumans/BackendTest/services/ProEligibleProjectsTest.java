package com.cloudHumans.BackendTest.services;

import com.cloudHumans.BackendTest.entities.EligibleProject;
import com.cloudHumans.BackendTest.entities.InternetTest;
import com.cloudHumans.BackendTest.entities.PastExperience;
import com.cloudHumans.BackendTest.entities.Pro;
import com.cloudHumans.BackendTest.exceptions.UnderAgeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProEligibleProjectsTest {


    @InjectMocks
    private ProEligibleProjects proEligibleProjects;

    @Mock
    private ProScore proScore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgeValidateUnder() {
        ProEligibleProjects proEligibleProjects = new ProEligibleProjects();

        Pro proUnderAge = new Pro();
        proUnderAge.setAge(17);
        assertThrows(UnderAgeException.class, () -> proEligibleProjects.ageValidate(proUnderAge));
    }

    @Test
    void testAgeValidateOver() {
        ProEligibleProjects proEligibleProjects = new ProEligibleProjects();

        Pro proAboveAge = new Pro();
        proAboveAge.setAge(20);
        assertDoesNotThrow(() -> proEligibleProjects.ageValidate(proAboveAge));
    }


    @Test
    void testProEligibleProjectsFirstScenario() {
        Pro pro = new Pro();
        pro.setAge(20);
        pro.setEducationLevel("high_school"); //1
        pro.setPastExperiences(new PastExperience(false, true)); //3
        pro.setInternetTest(new InternetTest(55.0f, 20.0f)); //1
        pro.setWritingScore(0.5); //1
        pro.setReferralCode("token1234"); //1

        when(proScore.score(pro)).thenReturn(7);

        EligibleProject result = proEligibleProjects.getEligibleProjects(pro);

        assertEquals(7, result.getScore());
        assertEquals("collect_information_for_xpto", result.getSelectedProject());
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

        when(proScore.score(pro)).thenReturn(12);

        EligibleProject result = proEligibleProjects.getEligibleProjects(pro);

        assertEquals(12, result.getScore());
        assertEquals("collect_information_for_xpto", result.getSelectedProject());
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

        when(proScore.score(pro)).thenReturn(1);

        EligibleProject result = proEligibleProjects.getEligibleProjects(pro);

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

        when(proScore.score(pro)).thenReturn(2);

        EligibleProject result = proEligibleProjects.getEligibleProjects(pro);

        assertEquals(2, result.getScore());
        assertEquals(0, result.getEligibleProjects().size());
        assertEquals(4, result.getIneligibleProjects().size());
    }

    @Test
    void testProEligibleProjectsSixthScenario() {
        Pro pro = new Pro();
        pro.setAge(20);
        pro.setEducationLevel("no_education"); //0
        pro.setPastExperiences(new PastExperience(true, false)); //5
        pro.setInternetTest(new InternetTest(4.0f, 4.0f)); //-1 -1 = -2
        pro.setWritingScore(-0.1); //-1
        pro.setReferralCode("errado"); //0

        when(proScore.score(pro)).thenReturn(2);

        EligibleProject result = proEligibleProjects.getEligibleProjects(pro);

        assertEquals(2, result.getScore());
        assertEquals(0, result.getEligibleProjects().size());
        assertEquals(4, result.getIneligibleProjects().size());
    }
}