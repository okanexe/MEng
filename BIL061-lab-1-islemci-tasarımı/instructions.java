import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//buyruk işlemlerinin gerçekleştiği kısım
public class instructions {
    yazmac register = new yazmac();
    HashMap<Integer, Integer> memory = new HashMap<Integer, Integer>(1048576);
    ArrayList<String> buyruk_bellegi = new ArrayList<String>(1048576);
    String pc = "0x0000"; // program sayacı
    int buyruk_sayisi =0;
    int R_bbc = 0;
    int I_bbc = 0;
    int BS_bbc = 0;
    int J_bbc = 0;
    int toplam_bbc =0;
    // dosyadan okunan birim başına çevrimler burada atanıyor
    public void set_BBC(int R_bbc, int I_bbc, int BS_bbc, int J_bbc){
        this.R_bbc = R_bbc;
        this.I_bbc = I_bbc;
        this.BS_bbc = BS_bbc;
        this.J_bbc = J_bbc;
    }
    // instructions oluşturulurken buyrukların olduğu bir arraylisti alıyor.
    public instructions(ArrayList<String> buyruk_bellegi){
        this.buyruk_bellegi = buyruk_bellegi;
    }

    public void execute(){// programın çalıştırıldığı kısım
        boolean SON =false; // SON komutunu arar
        while (true){ // PROGRAMIN ÇALIŞTIĞI YER
            System.out.println("başlangıç: " + pc);
            // x0 her zaman 0 olarak kalmalı.
            register.setRegister("x0", 0);

            for(String buyruk : buyruk_bellegi){
                // Hem son komutunu bulacak hem de program sayaacını kontrol edecek.(programın sonlanması için)
                if(buyruk.split(" ")[1].equals("SON") && buyruk.split(" ")[0].equals(pc)) {
                    SON = true;
                    buyruk_sayisi +=1;
                    break;
                }
                else if(Integer.decode(buyruk.split(" ")[0]).equals(Integer.decode(pc))){
                    //System.out.println(pc);
                    String type = buyruk.split(" ")[1];
                    //System.out.println(type);
                    System.out.println("...ready to enter type...");

                    if (type_is(type).equals("R-TYPE")){
                        rtype(buyruk);
                        pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
                        this.buyruk_sayisi += 1;
                        this.toplam_bbc += R_bbc;
                        break;
                    }
                    else if(type_is(type).equals("I-TYPE")){
                        itype(buyruk);
                        this.buyruk_sayisi += 1;
                        this.toplam_bbc += I_bbc;
                        //pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
                        break;
                    }
                    else if(type_is(type).equals("BS-TYPE")){
                        pc = bstype(buyruk, pc);
                        this.buyruk_sayisi += 1;
                        this.toplam_bbc += BS_bbc;
                        break;
                    }
                    else if(type_is(type).equals("J-TYPE")){
                        pc = jtype(buyruk);
                        this.buyruk_sayisi += 1;
                        this.toplam_bbc += J_bbc;
                        break;
                    }
                    else
                        System.out.println("!!! WRONG TYPE !!!");
                    break;
                }
            }
            if(SON)
                break;
        }
    }
    // bu oluşturuldu fakat kullanılmadı. gerek yok diye düşündüm.
    // Ama istenirse aşağıdaki buyruk fonksiyonlarının içindeki aritmetik işlemlerde kullanılabilir.
    public int AMB(int x1, int x2, int xhedef, String islem){
        if(islem == "00"){//ADD
            xhedef = x1 + x2;
            return xhedef;
        }
        else if(islem == "01"){//SUB
            xhedef = x1 - x2;
            return xhedef;
        }
        else if(islem == "10"){// AND
            xhedef = x1 & x2;
            return xhedef;
        }
        else if(islem == "11"){//OR
            xhedef = x1 | x2;
            return xhedef;
        }
        else
            System.out.println("Please give correct to AMB parameter...");
        return 0;
    }

    // gelen buyruğun tipi tespit ediliyor ve ilerde ona göre fonksiyonlara yönlendirilip program çalışırılacak.
    public String type_is(String type){
        String[] r_types = new String[]{"add", "sub", "xor", "and", "srl", "sra"};
        List<String> RTYPE = Arrays.asList(r_types);

        String[] i_types = new String[]{"addi", "subi", "xori", "jalr", "lw", "lb", "slti", "srai"};
        List<String> ITYPE = Arrays.asList(i_types);

        String[] bs_types = new String[]{"beq", "bge", "blt", "sw", "sb"};
        List<String> BSTYPE = Arrays.asList(bs_types);

        String[] j_types = new String[]{"jal"};
        List<String> JTYPE = Arrays.asList(j_types);
        //System.out.println(type);

        if (RTYPE.contains(type)) {
            //System.out.println("R-type");
            return "R-TYPE";
        } else if (ITYPE.contains(type)) {
            //System.out.println("I-type");
            return "I-TYPE";
        } else if (BSTYPE.contains(type)) {
            //System.out.println("bs-type");
            return "BS-TYPE";
        } else if(JTYPE.contains(type)) {
            //System.out.println("j-type");
            return "J-TYPE";
        }else{
            return "...invalid type...";
        }
    }

