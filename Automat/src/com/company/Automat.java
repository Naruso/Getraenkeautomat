package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Automat implements IAutomat{
    private Inventar<Produkt> produktInventar = new Inventar<>();
    private Inventar<Coin> coinInventar = new Inventar<>();
    private Produkt currentProdukt;
    private long currentSaldo;
    private long gesamtBetrag;

    public Automat()
    {
        initialisiere();
    }
    public void initialisiere()
    {
        for(Produkt p : Produkt.values()){
            produktInventar.putItem(p, 10);
        }

        for(Coin c: Coin.values()) {
            coinInventar.putItem(c, 10);
        }
        setCurrentSaldo(currentSaldo);
    }
    @Override
    public long selectItemAndGetPreis(Produkt item) throws SoldOutException {
        if(produktInventar.hasItem(item)){
            currentProdukt = item;
            return currentProdukt.getPreis();
        }
        System.out.println("Dieses Produkt ist leider ausverkauft. Bitte wählen Sie ein anderes Produkt!");
        return 0;
    }

    @Override
    public List<Coin> erstattung() {
        List<Coin> erstattungList = getWechselgeld(currentSaldo);
        updateCoinInventar(erstattungList);
        currentSaldo = 0;
        currentProdukt = null;
        return erstattungList;
    }

    @Override
    public void insertCoin(Coin coin) {
        currentSaldo = currentSaldo + coin.getCoinValue();
        coinInventar.addItem(coin);
        if(currentProdukt.getPreis() - currentSaldo < 0)
        {
            System.out.println("Betrag bezahlt: " + currentSaldo);
            System.out.println("[WECHSELGELD] "+ getWechselgeld(currentSaldo));
        }
        else
        {
            System.out.println("Noch zu zahlender Betrag: " + (currentProdukt.getPreis() - currentSaldo) + " CENT");

        }

        System.out.println();

    }
    @Override
    public Bucket<Produkt, List<Coin>> collectItemAndChange() {
        Produkt produktauswahl = takeProdukt();
        gesamtBetrag = gesamtBetrag + currentProdukt.getPreis();
        List<Coin> wechselgeld = sammelWechselgeld();

        return new Bucket<Produkt, List<Coin>>(produktauswahl, wechselgeld);
    }
    public Produkt takeProdukt() throws KeinWechselGeldException, NichtBezahltException
    {
        if(istBezahlt()){
            if(hatAusreichendWechselgeld())
            {
                produktInventar.removeItem(currentProdukt);
                return currentProdukt;
            }
            throw new KeinWechselGeldException("Der Automat hat nicht ausreichend Wechselgeld." +
                    " Bitte wählen Sie ein andere Produkt aus!");
        }
        long restBetrag = currentProdukt.getPreis() - currentSaldo;
        throw new NichtBezahltException("Bitte bezahlen Sie den Restbetrag. Restbetrag: ", restBetrag);
    }
    public List<Coin> sammelWechselgeld()
    {
        long wechselBetrag = currentSaldo - currentProdukt.getPreis();
        List<Coin> wechselgeld = getWechselgeld(wechselBetrag);
        currentSaldo = 0;
        currentProdukt = null;
        return wechselgeld;
    }


    public void setCurrentSaldo(long currentSaldo)
    {
        if(this.coinInventar.getInventar().size() > 0)

        this.currentSaldo = currentSaldo;
    }

    public List<Coin> getWechselgeld(long betrag)
    {
        List<Coin> wechselgeldList = Collections.EMPTY_LIST;

        if(betrag > 0){
            wechselgeldList = new ArrayList<Coin>();
            long saldo = betrag;
            while (saldo > 0) //Solange Saldo größer als 0 ist, wird Wechselgeld berechnet und ausgegeben
            {
                if(saldo >= Coin.ZWEIEURO.getCoinValue() && coinInventar.hasItem(Coin.ZWEIEURO))
                {
                wechselgeldList.add(Coin.ZWEIEURO);
                saldo = saldo - Coin.ZWEIEURO.getCoinValue();
                coinInventar.removeItem(Coin.ZWEIEURO);
                continue;
                }
                else if(saldo >= Coin.EURO.getCoinValue() && coinInventar.hasItem(Coin.EURO))
                {
                    wechselgeldList.add(Coin.EURO);
                    saldo = saldo - Coin.EURO.getCoinValue();
                    coinInventar.removeItem(Coin.EURO);
                    continue;
                }
                else if(saldo >= Coin.FUENFZIG.getCoinValue() && coinInventar.hasItem(Coin.FUENFZIG))
                {
                    wechselgeldList.add(Coin.FUENFZIG);
                    saldo = saldo - Coin.FUENFZIG.getCoinValue();
                    coinInventar.removeItem(Coin.FUENFZIG);
                    continue;
                }
                else if (saldo >= Coin.ZWANZIG.getCoinValue() && coinInventar.hasItem(Coin.ZWANZIG))
                {
                    wechselgeldList.add(Coin.ZWANZIG);
                    saldo = saldo - Coin.ZWANZIG.getCoinValue();
                    coinInventar.removeItem(Coin.ZWANZIG);
                    continue;
                }
                else if(saldo >= Coin.ZEHN.getCoinValue() && coinInventar.hasItem(Coin.ZEHN))
                {
                    wechselgeldList.add(Coin.ZEHN);
                    saldo = saldo - Coin.ZEHN.getCoinValue();
                    coinInventar.removeItem(Coin.ZEHN);
                    continue;
                }
                else
                {
                    System.out.println("Kein Wechselgeld übrig!");
                    erstattung();
                    System.exit(0);
                }
            }
        }
        return wechselgeldList;
    }
    public List<Coin> pruefeGetWechselgeld(long betrag)
    {
        List<Coin> wechselgeldList = Collections.EMPTY_LIST;

        if(betrag > 0){
            wechselgeldList = new ArrayList<Coin>();
            long saldo = betrag;
            while (saldo > 0) //Solange Saldo größer als 0 ist, wird Wechselgeld berechnet und ausgegeben
            {
                if(saldo >= Coin.ZWEIEURO.getCoinValue() && coinInventar.hasItem(Coin.ZWEIEURO))
                {
                    wechselgeldList.add(Coin.ZWEIEURO);
                    saldo = saldo - Coin.ZWEIEURO.getCoinValue();

                    continue;
                }
                else if(saldo >= Coin.EURO.getCoinValue() && coinInventar.hasItem(Coin.EURO))
                {
                    wechselgeldList.add(Coin.EURO);
                    saldo = saldo - Coin.EURO.getCoinValue();

                    continue;
                }
                else if(saldo >= Coin.FUENFZIG.getCoinValue() && coinInventar.hasItem(Coin.FUENFZIG))
                {
                    wechselgeldList.add(Coin.FUENFZIG);
                    saldo = saldo - Coin.FUENFZIG.getCoinValue();

                    continue;
                }
                else if (saldo >= Coin.ZWANZIG.getCoinValue() && coinInventar.hasItem(Coin.ZWANZIG))
                {
                    wechselgeldList.add(Coin.ZWANZIG);
                    saldo = saldo - Coin.ZWANZIG.getCoinValue();

                    continue;
                }
                else if(saldo >= Coin.ZEHN.getCoinValue() && coinInventar.hasItem(Coin.ZEHN))
                {
                    wechselgeldList.add(Coin.ZEHN);
                    saldo = saldo - Coin.ZEHN.getCoinValue();

                    continue;
                }
                else
                {
                    System.out.println("Kein Wechselgeld übrig!");
                    System.exit(0);
                }
            }
        }
        return wechselgeldList;
    }

    public boolean istBezahlt()
    {
        if(currentSaldo >= currentProdukt.getPreis())
            return true;
        else
            return false;
    }
    private void updateCoinInventar(List<Coin> updatedCoinList)
    {
        for(Coin c : updatedCoinList)
            coinInventar.removeItem(c);
    }

    private boolean hatAusreichendWechselgeld(){
        return checkWechselgeldFuerBetrag(currentSaldo - currentProdukt.getPreis());
    }
    private boolean checkWechselgeldFuerBetrag(long betrag)
    {
        boolean hatWechselgeld = true;

        try {
            pruefeGetWechselgeld(betrag);
        }
        catch(KeinWechselGeldException e)
        {
            return hatWechselgeld = false;
        }
        return hatWechselgeld;
    }

    public long getGesamtBetrag() {
        return gesamtBetrag;
    }

    public void ausgabeText()
    {
        System.out.println("#####################");
        System.out.println("Gesamtbetrag: " + gesamtBetrag);
        System.out.println("Aktueller Artikelbestand: " + produktInventar.getInventar());
        System.out.println("Aktueller Münzbestand: " + coinInventar.getInventar());
        System.out.println("######################");
    }
    public void ausgabeTextFuerMuenzen()
    {
        System.out.println("#####################");
        System.out.println("Aktueller Münzbestand: " + coinInventar.getInventar());
        System.out.println("######################");
    }
    public void ausgabeTextFuerGetraenke()
    {
        System.out.println("#####################");
        System.out.println("Aktueller Artikelbestand: " + produktInventar.getInventar());
        System.out.println("######################");
    }

    public void reset()
    {
        this.currentProdukt = null;
    }
}
