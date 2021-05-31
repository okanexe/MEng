import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class arena {
    public static void main(String[] args){
        System.out.println(12 >> 2);
        System.out.println(31 >> 4);

        System.out.println(12 >>> 4);
        System.out.println(0x020 << 2);

        //final int sayı = 99;
        yazmac reg = new yazmac();


    }

    public ArrayList dosyaOku(String[] args){
        ArrayList<String> dosya = new ArrayList<>();
        try {
            FileInputStream program = new FileInputStream(args[0]);
            BufferedReader prog = new BufferedReader(new InputStreamReader(program));
            try {
                String strLine;
                while ((strLine = prog.readLine()) != null) {
                    if (strLine.length() > 1) { dosya.add(strLine);}
                    else
                        continue;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            program.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dosya;
    }
}


class memory{
    private byte[] hafıza;

    public memory(int boyut){
        hafıza = new byte[boyut];
    }
    public void setMemory(int pc, int deger){
        hafıza[pc] = (byte) (deger & 0x000000FF); // ilk 8 bit
        hafıza[pc+1] = (byte)((deger & 0x0000FF00) >>> 8);
        hafıza[pc+2] = (byte)((deger & 0x00FF0000) >>> 16);
        hafıza[pc+3] = (byte)((deger & 0xFF000000) >>> 24);
    }
    public int get_8bit(int pc){
        return hafıza[pc];
    }
    public int get_32bit(int pc){
        return hafıza[pc] + (hafıza[pc+1] << 8) + (hafıza[pc+2] << 16) + (hafıza[pc+3] << 24);
    }
}
