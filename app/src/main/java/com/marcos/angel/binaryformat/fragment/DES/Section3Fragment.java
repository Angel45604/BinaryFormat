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

public class Section3Fragment extends Fragment {

    private static final String TAG = "Section3Fragment";
    private Binarization binarization;
    private DES des;
    private TextInputEditText plainText;
    private Button button;



    public Section3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_section3, container, false);

        plainText = (TextInputEditText) inflate.findViewById(R.id.plainText);
        button = (Button) inflate.findViewById(R.id.btnDES);

        binarization = new Binarization();
        des = new DES();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String binarizedText= des.set64Bits(binarization.Binarization(plainText.getText().toString()));
                String[] binarizedText64 = new String[binarizedText.length()/64];
                String[][] ks = new String[binarizedText64.length][des.getITERATIONScount()];
                Log.d(TAG,binarizedText);
                Log.d(TAG,"TAMANO "+binarizedText64.length);

                for(int i=0;i<binarizedText64.length;i++){
                    binarizedText64[i]=binarizedText.substring(i*64,(i+1)*64);
                    des.setC(des.permutedchoice1(binarizedText64[i]));

                    //KS
                    for(int j=0;j<des.getITERATIONScount();j++){
                        String cdx= des.rotation(des.getC(),j);
                        Log.d(TAG,"pt"+i+" C"+(j+1)+": "+cdx);
                        ks[i][j]=des.permutedchoice2(cdx);
                        Log.d(TAG,"pt"+i+" K"+(j+1)+": "+des.permutedchoice2(cdx));
                        des.setC(cdx);
                    }


                    Log.d(TAG,"-");
                }
            }
        });
        return inflate;
    }


}
