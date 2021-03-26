package com.company;

public class Market {

    Urun[] urunler;

    public Market(Urun[] urunler){
        this.urunler = urunler;
    }

    public float alisverisUcreti(AlisverisListesi liste){
        double toplamUcret = 0;
        String alinamayacaklar = alinamayanlar(liste);
        sort();
        Urun[] alinacakListesi = liste.alisverisListesi;

        for( Urun alinacakUrun : alinacakListesi )
        {
            int alinacakMiktar = alinacakUrun.miktar;
            if(alinamayacaklar.indexOf(alinacakUrun.getTip()) == -1)  // urunden alabiliyorsak fiyat hesabina baslariz
            {
                for(Urun marketUrun : urunler) // once istenilen kaliteden fiyat hesabi yapariz
                {
                    if(alinacakUrun.getTip().equals(marketUrun.getTip()) && alinacakUrun.kalite == marketUrun.kalite && alinacakUrun.gramaj == marketUrun.gramaj && !marketUrun.tarihiGectiMi(alinacakUrun.sonTarih))
                    {
                        if(alinacakMiktar > marketUrun.miktar)
                        {
                            alinacakMiktar -= marketUrun.miktar;
                            toplamUcret += marketUrun.fiyat*marketUrun.miktar;
                        }
                        else
                        {
                            toplamUcret += alinacakMiktar*marketUrun.fiyat;
                            alinacakMiktar -= marketUrun.miktar;
                        }
                    }
                }

                if(alinacakMiktar > 0)
                {
                    for(Urun marketUrun : urunler)
                    {
                        if(alinacakUrun.getTip().equals(marketUrun.getTip()) && alinacakUrun.kalite > marketUrun.kalite && alinacakUrun.gramaj == marketUrun.gramaj && !marketUrun.tarihiGectiMi(alinacakUrun.sonTarih))
                        {
                            if(alinacakMiktar > marketUrun.miktar)
                            {
                                alinacakMiktar -= marketUrun.miktar;
                                toplamUcret += marketUrun.fiyat*marketUrun.miktar;
                            }
                            else
                            {
                                toplamUcret += alinacakMiktar*marketUrun.fiyat;
                            }
                        }
                    }
                }
            }
        }

        return (float)toplamUcret;
    }

    public String alinamayanlar(AlisverisListesi liste){
        Urun[] alinacakListesi = liste.alisverisListesi;
        String alinamayanlar =  "";
        int alinabilecekUrunMiktari = 0;

        for(Urun alinacakUrun : alinacakListesi)
        {
            for(Urun marketUrunu : urunler)
            {
                if(alinacakUrun.getTip().equals(marketUrunu.getTip()) && alinacakUrun.kalite >= marketUrunu.kalite && alinacakUrun.gramaj == marketUrunu.gramaj && !(marketUrunu.tarihiGectiMi(alinacakUrun.sonTarih))  )
                {
                    alinabilecekUrunMiktari += marketUrunu.miktar; // gerekli kriterler saglaniyorsa
                }							// alinabilecek urun sayisini arttiririz
            }
            if(alinabilecekUrunMiktari < alinacakUrun.miktar)
                alinamayanlar += alinacakUrun.getTip() + ", ";  // yeterli sayida alamiyorsak alinamayanlara ekleriz
            alinabilecekUrunMiktari = 0;
        }
        return alinamayanlar;
    }

    public void sort() {
        int n = urunler.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (urunler[j].kalite < urunler[j+1].kalite)
                {
                    Urun temp = urunler[j];
                    urunler[j] = urunler[j+1];
                    urunler[j+1] = temp;
                }

    }
}

