package com.company;

import java.util.HashMap;


public class Inventar<T> {
    private HashMap<T, Integer> inventar = new HashMap<>();


    public int getMenge(T item)
    {
        int menge = inventar.get(item);
        if(menge != 0 )
        {
            return menge;
        }
        return 0;
    }

    public HashMap<T, Integer> getInventar()
    {
        return inventar;
    }

    public void addItem(T item)
    {
        int anzahl = inventar.get(item);
        inventar.put(item, anzahl + 1);
    }
    public void putItem(T item, int count)
    {
        inventar.put(item, count);
    }

    public void removeItem(T item)
    {
        if(hasItem(item)) {
            int anzahl = inventar.get(item);
            inventar.put(item, anzahl - 1);
        }
    }

    public boolean hasItem(T item)
    {
        if(getMenge(item) > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void clearInventory(){

        inventar.clear();

    }
}
