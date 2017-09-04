package com.marcos.angel.binaryformat.fragment;

import android.content.Context;
import android.net.Uri;
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

import com.marcos.angel.binaryformat.R;

public class Section2Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Section2Fragment";
    private static final int[] idKs = {R.id.k1,R.id.k2,R.id.k3,R.id.k4,R.id.k5,R.id.k6,R.id.k7,R.id.k8,R.id.k9,R.id.k10,R.id.k11,R.id.k12,R.id.k13,R.id.k14,R.id.k15,R.id.k16};
    private static final int[] idOs = {R.id.o1,R.id.o2,R.id.o3,R.id.o4,R.id.o5,R.id.o6,R.id.o7,R.id.o8,R.id.o9,R.id.o10,R.id.o11,R.id.o12,R.id.o13,R.id.o14,R.id.o15,R.id.o16};
    private String number;
    private int[] keys;
    private Integer[] keysNumber;
    private TextInputEditText k1,k2,k3,k4,k5,k6,k7,k8,k9,k10,k11,k12,k13,k14,k15,k16,binaryNumber;
    private TextInputEditText[] ks;
    private Operators operators;
    private Button btnCifrar;
    private Spinner o1,o2,o3,o4,o5,o6,o7,o8,o9,o10,o11,o12,o13,o14,o15,o16;
    private Spinner[] options;
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
                String binaryNum = binaryNumber.getText().toString();
                Log.d(TAG," "+binaryNum);
                for(int i=0;i<options.length;i++){
                    switch(keys[i]){
                        case 0:
                            binaryNum = operators.negation(binaryNum);
                            Log.d(TAG,"ITERACION "+i+" "+binaryNum);
                            break;
                        case 1:
                            binaryNum = operators.and(binaryNum,keysNumber[i]);
                            Log.d(TAG,"ITERACION "+i+" "+binaryNum);

                            break;
                        case 2:
                            binaryNum=operators.implicitOr(binaryNum,keysNumber[i]);
                            Log.d(TAG,"ITERACION "+i+" "+binaryNum);

                            break;
                        case 3:
                            binaryNum=operators.xor(binaryNum,keysNumber[i]);
                            Log.d(TAG,"ITERACION "+i+" "+binaryNum);

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
                        ks[j].setEnabled(false);
                        keys[j]=0;
                        keysNumber[j]=null;
                    }
                }
                break;
            case 1:
                for(int j=0;j<options.length;j++){
                    if(options[j].getId()==id){
                        keysNumber[j]=Integer.parseInt(ks[j].getText().toString());
                        keys[j]=1;
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
                        keysNumber[j]=Integer.parseInt(ks[j].getText().toString());
                        keys[j]=3;
                    }
                }
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
