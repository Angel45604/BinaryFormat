package com.marcos.angel.binaryformat.fragment.DES;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.marcos.angel.binaryformat.R;
import com.marcos.angel.binaryformat.fragment.logicOperators.Operators;

public class Section3Fragment extends Fragment {

    private static final String TAG = "Section3Fragment";
    private Binarization binarization;
    private DES des;
    private TextInputEditText plainText, keyText;
    private Button button;
    private Operators operators;
    private SwitchCompat cipherMode,  toggleCipher;



    public Section3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_section3, container, false);

        plainText = (TextInputEditText) inflate.findViewById(R.id.plainText);
        keyText = (TextInputEditText) inflate.findViewById(R.id.cipherKey);
        button = (Button) inflate.findViewById(R.id.btnDES);
        cipherMode = (SwitchCompat) inflate.findViewById(R.id.cipherMode);
        cipherMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cipherMode.setText("ASCII");
                }else{
                    cipherMode.setText("HEX");
                }
            }
        });

        toggleCipher = (SwitchCompat) inflate.findViewById(R.id.toggleCipher);
        toggleCipher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    toggleCipher.setText("Descifrar");
                }else{
                    toggleCipher.setText("Cifrar");
                }
            }
        });

        binarization = new Binarization();
        des = new DES();
        operators = new Operators();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleCipher.isChecked()){
                    cipher(true);
                }else{
                    cipher(false);
                }

            }
        });
        return inflate;
    }

    public void cipher(boolean cip){
        String cipherText=plainText.getText().toString();
        String keytxt=keyText.getText().toString();
        String descipherText="";
        String binarizedText ="";
        String binarizedKey ="";
        if(cipherMode.isChecked()) {
            binarizedText=binarization.Binarization(cipherText);
            binarizedKey=binarization.hexToBin(keytxt);
        }else{
            binarizedText=binarization.hexToBin(cipherText);
            binarizedKey=binarization.hexToBin(keytxt);
        }
        String[] binarizedText64 = new String[binarizedText.length()/64];
        String[] ks = new String[des.getITERATIONScount()];
        Log.d(TAG,binarizedText);
        Log.d(TAG,"TAMANO "+binarizedText.length());

        if(cip) {
            //c/u64
            for (int i = 0; i < binarizedText64.length; i++) {
                binarizedText64[i] = binarizedText.substring(i * 64, (i + 1) * 64);
                des.setMainBlock(des.initialPermutation(binarizedText64[i]));
                Log.d(TAG, "KEY: " + binarizedKey);
                Log.d(TAG, "IP: " + des.getMainBlock());
                des.setC(des.permutedchoice1(binarizedKey));

                //KS
                for (int j = 0; j < des.getITERATIONScount(); j++) {
                    String cdx = des.rotation(des.getC(), j);
                    Log.d(TAG, "pt" + i + " C" + (j + 1) + ": " + cdx);
                    ks[j] = des.permutedchoice2(cdx);
                    Log.d(TAG, "pt" + i + " K" + (j + 1) + ": " + des.permutedchoice2(cdx));
                    des.setC(cdx);
                }
                //Process
                String mainBlocx = des.getMainBlock();
                String l0 = mainBlocx.substring(0, mainBlocx.length() / 2);
                String r0 = mainBlocx.substring(mainBlocx.length() / 2, mainBlocx.length());
                des.setLn_1(l0);
                des.setRn_1(r0);
                for (int j = 0; j < ks.length; j++) {
                    //mainBlocx=des.getMainBlock();
                    String l1 = des.getRn_1();
                    String r1 = operators.xor(des.getLn_1(), des.feistel(des.getRn_1(), ks[j]));
                    Log.d(TAG, "ITERACION " + (j + 1) + " L" + j + "= " + des.getLn_1());
                    Log.d(TAG, "ITERACION " + (j + 1) + " R" + j + "= " + des.getLn_1());
                    des.setLn_1(l1);
                    des.setRn_1(r1);
                    Log.d(TAG, "ITERACION " + (j + 1) + " L" + (j + 1) + "= " + l1);
                    Log.d(TAG, "ITERACION " + (j + 1) + " R" + (j + 1) + "= " + r1);

                    //des.setMainBlock();
                }
                des.setMainBlock(des.finalPermutation(des.getRn_1() + des.getLn_1()));
                int decimal = Integer.parseInt(des.getMainBlock(),2);
                String hexStr = Integer.toString(decimal,16);
                Log.d(TAG, "64CIFRADO: " + hexStr);
                cipherText += des.getMainBlock();
                Log.d(TAG, "-");
            }
            Log.d(TAG, "FINAL: " + binarization.desBinarization(cipherText));
        }else {
            for (int i = 0; i < binarizedText64.length; i++) {
                binarizedText64[i] = cipherText.substring(i * 64, (i + 1) * 64);
                des.setMainBlock(des.initialPermutation(binarizedText64[i]));
                String mainBlocx = des.getMainBlock();
                String l0 = mainBlocx.substring(0, mainBlocx.length() / 2);
                String r0 = mainBlocx.substring(mainBlocx.length() / 2, mainBlocx.length());
                des.setLn_1(l0);
                des.setRn_1(r0);
                for (int j = ks.length - 1; j >= 0; j--) {
                    //mainBlocx=des.getMainBlock();
                    String l1 = des.getRn_1();
                    String r1 = operators.xor(des.getLn_1(), des.feistel(des.getRn_1(), ks[j]));
                    Log.d(TAG, "ITERACION " + (j + 1) + " L" + j + "= " + des.getLn_1());
                    Log.d(TAG, "ITERACION " + (j + 1) + " R" + j + "= " + des.getLn_1());
                    des.setLn_1(l1);
                    des.setRn_1(r1);
                    Log.d(TAG, "ITERACION " + (j + 1) + " L" + (j + 1) + "= " + l1);
                    Log.d(TAG, "ITERACION " + (j + 1) + " R" + (j + 1) + "= " + r1);

                    //des.setMainBlock();
                }
                des.setMainBlock(des.finalPermutation(des.getRn_1() + des.getLn_1()));
                Log.d(TAG, "64DESCIFRADO: " + binarization.desBinarization(des.getMainBlock()));
                descipherText += des.getMainBlock();
                Log.d(TAG, "-");
            }
            Log.d(TAG, "FINAL DESCIFRADO: " + binarization.desBinarization(descipherText));
        }
    }

    public void decipher(){

    }


}