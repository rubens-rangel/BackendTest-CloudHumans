package com.cloudHumans.BackendTest.entities;

public class PastExperience {

    private Boolean sales;
    private Boolean support;

    public PastExperience(boolean sales, boolean support) {
        this.sales = sales;
        this.support = support;
    }

    public PastExperience() {
    }

    public Boolean getSales() {
        return sales;
    }

    public void setSales(Boolean sales) {
        this.sales = sales;
    }

    public Boolean getSupport() {
        return support;
    }

    public void setSupport(Boolean support) {
        this.support = support;
    }
}
