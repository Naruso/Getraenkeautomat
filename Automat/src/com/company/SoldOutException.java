package com.company;

public class SoldOutException extends RuntimeException {
    private String fehlermeldung;
    public SoldOutException(String fehlermeldung)
    {
        this.fehlermeldung = fehlermeldung;
    }

    public String getFehlermeldung() {
        return fehlermeldung;
    }
}
