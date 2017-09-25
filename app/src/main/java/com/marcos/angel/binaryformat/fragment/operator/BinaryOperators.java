package com.marcos.angel.binaryformat.fragment.operator;

/**
 * Created by Angel on 12/09/2017.
 */

public class BinaryOperators {

    public BinaryOperators(){

    }

    public String toBinary(String num) {
        return""+Long.parseLong(num,2);
    }

    public String toDec(String num){
        return""+Long.toBinaryString(Long.parseLong(num));
    }

}
