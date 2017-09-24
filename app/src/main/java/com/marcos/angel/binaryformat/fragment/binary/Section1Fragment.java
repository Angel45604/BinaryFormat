package com.marcos.angel.binaryformat.fragment.binary;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.marcos.angel.binaryformat.R;


public class Section1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Section1Fragment";
    private TextInputEditText dec1,dec2;
    private TextInputLayout lDec1, lDec2;
    private TextView binaryResult, opcResult;
    private SwitchCompat toggle;
    private Button btnSuma, btnResta, btnMult, btnDiv;
    private boolean isTouched = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Section1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Section1Fragment newInstance(String param1, String param2) {
        Section1Fragment fragment = new Section1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private TextWatcher tw = new TextWatcher() {
        public void afterTextChanged(Editable s){
            if(isTouched) {
                try {
                    binaryResult.setText(toBinary(dec1.getText().toString()));
                } catch (Exception e) {
                    binaryResult.setText("");
                    e.printStackTrace();
                }
            }else{
                try {
                    binaryResult.setText(toDec(dec1.getText().toString()));
                }catch(Exception e){
                    binaryResult.setText("");
                    e.printStackTrace();
                }
            }
        }
        public void  beforeTextChanged(CharSequence s, int start, int count, int after){
            // you can check for enter key here
        }
        public void  onTextChanged (CharSequence s, int start, int before,int count) {

        }
    };

    public String toBinary(String num){
        return""+Long.parseLong(num,2);
    }
    public String toDec(String num){
        return""+Long.toBinaryString(Long.parseLong(num));
    }

    public String integerfrmbinary(int number){
        int [] positionNumsArr= {1,2,4,8,16,32,64,128};
        int[] numberSplit = new int [8];
        int count1=0;
        int decimalValue=0;
        while (number > 0)
        {
            numberSplit[count1]=( number % 10);
            if(numberSplit[count1]!=1 && numberSplit[count1] !=0){
                return "Binario Invalido";
            }
            count1++;
            number = number / 10;
        }
        for(int count2 = 0;count2<8;count2++)
        {
            if(numberSplit[count2]==1)
            {
                decimalValue=decimalValue+positionNumsArr[count2];
            }
        }

        return ""+decimalValue;
    }

    public String suma(String numa, String numb){
        long res =0;
        try {
             res = Long.parseLong(numa) + Long.parseLong(numb);
        }catch(Exception e){
            e.printStackTrace();
            return "NUMERO MUY GRANDE";
        }

        if(isTouched){
            String numac= toBinary(numa);
            String numab= toBinary(numb);
            res = Long.parseLong(numac)+Long.parseLong(numab);
            return res+"";

        }else{
            return ""+Long.toBinaryString(res);
        }

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
            if (isTouched) {
                String numac = toBinary(numa);
                String numab = toBinary(numb);
                res = Long.parseLong(numac) - Long.parseLong(numab);
                return res + "";

            } else {
                return""+Long.toBinaryString(res);
            }
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
        if(isTouched){
            try {
                String numac = toBinary(numa);
                String numbc = toBinary(numb);
                res = Long.parseLong(numac) * Long.parseLong(numbc);
                return res + "";
            }catch(Exception e){
                e.printStackTrace();
                return "ERROR";
            }
        }else{
            return ""+Long.toBinaryString(res);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_section1, container, false);

        dec2 = inflate.findViewById(R.id.dec2);
        dec1 = inflate.findViewById(R.id.dec1);
        dec1.addTextChangedListener(tw);
        lDec1 = inflate.findViewById(R.id.lDec1);
        lDec1.setHint("Decimal");
        lDec2 = inflate.findViewById(R.id.lDec2);
        lDec2.setHint("Decimal");
        lDec2 = inflate.findViewById(R.id.lDec2);
        binaryResult = inflate.findViewById(R.id.binaryResult);
        opcResult = inflate.findViewById(R.id.opcResult);

        btnSuma =  inflate.findViewById(R.id.btn1);
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opcResult.setText(suma(dec1.getText().toString(),dec2.getText().toString()));
            }
        });

        btnResta = inflate.findViewById(R.id.btn2);
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dec1.getText().toString()=="" ||dec2.getText().toString()==""){
                    opcResult.setText("LLENA LOS 2 CAMPOS");
                }else {
                    opcResult.setText(resta(dec1.getText().toString(), dec2.getText().toString()));
                }
            }
        });

        btnMult = inflate.findViewById(R.id.btn3);
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dec1.getText().toString()==""||dec2.getText().toString()==""){
                    opcResult.setText("LLENA LOS 2 CAMPOS");
                }else {
                    opcResult.setText(mult(dec1.getText().toString(), dec2.getText().toString()));
                }
            }
        });

        toggle = inflate.findViewById(R.id.toggleDec);
        toggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched = true;
                return false;
            }
        });
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                    if (isChecked) {
                        isTouched = true;
                        toggle.setText("Decimal");
                        lDec1.setHint("Binario");
                        lDec2.setHint("Binario");
                        try {
                            dec1.setText(toDec(dec1.getText().toString()));
                            dec2.setText(toDec(dec2.getText().toString()));
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        isTouched=false;
                        lDec1.setHint("Decimal");
                        lDec2.setHint("Decimal");
                        toggle.setText("Binario");
                        try {
                            dec1.setText(toBinary(dec1.getText().toString()));
                            dec2.setText(toBinary(dec2.getText().toString()));
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
            }
        });

        // Inflate the layout for this fragment
        return inflate;
    }

}
