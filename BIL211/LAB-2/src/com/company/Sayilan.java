package com.company;

public class Sayilan extends Urun {

    public Sayilan(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        super(miktar, gramaj, fiyat, kalite, sonTarih);
    }

    public String getTip() {
        return "Sayilan";
    }

}
