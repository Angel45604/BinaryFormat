package com.marcos.angel.binaryformat.fragment.operator;

/**
 * Created by Angel on 12/09/2017.
 */

public class SimpleOperators {

    public String suma(String numa, String numb){
        long res =0;
        try {
            res = Long.parseLong(numa) + Long.parseLong(numb);
        }catch(Exception e){
            e.printStackTrace();
            return "NUMERO MUY GRANDE";
        }
        return ""+Long.toBinaryString(res);
    }
    public String resta(String numa, String numb){
        long res=0;
        try {
            res = Long.parseLong(numa) - Long.parseLong(numb);
        }catch(Exception e){
            e.printStackTrace();
            return "NUMERO MUY GRANDE";
        }
        if(res<0){
            return "NEGATIVO";
        }else {
            return""+Long.toBinaryString(res);
        }
    }

    public String mult(String numa, String numb){
        long res=0;
        try {
            res = Long.parseLong(numa) * Long.parseLong(numb);
        }catch(Exception e){
            e.printStackTrace();
            return "NUMERO MUY GRANDE";
        }
        return ""+Long.toBinaryString(res);
    }

}
