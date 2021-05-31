import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class workingArea {

    public static void main(String[] args) {
        /*int val1 = 0x004 + 30;
        String ok = "0x"+Integer.toHexString(val1);
        Integer.decode("0x"+Integer.toHexString(val1));
        System.out.println(ok);
        */
        //String[] ok = "".split(" ",2);
        //System.out.println(ok.length);
        //System.out.println(ok[1]);

        //System.out.println(Integer.decode("0x000c"));

        //System.out.println(Integer.parseInt("10001"));

        int var = -23243;
        String hex = Integer.toHexString(var);

        BigInteger bi = new BigInteger("fffffff4", 16);
        int a=bi.intValue();
        System.out.println(a);


        //System.out.println(hex);

        /*
        String sayı = "-8";
        String hexa = "fffffff4";
        //int hex = Integer.decode("0x" + Integer.toHexString(Integer.parseInt(sayı)));
        String val = Integer.toHexString(Integer.parseInt(sayı));
        System.out.println(Integer.toHexString(Integer.parseInt(sayı)));

         */

        float sayı = (float)15 / (float)90;
        System.out.println(sayı);


    }

}
