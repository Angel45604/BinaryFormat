package com.marcos.angel.binaryformat.fragment.DES;

import android.util.Log;

import java.math.BigInteger;
import java.util.Locale;

/**
 * Created by angel on 23/09/2017.
 */

public class Binarization {

    public Binarization(){

    }

    public String Binarization(String plainText){
        byte[] binary=plainText.getBytes();
        String binaryText = "";
        for (byte b : binary)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryText+=(val & 128) == 0 ? 0 : 1;
                val <<= 1;
            }
            binaryText+=(' ');
        }
        return binaryText;
    }

    public String Binarization4(String plainText){
        String binaryText=Integer.toBinaryString(Integer.parseInt(plainText));
        String tmp="";
        for(int i=0;i<(4-binaryText.length());i++){
            tmp+=0;
        }
        tmp+=binaryText;
        return tmp;
    }

    public String desBinarization(String binarizedText){
        String binaryText="";
        for(int i=0;i<binarizedText.length();i+=8){
            String tmp=binarizedText.substring(i,i+8);
            Log.d("TAMANIODESBINARIZADO ",tmp);
            binaryText+=""+Long.parseLong(tmp,2);
        }
        return binaryText;
    }

     String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

}
