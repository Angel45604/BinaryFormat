package com.marcos.angel.binaryformat.fragment.mask;

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
import android.widget.TextView;

import com.marcos.angel.binaryformat.R;
import com.marcos.angel.binaryformat.fragment.DES.Binarization;

public class Section4Fragment extends Fragment {
    TextInputEditText networkIP, netsNumber;
    Button btnNetear;
    TextView netsList;
    Binarization binarization;

    public Section4Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_section4, container, false);
        networkIP = (TextInputEditText) inflate.findViewById(R.id.network);
        netsNumber = (TextInputEditText) inflate.findViewById(R.id.netsNumber);
        btnNetear = (Button) inflate.findViewById(R.id.btnNetear);
        netsList = (TextView) inflate.findViewById(R.id.netResult);
        binarization= new Binarization();

        btnNetear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String net = networkIP.getText().toString();
                int numberNets = Integer.parseInt(netsNumber.getText().toString());
                String weas="";
                int paso1=0;
                int m=0;
                int h=0;
                String[] nets = net.split("\\.");
                String[] binNets = new String[nets.length];
                String binNetsN="";
                Log.d("binarynet","HOLA PABLO"+nets[0]);

                if(Integer.parseInt(nets[0])<=127){
                    netsList.setText("Clase a");
                    for(int i=0;i<binNets.length;i++){
                        if(i>0) {
                            binNets[i] = "00000000";
                        }
                        else{
                            binNets[i]="11111111";
                        }
                        binNetsN+=binNets[i];

                    }
                    int a=0;
                    String tmp="";
                    while(true){
                        if((Math.pow(2,a)-2)>=numberNets) {
                            paso1=a;

                            for(int i=0;i<8;i++){
                                 if(i<paso1){
                                     tmp+="1";
                                 }
                                 else{
                                     m++;
                                     tmp+="0";
                                 }
                            }
                            binNets[1]=tmp;
                            break;
                        }
                        a++;
                    }
                    binNets[1]=tmp;
                    int paso2=256-Integer.parseInt(binarization.desBinarization(binNets[1]));
                    binNetsN="";
                    for(int i=0;i<binNets.length;i++){
                        if(i>0) {
                            binNets[i] = "00000000";
                        }
                        else{
                            binNets[i]="11111111";
                        }
                        binNetsN+=binNets[i];

                    }
                    m=(int)Math.pow(2,h)-2;
                    Log.d("OLA","paso1 "+paso1+" H: "+h+ "NETS: "+nets[1]+" paso2: "+paso2+" Numerito: "+binarization.desBinarization(binNets[1]));
                    for(int i=0;i<numberNets;i++){
                        if(i==0){
                            weas+="Subred: "+nets[0]+"."+(Integer.parseInt(nets[1]))+".0.0 Primera Utilizable: "+nets[0]+"."+(Integer.parseInt(nets[1])+1)+".0.0 Ultima utilizable: "+nets[0]+"."+(Integer.parseInt(nets[1])+paso2-2)+".255.255 Broadcast: "+nets[0]+"."+(Integer.parseInt(nets[1])+paso2-1)+".0.0\n";
                        }else {
                            weas += "Subred: " + nets[0] + "." + (Integer.parseInt(nets[1]) + (i * paso2)) + ".0.0 Primera Utilizable: " + nets[0] + "." + (Integer.parseInt(nets[1]) + (i * paso2)+1) + ".0.0 Ultima utilizable: " + nets[0] + "." + (Integer.parseInt(nets[1]) + (i * paso2)+paso2 - 2) + ".255.255 Broadcast: " + nets[0] + "." + (Integer.parseInt(nets[1]) + (i * paso2)+paso2 - 1) + ".0.0\n";
                        }
                    }
                    netsList.setText(weas);


                }else if(Integer.parseInt(nets[0])<=191){
                    netsList.setText("Clase b");
                    for(int i=0;i<binNets.length;i++){
                        if(i>1) {
                            binNets[i] = "00000000";
                        }
                        else{
                            binNets[i]="11111111";
                        }
                    }
                    int a=0;
                    String tmp="";
                    while(true){
                        if((Math.pow(2,a)-2)>=numberNets) {
                            paso1=a;

                            for(int i=0;i<8;i++){
                                if(i<paso1){
                                    tmp+="1";
                                }
                                else{
                                    m++;
                                    tmp+="0";
                                }
                            }
                            binNets[2]=tmp;
                            break;
                        }
                        a++;
                    }
                    binNets[2]=tmp;
                    int paso2=256-Integer.parseInt(binarization.desBinarization(binNets[2]));
                    binNetsN="";
                    for(int i=0;i<binNets.length;i++){
                        if(i>1) {
                            binNets[i] = "00000000";
                        }
                        else{
                            binNets[i]="11111111";
                        }
                        binNetsN+=binNets[i];

                    }
                    m=(int)Math.pow(2,h)-2;
                    Log.d("OLA","paso1 "+paso1+" H: "+h+ "NETS: "+nets[2]+" paso2: "+paso2+" Numerito: "+binarization.desBinarization(binNets[2]));
                    for(int i=0;i<numberNets;i++){
                        if(i==0){
                            weas+="Subred: "+nets[0]+"."+nets[1]+"."+(Integer.parseInt(nets[2]))+".0 Primera Utilizable: "+nets[0]+"."+nets[1]+"."+(Integer.parseInt(nets[2])+1)+".0 Ultima utilizable: "+nets[0]+"."+nets[1]+"."+(Integer.parseInt(nets[2])+paso2-2)+".255 Broadcast: "+nets[0]+"."+nets[1]+"."+(Integer.parseInt(nets[2])+paso2-1)+".0\n";
                        }else {
                            weas += "Subred: " + nets[0]+"."+nets[1] + "." + (Integer.parseInt(nets[2]) + (i * paso2)) + ".0 Primera Utilizable: " + nets[0]+"."+nets[1] + "." + (Integer.parseInt(nets[2]) + (i * paso2)+1) + ".0 Ultima utilizable: " + nets[0]+"."+nets[1] + "." + (Integer.parseInt(nets[2]) + (i * paso2)+paso2 - 2) + ".255 Broadcast: " + nets[0]+"."+nets[1] + "." + (Integer.parseInt(nets[2]) + (i * paso2)+paso2 - 1) + ".0\n";
                        }
                    }
                    netsList.setText(weas);
                }else{
                    netsList.setText("Clase c");
                    for(int i=0;i<binNets.length;i++){
                        if(i>2) {
                            binNets[i] = "00000000";
                        }
                        else{
                            binNets[i]="11111111";
                        }
                    }
                    int a=0;
                    String tmp="";
                    while(true){
                        if((Math.pow(2,a)-2)>=numberNets) {
                            paso1=a;
                            Log.d("AIGUAL","A: "+a);

                            for(int i=0;i<8;i++){
                                if(i<paso1){
                                    tmp+="1";
                                }
                                else{
                                    m++;
                                    tmp+="0";
                                }
                            }
                            binNets[3]=tmp;
                            break;
                        }
                        a++;
                    }
                    binNets[3]=tmp;
                    int paso2=256-Integer.parseInt(binarization.desBinarization(binNets[3]));
                    binNetsN="";
                    for(int i=0;i<binNets.length;i++){
                        if(i>2) {
                            binNets[i] = "00000000";
                        }
                        else{
                            binNets[i]="11111111";
                        }
                        binNetsN+=binNets[i];

                    }
                    m=(int)Math.pow(2,h)-2;
                    Log.d("OLA","paso1 "+paso1+" H: "+h+ "NETS: "+nets[3]+" paso2: "+paso2+" Numerito: "+binarization.desBinarization(binNets[3]));
                    for(int i=0;i<numberNets;i++){
                        if(i==0){
                            weas+="Subred: "+nets[0]+"."+nets[1]+"."+nets[2]+"."+(Integer.parseInt(nets[3]))+" Primera Utilizable: "+nets[0]+"."+nets[1]+"."+nets[2]+"."+(Integer.parseInt(nets[3])+1)+" Ultima utilizable: "+nets[0]+"."+nets[1]+"."+nets[2]+"."+(Integer.parseInt(nets[3])+paso2-2)+".255 Broadcast: "+nets[0]+"."+nets[1]+"."+nets[2]+"."+(Integer.parseInt(nets[3])+paso2-1)+"\n";
                        }else {
                            weas += "Subred: " + nets[0]+"."+nets[1] + "."+nets[2]+"." + (Integer.parseInt(nets[3]) + (i * paso2)) + " Primera Utilizable: " + nets[0]+"."+nets[1] + "."+nets[2]+"." + (Integer.parseInt(nets[3]) + (i * paso2)+1) + " Ultima utilizable: " + nets[0]+"."+nets[1] + "."+nets[2]+"." + (Integer.parseInt(nets[3]) + (i * paso2)+paso2 - 2) + " Broadcast: " + nets[0]+"."+nets[1] + "."+nets[2]+"." + (Integer.parseInt(nets[3]) + (i * paso2)+paso2 - 1) + "\n";
                        }
                    }
                    netsList.setText(weas);
                }
                for(int i=0;i<binNets.length;i++){
                    Log.d("NETS",binNets[i]);
                }
            }
        });

        // Inflate the layout for this fragment
        return inflate;
    }
}
