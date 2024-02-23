package com.cloudHumans.BackendTest.controllers;

import com.cloudHumans.BackendTest.entities.EligibleProject;
import com.cloudHumans.BackendTest.entities.Pro;
import com.cloudHumans.BackendTest.exceptions.UnderAgeException;
import com.cloudHumans.BackendTest.services.ProService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ProsControllerTest {

    @InjectMocks
    private ProsController prosController;

    @Mock
    private ProService proService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertValidPro() {
        Pro validPro = new Pro();
        EligibleProject eligibleProject = new EligibleProject();

        when(proService.proEligibleProjects(validPro)).thenReturn(eligibleProject);
        ResponseEntity<Map<String, Object>> responseEntity = prosController.insert(validPro);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Map<String, Object> responseBody = responseEntity.getBody();
        assertEquals(eligibleProject, responseBody.get("eligibility"));
    }

    @Test
    void testInsertUnderAgeException() {
        Pro underAgePro = new Pro();
        underAgePro.setAge(17);

        String errorMessage = "Pro is ineligible to be paired with any project due to age";
        when(proService.proEligibleProjects(underAgePro)).thenThrow(new UnderAgeException(errorMessage));

        ResponseEntity<Map<String, Object>> responseEntity = prosController.insert(underAgePro);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Map<String, Object> responseBody = responseEntity.getBody();
        assertEquals(errorMessage, responseBody.get("error"));
    }

}