package com.cloudHumans.BackendTest.controllers;

import com.cloudHumans.BackendTest.entities.EligibleProject;
import com.cloudHumans.BackendTest.entities.Pro;
import com.cloudHumans.BackendTest.exceptions.UnderAgeException;
import com.cloudHumans.BackendTest.services.ProEligibleProjects;
import com.cloudHumans.BackendTest.services.ProScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/pros")
public class ProsController {

    @Autowired
    ProEligibleProjects proEligibleProjects;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> insert(@RequestBody Pro pro) {
        Map<String, Object> response = new HashMap<>();

        try {
            EligibleProject eligibleProjects = proEligibleProjects.getEligibleProjects(pro);
            response.put("eligibility", eligibleProjects);
            return ResponseEntity.ok().body(response);

        } catch (UnderAgeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
