package com.cloudHumans.BackendTest.entities;

import java.util.List;

public class EligibleProject {
    private int score;
    private String selectedProject;
    private List<String> eligibleProjects;
    private List<String> ineligibleProjects;

    public EligibleProject(int score, String selectedProject, List<String> eligibleProjects, List<String> ineligibleProjects) {
        this.score = score;
        this.selectedProject = selectedProject;
        this.eligibleProjects = eligibleProjects;
        this.ineligibleProjects = ineligibleProjects;
    }

    public EligibleProject() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(String selectedProject) {
        this.selectedProject = selectedProject;
    }

    public List<String> getEligibleProjects() {
        return eligibleProjects;
    }

    public void setEligibleProjects(List<String> eligibleProjects) {
        this.eligibleProjects = eligibleProjects;
    }

    public List<String> getIneligibleProjects() {
        return ineligibleProjects;
    }

    public void setIneligibleProjects(List<String> ineligibleProjects) {
        this.ineligibleProjects = ineligibleProjects;
    }
}
