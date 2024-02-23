package com.cloudHumans.BackendTest.entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Pro {

    private Integer age;
    @JsonProperty("education_level")
    private String educationLevel;
    @JsonProperty("past_experiences")
    PastExperience pastExperience;
    @JsonProperty("internet_test")
    InternetTest internetTest;
    @JsonProperty("writing_score")
    private Double writingScore;
    @JsonProperty("referral_code")
    private String referralCode;


    public Pro(Integer age, String educationLevel, PastExperience pastExperience, InternetTest internetTest, Double writingScore, String referralCode) {
        this.age = age;
        this.educationLevel = educationLevel;
        this.pastExperience = pastExperience;
        this.internetTest = internetTest;
        this.writingScore = writingScore;
        this.referralCode = referralCode;
    }

    public Pro() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public PastExperience getPastExperiences() {
        return pastExperience;
    }

    public void setPastExperiences(PastExperience pastExperience) {
        this.pastExperience = pastExperience;
    }

    public InternetTest getInternetTest() {
        return internetTest;
    }

    public void setInternetTest(InternetTest internetTest) {
        this.internetTest = internetTest;
    }

    public Double getWritingScore() {
        return writingScore;
    }

    public void setWritingScore(Double writingScore) {
        this.writingScore = writingScore;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

}
