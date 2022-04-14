package com.company;

public enum Coin {

    ZEHN (10),
    ZWANZIG (20),
    FUENFZIG (50),
    EURO (100),
    ZWEIEURO (200);

    private int coinValue;

    Coin(int coinValue)
    {
        this.coinValue = coinValue;
    }

    public int getCoinValue()
    {
        return coinValue;
    }
}
