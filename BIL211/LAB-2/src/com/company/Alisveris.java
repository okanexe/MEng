package com.company;

public class Alisveris {
    public static void main(String [] args){
        Urun[] aMarketUrunleri = new Urun[6];

        aMarketUrunleri[0] = new Ekmek(15, -1, 1.00, 2, "15/02/2021");
        aMarketUrunleri[1] = new Ekmek(3, -1, 0.75, 3, "14/02/2021");
        aMarketUrunleri[2] = new Pirinc(3, 500, 12.25, 2, "10/02/2022");
        aMarketUrunleri[3] = new Pirinc(1, 1500, 36.00, 2, "07/05/2023");
        aMarketUrunleri[4] = new Peynir(2, 250, 45.99, 2, "10/11/2021");
        aMarketUrunleri[5] = new Peynir(10, 250, 29.99, 3, "14/01/2021");

        Market aMarket = new Market(aMarketUrunleri);


        Urun[] bMarketUrunleri = new Urun[8];
        bMarketUrunleri[0] = new Ekmek(12, -1, 2.00, 1, "17/02/2021");
        bMarketUrunleri[1] = new Ekmek(1, -1, 0.75, 2, "14/02/2021");
        bMarketUrunleri[2] = new Pirinc(30, 250, 28.75, 1, "04/11/2022");
        bMarketUrunleri[3] = new Pirinc(10, 1500, 69.80, 2, "07/12/2023");
        bMarketUrunleri[4] = new Elma(3, -1, 1.59, 2, "15/03/2021");
        bMarketUrunleri[5] = new Elma(10, -1, 4.95, 1, "18/05/2021");
        bMarketUrunleri[6] = new Peynir(2, 250, 60.99, 2, "17/12/2021");
        bMarketUrunleri[7] = new Peynir(10, 500, 129.99, 1, "01/03/2022");

        Market bMarket = new Market(bMarketUrunleri);


        String bugun = "14/02/2021";
        Urun[] alisverisListesiUrunleri = new Urun[4];
        alisverisListesiUrunleri[0] = new Ekmek(5, -1, -1, 3, bugun);
        alisverisListesiUrunleri[1] = new Pirinc(1, 1500, -1, 2, bugun);
        alisverisListesiUrunleri[2] = new Elma(5, -1, -1, 3, bugun);
        alisverisListesiUrunleri[3] = new Peynir(2, 250, -1, 3, bugun);

        AlisverisListesi liste = new AlisverisListesi(alisverisListesiUrunleri);


        float aMarketFiyati = aMarket.alisverisUcreti(liste);
        String aMarketAlinamayanlar = aMarket.alinamayanlar(liste);

        float bMarketFiyati = bMarket.alisverisUcreti(liste);
        String bMarketAlinamayanlar = bMarket.alinamayanlar(liste); // Alinamayanlar listesi "Ekmek, Pirinc" formatinda
        //olmali, yerine ust kalite alinanlari icermemeli

        System.out.printf("A Market fiyati: %.2f\n", aMarketFiyati);
        System.out.println("A Marketten alinamayanlar: " + aMarketAlinamayanlar);

        System.out.printf("B Market fiyati: %.2f\n", bMarketFiyati);
        System.out.println("B Marketten alinamayanlar: " + bMarketAlinamayanlar);

    }
}

