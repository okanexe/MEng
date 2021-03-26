package com.company;

public class Ekmek extends Sayilan {

    public Ekmek(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        super(miktar, gramaj, fiyat, kalite, sonTarih);
    }

    public String getTip() {
        return "Ekmek";
    }

}
