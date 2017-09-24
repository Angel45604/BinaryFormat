package com.marcos.angel.binaryformat.fragment.DES;

import android.util.Log;

/**
 * Created by angel on 23/09/2017.
 */

public class DES {

    public static final int[] PERMUTEDTABLE1={
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,

            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };

    public static final int[] PERMUTEDTABLE2={
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };

    public static final int[] INITIALPERMUTATIONTABLE={
            58,	50,	42,	34,	26,	18,	10,	2,
            60,	52,	44,	36,	28,	20,	12,	4,
            62,	54,	46,	38,	30,	22,	14,	6,
            64,	56,	48,	40,	32,	24,	16,	8,
            57,	49,	41,	33,	25,	17,	9,	1,
            59,	51,	43,	35,	27,	19,	11,	3,
            61,	53,	45,	37,	29,	21,	13,	5,
            63,	55,	47,	39,	31,	23,	15,	7,
    };

    public static final int[] ITERATIONS={1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    public String c;

    public DES(){

    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getITERATIONScount(){
        return ITERATIONS.length;
    }

    public String permutedchoice1(String unpermutedString){
        String permutedString="";
        for(int i=0;i<PERMUTEDTABLE1.length;i++){
            permutedString+=unpermutedString.substring(PERMUTEDTABLE1[i]-1,PERMUTEDTABLE1[i]);
        }

        return permutedString;
    }

    public String permutedchoice2(String permutedString){
        String tmp="";
        for(int i=0;i<PERMUTEDTABLE2.length;i++){
            tmp+=permutedString.substring(PERMUTEDTABLE2[i]-1,PERMUTEDTABLE2[i]);
        }
        return tmp;
    }

    public String initialPermutation(String binarizedString){
        String permutedString="";
        for(int i=0;i<INITIALPERMUTATIONTABLE.length;i++){
            permutedString+=binarizedString.substring(INITIALPERMUTATIONTABLE[i]-1,INITIALPERMUTATIONTABLE[i]);
        }
        return permutedString;
    }

    public String rotation(String permutedString, int iteration){
        String subC=permutedString.substring(0,permutedString.length()/2);
        String subD=permutedString.substring(permutedString.length()/2,permutedString.length());
        String i1=subC.substring(0,ITERATIONS[iteration]);
        String i2=subD.substring(0,ITERATIONS[iteration]);
        subC=subC.substring(ITERATIONS[iteration],subC.length());
        subC+=i1;
        subD=subD.substring(ITERATIONS[iteration],subD.length());
        subD+=i2;

        return subC+subD;
    }

    /**
     *
     * @param text binarized text
     * @return return The text in multiples of 64 separated with "|"
     */
    public String set64Bits(String text){
        text=text.replaceAll("\\s+","");
        String newText=text;
        String tmp="";
        while(newText.length()%64!=0){
            newText+="0";
        }
        return newText;
    }

}
