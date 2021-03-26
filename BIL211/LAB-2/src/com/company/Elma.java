package com.company;

public class Elma extends Sayilan {

    public Elma(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        super(miktar, gramaj, fiyat, kalite, sonTarih);
    }

    public String getTip() {
        return "Elma";
    }

}

