package com.company;

public class Tartilan extends Urun {

    public Tartilan(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        super(miktar, gramaj, fiyat, kalite, sonTarih);
    }

    public String getTip() {
        return "Tartilan";
    }

}
