package com.company;

public class NichtBezahltException extends RuntimeException{
    private String message;
    private long restBetrag;
    private String testMessage;

    public NichtBezahltException(String message, long restBetrag)
    {
        this.message = message;
        this.restBetrag = restBetrag;
    }


    public String getMessage()
    {
        return message + restBetrag;
    }

    public long getRestBetrag() {
        return restBetrag;
    }
}
