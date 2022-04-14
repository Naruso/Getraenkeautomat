package com.company;

public enum Produkt {
    COLA("Cola", 130),
    LIMONADE("Limonade", 120),
    WASSER("Wasser", 110);

    private long preis;
    private String name;

    Produkt(String name, long preis)
    {
        this.name = name;
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public long getPreis() {
        return preis;
    }
}
