package com.marcos.angel.binaryformat.fragment.DES;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.marcos.angel.binaryformat.R;
import com.marcos.angel.binaryformat.fragment.logicOperators.Operators;

public class Section3Fragment extends Fragment {

    private static final String TAG = "Section3Fragment";
    private Binarization binarization;
    private DES des;
    private TextInputEditText plainText, keyText;
    private Button button;
    private Operators operators;



    public Section3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_section3, container, false);

        plainText = (TextInputEditText) inflate.findViewById(R.id.plainText);
        keyText = (TextInputEditText) inflate.findViewById(R.id.cipherKey);
        button = (Button) inflate.findViewById(R.id.btnDES);

        binarization = new Binarization();
        des = new DES();
        operators = new Operators();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cipherText="";
                String descipherText="";
                String binarizedText= "0000000100100011010001010110011110001001101010111100110111101111";
                String binarizedKey = "0001001100110100010101110111100110011011101111001101111111110001";
                String[] binarizedText64 = new String[binarizedText.length()/64];
                String[] ks = new String[des.getITERATIONScount()];
                Log.d(TAG,binarizedText);
                Log.d(TAG,"TAMANO "+binarizedText64.length);

                //c/u64
                for(int i=0;i<binarizedText64.length;i++){
                    binarizedText64[i]=binarizedText.substring(i*64,(i+1)*64);
                    des.setMainBlock(des.initialPermutation(binarizedText64[i]));
                    Log.d(TAG,"KEY: "+binarizedKey);
                    Log.d(TAG,"IP: "+des.getMainBlock());
                    des.setC(des.permutedchoice1(binarizedKey));

                    //KS
                    for(int j=0;j<des.getITERATIONScount();j++){
                        String cdx= des.rotation(des.getC(),j);
                        Log.d(TAG,"pt"+i+" C"+(j+1)+": "+cdx);
                        ks[j]=des.permutedchoice2(cdx);
                        Log.d(TAG,"pt"+i+" K"+(j+1)+": "+des.permutedchoice2(cdx));
                        des.setC(cdx);
                    }

                    //Process
                    for(int j=0;j<ks.length;j++){
                        String mainBlockx=des.getMainBlock();
                        Log.d(TAG,"MAINBLOCK: "+mainBlockx);
                        String l0="";
                        String r0="";
                        if(j==0) {
                            l0 = mainBlockx.substring(0, mainBlockx.length() / 2);
                            r0 = mainBlockx.substring(mainBlockx.length() / 2, mainBlockx.length());
                        }else{
                            l0=operators.xor(l0,des.feistel(r0,ks[j]));
                        }
                        Log.d(TAG,"L: "+l0+ " R: "+r0);

                        des.setMainBlock(r0+operators.xor(l0,des.feistel(r0,ks[j])));
                    }
                    des.setMainBlock(des.finalPermutation(des.getMainBlock()));
                    Log.d(TAG,"64CIFRADO: "+des.getMainBlock());
                    cipherText+=des.getMainBlock();
                    Log.d(TAG,"-");
                }
                Log.d(TAG,"FINAL: "+binarization.desBinarization(cipherText));

                for(int i=0;i<binarizedText64.length;i++){
                    binarizedText64[i]=cipherText.substring(i*64,(i+1)*64);
                    des.setMainBlock(des.initialPermutation(binarizedText64[i]));
                    for(int j=ks.length-1;j>=0;j--){
                        Log.d(TAG,"DES"+i+" K"+(j+1)+": "+ks[j]);
                        String mainBlockx=des.getMainBlock();
                        String suba= mainBlockx.substring(0,mainBlockx.length()/2);
                        String subb1= mainBlockx.substring(mainBlockx.length()/2,mainBlockx.length());

                        String subb2=des.feistel(subb1,ks[j]);

                        des.setMainBlock(subb1+operators.xor(subb2,suba));
                    }
                    des.setMainBlock(des.finalPermutation(des.getMainBlock()));
                    Log.d(TAG,"64DESCIFRADO: "+des.getMainBlock());
                    descipherText+=des.getMainBlock();
                    Log.d(TAG,"-");
                }
                Log.d(TAG,"FINAL DESCIFRADO: "+binarization.desBinarization(descipherText));

            }
        });
        return inflate;
    }

    public void cipher(){

    }


}