package com.company;

public class Peynir extends Tartilan {

    public Peynir(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        super(miktar, gramaj, fiyat, kalite, sonTarih);
    }

    public String getTip() {
        return "Peynir";
    }

}
