package com.marcos.angel.binaryformat.fragment;

/**
 * Created by Angel on 03/09/2017.
 */

public class Operators {


    public String negation(String a){
        String result="";
        char[] b = a.toCharArray();
        for(int i=0;i<b.length;i++){
            if(b[i]=='1'){
                b[i]='0';
            }else{
                b[i]='1';
            }
            result+=b[i];
        }

        return result;
    }

    public String and(String a, Integer key){
        String result="";
        String tmp=""+Integer.toBinaryString(key);
        char[] c = a.toCharArray();
        if(tmp.length()<c.length){
            int tmpi=tmp.length();
            tmp="";
            for(int i=0;i<c.length-tmpi;i++){
                tmp+="0";
            }
        }
        tmp+=Integer.toBinaryString(key);
        char[] b= tmp.toString().toCharArray();
        for(int i=0;i<c.length;i++){
            if(c[i]=='1' && b[i]=='1'){
                c[i]='1';
            }else{
                c[i]='0';
            }
            result+=c[i];
        }
        return result;
    }

    public String implicitOr(String a, Integer key){
        String result="";
        String tmp=""+Integer.toBinaryString(key);
        char[]c = a.toCharArray();
        if(tmp.length()<c.length){
            int tmpi=tmp.length();
            tmp="";
            for(int i=0;i<c.length-tmpi;i++){
                tmp+="0";
            }
        }
        tmp+=Integer.toBinaryString(key);
        char[] b= tmp.toString().toCharArray();
        for(int i=0;i<c.length;i++){
            if(c[i]=='0'&& b[i]=='0'){
                c[i]='0';
            }else{
                c[i]='1';
            }
            result+=c[i];
        }
        return result;
    }

    public String xor(String a, Integer key){
        String result="";
        String tmp=""+Integer.toBinaryString(key);
        char[] c = a.toCharArray();
        if(tmp.length()<c.length){
            int tmpi=tmp.length();
            tmp="";
            for(int i=0;i<c.length-tmpi;i++){
                tmp+="0";
            }
        }
        tmp+=Integer.toBinaryString(key);
        char[] b= tmp.toString().toCharArray();
        for(int i=0;i<c.length;i++){
            if((c[i]=='1' && b[i]=='1')||(c[i]=='0' && b[i]=='0')){
                c[i]='0';
            }else{
                c[i]='1';
            }
            result+=c[i];
        }
        return result;
    }

    public String xor(String a, String key){
        String result="";
        char[] b= key.toString().toCharArray();
        char[] c = a.toCharArray();
        for(int i=0;i<c.length;i++){
            if((c[i]=='1' && b[i]=='1')||(c[i]=='0' && b[i]=='0')){
                c[i]='0';
            }else{
                c[i]='1';
            }
            result+=c[i];
        }
        return result;
    }
}
