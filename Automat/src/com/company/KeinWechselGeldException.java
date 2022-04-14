package com.company;

public class KeinWechselGeldException extends RuntimeException{

    private String fehlermeldung;
    public KeinWechselGeldException(String fehlermeldung)
    {
        this.fehlermeldung = fehlermeldung;
    }

    public String getFehlermeldung() {
        return fehlermeldung;
    }
}