    public void rtype(String instruction){
        String buyruk_adresi = instruction.split(" ")[0];
        String buyruk = instruction.split(" ")[1];
        String hedef_yazmaci = instruction.split(" ")[2];
        String kaynak_yazmaci1 = instruction.split(" ")[3];
        String kaynak_yazmaci2 = instruction.split(" ")[4];

        if (buyruk.equals("add")){
            // normal
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) + register.getRegister(kaynak_yazmaci1));
            // aritmetik mantık birimi ile
            //register.setRegister(hedef_yazmaci, AMB(register.getRegister(kaynak_yazmaci1), register.getRegister(kaynak_yazmaci2), register.getRegister(hedef_yazmaci), "00"));
        }
        else if(buyruk.equals("sub")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) - register.getRegister(kaynak_yazmaci1));
        }
        else if(buyruk.equals("xor")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) ^ register.getRegister(kaynak_yazmaci1));
        }
        else if (buyruk.equals("and")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) & register.getRegister(kaynak_yazmaci1));
        }
        else if(buyruk.equals("srl")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) >>> register.getRegister(kaynak_yazmaci1));
        }
        else if(buyruk.equals("sra")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) >> register.getRegister(kaynak_yazmaci1));
        }
        else
            System.out.println("...Please give correct R-type instruction...");
    }

    public void itype(String instruction){
        String buyruk_adresi = instruction.split(" ")[0];
        String buyruk = instruction.split(" ")[1];
        String hedef_yazmaci = instruction.split(" ")[2];
        String kaynak_yazmaci1 = instruction.split(" ")[3];
        int anlik_deger = Integer.decode(instruction.split(" ")[4]);

        if(buyruk.equals("addi")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) + anlik_deger);
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("subi")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) - anlik_deger);
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("xori")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) ^ anlik_deger);
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if (buyruk.equals("jalr")){// anlık değerine tekrar bakılacak....
            register.setRegister(hedef_yazmaci, Integer.decode(buyruk_adresi) + 4);
            pc = "0x00"+Integer.toHexString(register.getRegister(kaynak_yazmaci1) + anlik_deger);
        }
        else if(buyruk.equals("lw")){
            register.setRegister(hedef_yazmaci, memory.get(register.getRegister(kaynak_yazmaci1) + anlik_deger));
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("lb")){
            int data = memory.get(register.getRegister(kaynak_yazmaci1) + anlik_deger);
            data = (data & 0x000000FF); // memory'deki değerin ilk 8 biti alınıyor.
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
            register.setRegister(hedef_yazmaci, data);

        }
        else if(buyruk.equals("slti")){
            if(register.getRegister(kaynak_yazmaci1) < anlik_deger){
                register.setRegister(hedef_yazmaci, 1);
            }else{
                register.setRegister(hedef_yazmaci, 0);
            }
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if (buyruk.equals("srai")){
            register.setRegister(hedef_yazmaci, register.getRegister(kaynak_yazmaci1) >> anlik_deger);
            pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else
            System.out.println("Please give correct itype parameters...");
    }

    public String bstype(String instruction, String pc){
        String buyruk_adresi = instruction.split(" ")[0];
        String buyruk = instruction.split(" ")[1];
        String kaynak_yazmaci1 = instruction.split(" ")[2];
        String kaynak_yazmaci2 = instruction.split(" ")[3];
        int anlik_deger = Integer.decode(instruction.split(" ")[4]);

        if(buyruk.equals("beq")){
            if(register.getRegister(kaynak_yazmaci1) == register.getRegister(kaynak_yazmaci2)){
                pc = "0x00"+Integer.toHexString(Integer.decode(pc) + anlik_deger);
                return pc;
            }
            else
                return pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("bge")){
            if(register.getRegister(kaynak_yazmaci1) >= register.getRegister(kaynak_yazmaci2)){
                pc = "0x00"+Integer.toHexString(Integer.decode(pc) + anlik_deger*2);
                return pc;
            }
            else
                return pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("blt")){
            if(register.getRegister(kaynak_yazmaci1) < register.getRegister(kaynak_yazmaci2)){
                pc = "0x00"+Integer.toHexString(Integer.decode(pc) + anlik_deger);
                return pc;
            }
            else
                return pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("sw")){
            int adres = register.getRegister(kaynak_yazmaci2) + anlik_deger;
            memory.put(adres, register.getRegister(kaynak_yazmaci1));
            return pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else if(buyruk.equals("sb")){
            int adres = ( (register.getRegister(kaynak_yazmaci2) + anlik_deger) & 0x000000FF );
            memory.put(adres, register.getRegister(kaynak_yazmaci1));
            return pc = "0x00"+Integer.toHexString(Integer.decode(pc) + 4);
        }
        else
            return("Please give correct bs-type parameters...");
    }

    public String  jtype(String instruction){
        String buyruk_adresi = instruction.split(" ")[0];
        String buyruk = instruction.split(" ")[1];
        String hedef_yazmaci = instruction.split(" ")[2];
        BigInteger bi = new BigInteger(instruction.split(" ")[3], 16);
        int anlik_deger=bi.intValue();
        //int anlik_deger = Integer.parseInt(instruction.split(" ")[3]);

        if(buyruk.equals("jal")){
            register.setRegister(hedef_yazmaci, Integer.decode(buyruk_adresi) + 4);
            //System.out.println("buyruk_adresi: " + buyruk_adresi);
            //System.out.println("anlik_deger: " + anlik_deger);
            return "0x"+Integer.toHexString(Integer.decode(buyruk_adresi) + anlik_deger*2);
        }
        System.out.println("Please give the correct jtype parameters...");
        return "Please give the correct jtype parameters...";
    }
}