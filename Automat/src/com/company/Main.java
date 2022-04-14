package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Automat getraenkeAutomat = new Automat();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("[GETRÄNKEAUTOMAT]");
        System.out.println();
        getraenkeAutomat.ausgabeTextFuerGetraenke();
        System.out.println();
        System.out.println("Bitte wählen Sie ein Produkt aus!");
        System.out.println();
        System.out.println("[COLA] = 1\n"+
                "[LIMONADE] = 2\n" +
                "[WASSER] = 3\n" +
                "[EXIT] = 4");
        System.out.println();
        System.out.println();
        buyItem();
        System.out.println();
        System.out.println("Möchten Sie noch ein Getränk kaufen?");
        System.out.println("[EXIT] = 1 um das Programm zu beenden\n"
                +  "[WEITER] = 2 um noch ein Getränk zu kaufen!");
        System.out.println();
        String auswahlNeu = scanner.nextLine().toLowerCase();
        boolean stillBuying = true;
        while(stillBuying) {
            if (auswahlNeu.equals("1")) {
                stillBuying = false;
                System.out.println();
                System.out.println("Vielen Dank für Ihren Einkauf!");
                System.exit(0);
            } else {
                System.out.println();
                getraenkeAutomat.ausgabeTextFuerGetraenke();
                System.out.println();
                System.out.println("Bitte wählen Sie ein Produkt aus!");
                System.out.println();
                System.out.println("[COLA] = 1\n"+
                        "[LIMONADE] = 2\n" +
                        "[WASSER] = 3\n" +
                        "[EXIT] = 4");
                System.out.println();
                buyItem();
            }
            System.out.println();
            System.out.println("Möchten Sie noch ein Getränk kaufen?");
            System.out.println("[EXIT] = 1 um das Programm zu beenden\n"
                    +  "[WEITER] = 2 um noch ein Getränk zu kaufen!");
            System.out.println();
            auswahlNeu = scanner.nextLine().toLowerCase();
        }
    }
    public static void buyItem()
    {
        String auswahl = scanner.nextLine().toLowerCase();
        if (auswahl.equals("1")) {

            long preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.COLA);
                if(preis == 0)
                {
                    System.out.println();
                    System.out.println("[LIMONADE] = 2\n" +
                            "[WASSER] = 3\n" +
                            "[EXIT] = 4");
                    System.out.println();
                    auswahl = scanner.nextLine().toLowerCase();
                    if (auswahl.equals("2"))
                    {
                        preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.LIMONADE);
                        System.out.println("Preis LIMONADE: " + preis + " CENT");
                    }
                    else if (auswahl.equals("3"))
                    {
                        preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.WASSER);
                        System.out.println("Preis WASSER: " + preis + " CENT");
                    }
                    else if(auswahl.equals("4"))
                    {
                        System.exit(0);
                    }

                }
                else
                {
                    System.out.println("Preis COLA: " + preis + " CENT");
                }

            System.out.println("Bitte geben Sie die folgenden Zahlen ein um zu bezahlen. . .");
            System.out.println();
            System.out.println("[ZWEIEURO] = 200\n" +
                    "[EINEURO] = 100\n" + "[FUENFZIG] = 50\n" +
                    "[ZWANZIG] = 20\n" + "[ZEHN] = 10");
            System.out.println();
            System.out.println("Bitte Münzen einwerfen!");
            while (!getraenkeAutomat.istBezahlt()){
                System.out.println();
                getraenkeAutomat.ausgabeTextFuerMuenzen();
                System.out.println();
                String muenzeinwurf = scanner.nextLine().toLowerCase();
                if(muenzeinwurf.equals("200"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZWEIEURO);
                }
                else if(muenzeinwurf.equals("100"))
                {
                    getraenkeAutomat.insertCoin(Coin.EURO);
                }
                else if(muenzeinwurf.equals("50"))
                {
                    getraenkeAutomat.insertCoin(Coin.FUENFZIG);
                }
                else if(muenzeinwurf.equals("20"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZWANZIG);
                }
                else if(muenzeinwurf.equals("10"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZEHN);
                }
            }
            Bucket<Produkt, List<Coin>> bucketCola = getraenkeAutomat.collectItemAndChange();
            System.out.println("[RETURN]\n" +
                    "[GETRAENK] " + bucketCola.getObjekt1().toString() + "\n" +
                    "[WECHSELGELD] " + bucketCola.getObjekt2().toString());
            System.out.println();
            getraenkeAutomat.ausgabeText();
        }

        else if (auswahl.equals("2"))
        {
            long preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.LIMONADE);
            if(preis == 0)
            {
                System.out.println();
                System.out.println("[COLA] = 1\n" +
                        "[WASSER] = 3\n" +
                        "[EXIT] = 4");
                System.out.println();
                auswahl = scanner.nextLine().toLowerCase();
                if (auswahl.equals("1"))
                {
                    preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.COLA);
                    System.out.println("Preis COLA: " + preis + " CENT");
                }
                else if (auswahl.equals("3"))
                {
                    preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.WASSER);
                    System.out.println("Preis WASSER: " + preis + " CENT");
                }
                else if(auswahl.equals("4"))
                {
                    System.exit(0);
                }

            }
            else
            {
                System.out.println("Preis LIMONADE: " + preis + " CENT");
            }

            System.out.println("Bitte geben Sie die folgenden Zahlen ein um zu bezahlen. . .");
            System.out.println();
            System.out.println("[ZWEIEURO] = 200\n" +
                    "[EINEURO] = 100\n" + "[FUENFZIG] = 50\n" +
                    "[ZWANZIG] = 20\n" + "[ZEHN] = 10");
            System.out.println();
            System.out.println("Bitte Münzen einwerfen!");
            while (!getraenkeAutomat.istBezahlt()){
                System.out.println();
                getraenkeAutomat.ausgabeTextFuerMuenzen();
                System.out.println();
                String muenzeinwurf = scanner.nextLine().toLowerCase();
                if(muenzeinwurf.equals("200"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZWEIEURO);
                }
                else if(muenzeinwurf.equals("100"))
                {
                    getraenkeAutomat.insertCoin(Coin.EURO);
                }
                else if(muenzeinwurf.equals("50"))
                {
                    getraenkeAutomat.insertCoin(Coin.FUENFZIG);
                }
                else if(muenzeinwurf.equals("20"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZWANZIG);
                }
                else if(muenzeinwurf.equals("10"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZEHN);
                }
            }
            Bucket<Produkt, List<Coin>> bucketLimonade = getraenkeAutomat.collectItemAndChange();
            System.out.println("[RETURN]\n" +
                    "[GETRAENK] " + bucketLimonade.getObjekt1().toString() + "\n" +
                    "[WECHSELGELD] " + bucketLimonade.getObjekt2().toString());
            System.out.println();
            getraenkeAutomat.ausgabeText();

        }
        else if (auswahl.equals("3"))
        {
            long preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.WASSER);
            if(preis == 0)
            {
                System.out.println();
                System.out.println("[COLA] = 1\n" +
                        "[LIMONADE] = 2\n" +
                        "[EXIT] = 4");
                System.out.println();
                auswahl = scanner.nextLine().toLowerCase();
                if (auswahl.equals("1"))
                {
                    preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.COLA);
                    System.out.println("Preis COLA: " + preis + " CENT");
                }
                else if (auswahl.equals("2"))
                {
                    preis = getraenkeAutomat.selectItemAndGetPreis(Produkt.LIMONADE);
                    System.out.println("Preis LIMONADE: " + preis + " CENT");
                }
                else if(auswahl.equals("4"))
                {
                    System.exit(0);
                }

            }
            else
            {
                System.out.println("Preis WASSER: " + preis + " CENT");
            }

            System.out.println();
            System.out.println("Bitte geben Sie die folgenden Zahlen ein um zu bezahlen. . .");
            System.out.println();
            System.out.println("[ZWEIEURO] = 200\n" +
                    "[EINEURO] = 100\n" + "[FUENFZIG] = 50\n" +
                    "[ZWANZIG] = 20\n" + "[ZEHN] = 10");
            System.out.println();
            System.out.println("Bitte Münzen einwerfen!");
            while (!getraenkeAutomat.istBezahlt()){
                System.out.println();
                getraenkeAutomat.ausgabeTextFuerMuenzen();
                System.out.println();
                String muenzeinwurf = scanner.nextLine().toLowerCase();
                if(muenzeinwurf.equals("200"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZWEIEURO);
                }
                else if(muenzeinwurf.equals("100"))
                {
                    getraenkeAutomat.insertCoin(Coin.EURO);
                }
                else if(muenzeinwurf.equals("50"))
                {
                    getraenkeAutomat.insertCoin(Coin.FUENFZIG);
                }
                else if(muenzeinwurf.equals("20"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZWANZIG);
                }
                else if(muenzeinwurf.equals("10"))
                {
                    getraenkeAutomat.insertCoin(Coin.ZEHN);
                }
            }
            Bucket<Produkt, List<Coin>> bucketWasser = getraenkeAutomat.collectItemAndChange();
            System.out.println("[RETURN]\n" +
                    "[GETRAENK] " + bucketWasser.getObjekt1().toString() + "\n" +
                    "[WECHSELGELD] " + bucketWasser.getObjekt2().toString());
            System.out.println();
            getraenkeAutomat.ausgabeText();
        }
        else if(auswahl.equals("4"))
        {
            System.exit(0);
        }
        else
        {
            System.out.println("Dieses Getränk ist leider nicht verfügbar!");
        }

    }
}


