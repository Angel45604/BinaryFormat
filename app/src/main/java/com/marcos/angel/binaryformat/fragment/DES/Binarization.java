package com.marcos.angel.binaryformat.fragment.DES;

import android.util.Log;

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

}
