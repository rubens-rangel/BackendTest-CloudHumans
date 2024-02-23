package com.cloudHumans.BackendTest.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternetTest {
    @JsonProperty("download_speed")
    private Float downloadSpeed;
    @JsonProperty("upload_speed")
    private Float uploadSpeed;

    public InternetTest(Float downloadSpeed, Float uploadSpeed) {
        this.downloadSpeed = downloadSpeed;
        this.uploadSpeed = uploadSpeed;
    }

    public InternetTest() {
    }

    public Float getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(Float downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public Float getUploadSpeed() {
        return uploadSpeed;
    }

    public void setUploadSpeed(Float uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }
}
