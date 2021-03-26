package com.company;

public class Pirinc extends Tartilan{

    public Pirinc(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        super(miktar, gramaj, fiyat, kalite, sonTarih);
    }

    public String getTip() {
        return "Pirinc";
    }

}
