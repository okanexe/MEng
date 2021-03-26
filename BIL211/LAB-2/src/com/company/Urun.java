package com.company;

public class Urun {

    public int miktar;
    public int gramaj;
    public float fiyat;
    public int kalite;
    public String sonTarih;

    public Urun(int miktar, int gramaj, double fiyat, int kalite, String sonTarih) {
        this.miktar = miktar;
        this.gramaj = gramaj;
        this.fiyat = (float)fiyat;
        this.kalite = kalite;
        this.sonTarih = sonTarih;
    }

    public String getTip() { // bunu surekli diger classlarda override ederek urunun tipine erisecegiz
        return "Urun";
    }

    public boolean tarihiGectiMi(String bugunTarihi) {
        return getDate(bugunTarihi) > getDate(sonTarih);
    }

    public double getDate(String tarih) {  // 12/11/2021 tarihini ---> 20211112 seklinde bir sayiya cevirdim. Sayisi buyuk olanin tarihi daha ileri olur boylece
        String tarihValue = "";
        tarihValue = tarih.substring(6) + tarih.substring(3,5) + tarih.substring(0,2);
        return Double.parseDouble(tarihValue);
    }

}
