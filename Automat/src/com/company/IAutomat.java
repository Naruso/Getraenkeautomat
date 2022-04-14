package com.company;

import java.util.List;

public interface IAutomat {

    long selectItemAndGetPreis(Produkt item);
    void insertCoin(Coin coin);
    List<Coin> erstattung();
    Bucket collectItemAndChange();
}
