package com.marcos.angel.binaryformat.fragment.DES;

import android.util.Log;

import com.marcos.angel.binaryformat.fragment.logicOperators.Operators;

/**
 * Created by angel on 23/09/2017.
 */

public class DES {
    private Operators operators = new Operators();
    private Binarization binarization = new Binarization();

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

    public static final int[] FINALPERMUTATIONTABLE={
            40,	8,	48,	16,	56,	24,	64,	32,
            39,	7,	47,	15,	55,	23,	63,	31,
            38,	6,	46,	14,	54,	22,	62,	30,
            37,	5,	45,	13,	53,	21,	61,	29,
            36,	4,	44,	12,	52,	20,	60,	28,
            35,	3,	43,	11,	51,	19,	59,	27,
            34,	2,	42,	10,	50,	18,	58,	26,
            33,	1,	41,	9,	49,	17,	57,	25,
    };

    public static final int[] EXPANSIONTABLE={
            32,	1,	2,	3,	4,	5,
            4,	5,	6,	7,	8,	9,
            8,	9,	10,	11,	12,	13,
            12,	13,	14,	15,	16,	17,
            16,	17,	18,	19,	20,	21,
            20,	21,	22,	23,	24,	25,
            24,	25,	26,	27,	28,	29,
            28,	29,	30,	31,	32,	1,
    };

    public static final int[] PERMUTATION={
            16,	7,	20,	21,	29,	12,	28,	17,
            1,	15,	23,	26,	5,	18,	31,	10,
            2,	8,	24,	14,	32,	27,	3,	9,
            19,	13,	30,	6,	22,	11,	4,	25,
    };

    public static final int[][][] S ={
            {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            {
                {15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10},
                {3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5},
                {0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15},
                {13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9}
            },
            {
                {10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8},
                {13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1},
                {13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7},
                {1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12}
            },
            {
                {7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15},
                {13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9},
                {10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4},
                {3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14}
            },
            {
                    {2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9},
                    {14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6},
                    {4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14},
                    {11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3}
            },
            {
                    {12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11},
                    {10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8},
                    {9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6},
                    {4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13}
            },
            {
                    {4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1},
                    {13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6},
                    {1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2},
                    {6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12}
            },
            {
                    {13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7},
                    {1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2},
                    {7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8},
                    {2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11}
            }

    };



    public static final int[] ITERATIONS={1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    public String c;

    public String mainBlock;

    public DES(){

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

    public String finalPermutation(String binarizedString){
        String permutedString="";
        for(int i=0;i<FINALPERMUTATIONTABLE.length;i++){
            permutedString+=binarizedString.substring(FINALPERMUTATIONTABLE[i]-1,FINALPERMUTATIONTABLE[i]);
        }
        return permutedString;
    }

    public String expand(String binarizedString){
        String expandedString="";
        for(int i=0;i<EXPANSIONTABLE.length;i++){
            expandedString+=binarizedString.substring(EXPANSIONTABLE[i]-1,EXPANSIONTABLE[i]);
        }
        return expandedString;
    }


    /**
     *
     * @param r The r-side of the mainBlock
     * @param roundKey The iteration key
     * @return The r processed by feistel
     */
    public String feistel(String r, String roundKey){
        String expandedR=expand(r);

        String tmp=operators.xor(expandedR,roundKey);

        String output=sBlock(tmp);

        return output;
    }

    public String sBlock(String bytes){
        Log.d("SBOX","INPUT: "+bytes);
        String output="";
        for(int i=0;i<S.length;i++){
            String sixString = bytes.substring(i*6,(i+1)*6);
            String ab= ""+sixString.charAt(0)+sixString.charAt(sixString.length()-1);
            String med= sixString.substring(1,sixString.length()-1);

            Log.d("SBOX",i+" SIX: "+sixString+" "+ab+" "+med);
            int[] col= S[i][Integer.parseInt(ab,2)];
            Log.d("SBOX","COL: "+S[i][Integer.parseInt(ab,2)]);

            String row =""+ col[Integer.parseInt(med,2)];
            Log.d("SBOX","ROW: "+col[Integer.parseInt(med,2)]);
            row =""+binarization.Binarization4(row);
            Log.d("SBOX","RETURN: "+row);

            output+=row;
        }
        String tmp="";
        for(int i=0;i<PERMUTATION.length;i++){
            tmp+=output.substring(PERMUTATION[i]-1,PERMUTATION[i]);
        }
        Log.d("SBOX","FINALOUTPUT: "+output);
        Log.d("SBOX","P PINAL: "+tmp);

        return tmp;

    }

    /**
     *
     * @param permutedString C/D to rotate
     * @param iteration The iteration
     * @return The String rotated
     */
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
     * @return return the text in multiples of 64
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

    public String getMainBlock() {
        return mainBlock;
    }

    public void setMainBlock(String mainBlock) {
        this.mainBlock = mainBlock;
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

}