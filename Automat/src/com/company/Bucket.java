package com.company;


public class Bucket<G1, G2>{

    private G1 objekt1;
    private G2 objekt2;

    public Bucket(G1 objekt1, G2 objekt2)
    {
        this.objekt1 = objekt1;
        this.objekt2 = objekt2;
    }

    public G1 getObjekt1() {
        return objekt1;
    }

    public G2 getObjekt2() {
        return objekt2;
    }

}
