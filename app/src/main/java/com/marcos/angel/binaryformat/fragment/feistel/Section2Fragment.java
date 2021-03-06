package com.marcos.angel.binaryformat.fragment.feistel;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.marcos.angel.binaryformat.R;
import com.marcos.angel.binaryformat.fragment.logicOperators.Operators;

public class Section2Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Section2Fragment";
    private static final int[] idKs = {R.id.k1,R.id.k2,R.id.k3,R.id.k4,R.id.k5,R.id.k6,R.id.k7,R.id.k8,R.id.k9,R.id.k10,R.id.k11,R.id.k12,R.id.k13,R.id.k14,R.id.k15,R.id.k16};
    private static final int[] idOs = {R.id.o1,R.id.o2,R.id.o3,R.id.o4,R.id.o5,R.id.o6,R.id.o7,R.id.o8,R.id.o9,R.id.o10,R.id.o11,R.id.o12,R.id.o13,R.id.o14,R.id.o15,R.id.o16};
    private int[] keys;
    private Integer[] keysNumber;
    private TextInputEditText binaryNumber;
    private TextInputEditText[] ks;
    private Operators operators;
    private Button btnCifrar;
    private Spinner[] options;
    private TextView cResultT;

    public Section2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_section2, container, false);
        binaryNumber = v.findViewById(R.id.binaryNumber);
        keys = new int[16];
        keysNumber = new Integer[16];
        ks= new TextInputEditText[16];
        options = new Spinner[16];
        operators= new Operators();
        cResultT = v.findViewById(R.id.cResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.operators_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        for(int i=0;i<ks.length;i++){
            ks[i] = v.findViewById(idKs[i]);
            options[i] = v.findViewById(idOs[i]);
            options[i].setAdapter(adapter);
            options[i].setOnItemSelectedListener(this);
        }

        btnCifrar = v.findViewById(R.id.btnCifrar);

        btnCifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < keys.length; i++) {
                    if (options[i].getSelectedItem().equals("NOT")) {
                        keysNumber[i] = null;
                    } else {
                        try {
                            keys[i]=(int)options[i].getSelectedItemId();
                            keysNumber[i] = Integer.parseInt(ks[i].getText().toString());
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }


                String cResult = "";
                String binaryNum = binaryNumber.getText().toString();
                String binaryNuma="";
                String binaryNumb="";
                if (binaryNum.equals("")) {

                } else {
                    if(binaryNum.length()<16){
                        if(binaryNum.length()>8){
                            int tmp = binaryNum.length();
                            binaryNum="";
                            for(int i=0;i<16-tmp;i++){
                                binaryNum+="0";
                            }
                            binaryNum+=binaryNumber.getText().toString();
                            binaryNuma=binaryNum.substring(0,binaryNum.length()/2);
                            binaryNumb=binaryNum.substring(binaryNum.length()/2,binaryNum.length());
                        }else{
                            int tmp=binaryNum.length();
                            binaryNum="";
                            for(int i=0;i<8-tmp;i++){
                                binaryNum+="0";
                            }
                            binaryNum+=binaryNumber.getText().toString();
                        }
                    }else{
                        binaryNum=binaryNumber.getText().toString();
                        binaryNuma=binaryNum.substring(0,binaryNum.length()/2);
                        binaryNumb=binaryNum.substring(binaryNum.length()/2,binaryNum.length());
                    }
                    String binA = binaryNum.substring(0, binaryNum.length()/2);
                    String firstB = binaryNum.substring(binaryNum.length()/2, binaryNum.length());
                    String binB = binaryNum.substring(binaryNum.length()/2, binaryNum.length());
                    if(binaryNuma.equals("")) {
                        for (int i = 0; i < options.length; i++) {
                            switch (keys[i]) {
                                case 0:
                                    binB = operators.negation(binB);
                                    cResult += "NOT  " + binB + "\n";
                                    binB = operators.xor(binB, binA);
                                    cResult += "xor  " + binB + "\n";
                                    binaryNum = firstB + binB;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binA = firstB;
                                    firstB = binB;
                                    break;
                                case 1:
                                    binB = operators.and(binB, keysNumber[i]);
                                    cResult += "AND  " + keysNumber[i] + binB + "\n";
                                    binB = operators.xor(binB, binA);
                                    cResult += "xor  " + binB + "\n";
                                    binaryNum = firstB + binB;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binA = firstB;
                                    firstB = binB;
                                    break;
                                case 2:
                                    binB = operators.implicitOr(binB, keysNumber[i]);
                                    cResult += "OR   " + keysNumber[i] + binB + "\n";
                                    binB = operators.xor(binB, binA);
                                    cResult += "xor  " + binB + "\n";
                                    binaryNum = firstB + binB;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binA = firstB;
                                    firstB = binB;
                                    break;
                                case 3:
                                    binB = operators.xor(binB, keysNumber[i]);
                                    cResult += "XOR  " + keysNumber[i] + binB + "\n";
                                    binB = operators.xor(binB, binA);
                                    cResult += "xor  " + binB + "\n";
                                    binaryNum = firstB + binB;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binA = firstB;
                                    firstB = binB;
                                    break;
                            }
                        }
                        binA = binaryNum.substring(0, binaryNum.length() / 2);
                        binB = binaryNum.substring(binaryNum.length() / 2, binaryNum.length());
                        binaryNum = binB + binA;
                        cResult += binaryNum + "\n";
                        cResultT.setText(cResult);
                    }else{
                        String b1="";
                        String b2="";
                        int k1=0;
                        int k2=0;
                        String firstB1="";
                        binA=binaryNumb.substring(0,binaryNumb.length()/2);
                        binB=binaryNumb.substring(binaryNumb.length()/2,binaryNumb.length());
                        firstB1=binaryNumb;
                        for (int i = 0; i < options.length; i++) {
                            switch (keys[i]) {
                                case 0:
                                    binaryNumb = operators.negation(binaryNumb);
                                    cResult += "NOT  " + binaryNumb + "\n";
                                    binaryNumb = operators.xor(binaryNumb, binaryNuma);
                                    cResult += "xor  " + binaryNumb + "\n";
                                    binaryNum = firstB1 + binaryNumb;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binaryNuma = firstB1;
                                    firstB1 = binaryNumb;
                                    break;
                                case 1:
                                    b1 ="";
                                    b2="";
                                    k1= Integer.parseInt(keysNumber[i].toString().substring(0,1));
                                    k2= Integer.parseInt(keysNumber[i].toString().substring(1,2));
                                    b1=binaryNumb.substring(0,binaryNumb.length()/2);
                                    b2=binaryNumb.substring(binaryNumb.length()/2,binaryNumb.length());

                                    b1 = operators.and(b1, k1);
                                    b2=operators.and(b2,k2);
                                    binaryNumb=b1+b2;
                                    cResult += "AND  " + keysNumber[i] + binaryNumb + "\n";
                                    binaryNumb = operators.xor(binaryNumb, binaryNuma);
                                    cResult += "xor  " + binaryNumb + "\n";
                                    binaryNum = firstB1 + binaryNumb;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binaryNuma = firstB1;
                                    firstB1 = binaryNumb;
                                    break;
                                case 2:
                                    b1 ="";
                                    b2="";
                                    k1= Integer.parseInt(keysNumber[i].toString().substring(0,1));
                                    k2= Integer.parseInt(keysNumber[i].toString().substring(1,2));
                                    b1=binaryNumb.substring(0,binaryNumb.length()/2);
                                    b2=binaryNumb.substring(binaryNumb.length()/2,binaryNumb.length());
                                    b1=operators.implicitOr(b1,k1);
                                    b2=operators.implicitOr(b2,k2);
                                    binaryNumb=b1+b2;
                                    cResult += "OR   " + keysNumber[i] + binaryNumb+ "\n";
                                    binaryNumb = operators.xor(binaryNumb, binaryNuma);
                                    cResult += "xor  " + binaryNumb + "\n";
                                    binaryNum = firstB1 + binaryNumb;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binaryNuma = firstB1;
                                    firstB1 = binaryNumb;
                                    break;
                                case 3:
                                    b1 ="";
                                    b2="";
                                    k1= Integer.parseInt(keysNumber[i].toString().substring(0,1));
                                    k2= Integer.parseInt(keysNumber[i].toString().substring(1,2));
                                    b1=binaryNumb.substring(0,binaryNumb.length()/2);
                                    b2=binaryNumb.substring(binaryNumb.length()/2,binaryNumb.length());

                                    b1=operators.xor(b1,k1);
                                    b2=operators.xor(b2,k2);
                                    binaryNumb=b1+b2;
                                    cResult += "XOR  " + keysNumber[i] + binaryNumb + "\n";
                                    binaryNumb = operators.xor(binaryNumb, binaryNuma);
                                    cResult += "xor  " + binaryNumb + "\n";
                                    binaryNum = firstB1 + binaryNumb;
                                    cResult += binaryNum + "\n";
                                    cResult+="----------------\n";
                                    binaryNuma = firstB1;
                                    firstB1 = binaryNumb;
                                    break;
                            }
                        }
                        binaryNuma = binaryNum.substring(0, binaryNum.length() / 2);
                        binaryNumb = binaryNum.substring(binaryNum.length() / 2, binaryNum.length());
                        binaryNum = binaryNumb + binaryNuma;
                        cResult += binaryNum + "\n";
                        cResultT.setText(cResult);
                    }
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int id= adapterView.getId();
        int item = (int)adapterView.getSelectedItemId();
        Log.d(TAG,""+id+" "+item);
        switch(item){
            case 0:
                for(int j=0;i<options.length;i++){
                    if(options[j].getId()==id){
                        keys[j]=0;
                        keysNumber[j]=null;
                    }
                }
                break;
            case 1:
                for(int j=0;j<options.length;j++){
                    if(options[j].getId()==id){
                        try {
                            keysNumber[j] = Integer.parseInt(ks[j].getText().toString());
                            keys[j] = 1;
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 2:
                for(int j=0;j<options.length;j++){
                    if(options[j].getId()==id){
                        try {
                            keysNumber[j] = Integer.parseInt(ks[j].getText().toString());
                            keys[j] = 2;
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 3:
                for(int j=0;j<options.length;j++){
                    if(options[j].getId()==id){
                        try {
                            keysNumber[j] = Integer.parseInt(ks[j].getText().toString());
                            keys[j] = 3;
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
