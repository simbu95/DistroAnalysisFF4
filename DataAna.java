
package dataana;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAna {

    public static void main(String[] args) throws IOException {
        try {
            BufferedReader input = new BufferedReader(new FileReader("Data.txt"));
            String s;
            int Legend=0;
            int Adamant=0;
            int Mist=0;
            int LM=0;
            int AM=0;
            int DProg=0;
            int DProgCPU=0;
            while ((s=input.readLine())!=null){
                String[] KIs=s.split(",");
                int Dmist=Integer.parseInt(input.readLine());
                int KI=Integer.parseInt(KIs[27].trim());
                
                if(Dmist==34 && KI==13){
                    Legend+=1;
                }
                if(Dmist==34 && KI==12){
                    Adamant+=1;
                }
                if(Dmist==34){
                    Mist+=1;
                }
                if(KI==13){
                    LM+=1;
                }
                if(KI==12){
                    AM+=1;
                }
                int[] KIi=new int[28];
                for (int i=0;i<28;i++){
                    KIi[i]=Integer.parseInt(KIs[i].trim());
                }
                if(CheckMist(KIi)){
                    DProg+=1;
                    if(Dmist==34){
                        DProgCPU+=1;
                    }
                }
            }
            System.out.println("Dmist at CPU has legend "+ Legend +" times, and had adamant " +Adamant+" times");
            System.out.println("Dmist was at CPU "+Mist+" times");
            System.out.println("Dmist has legend "+ LM +" times, and had adamant " +AM+" times");
            System.out.println("Dmist had progress " + DProg + " times, and had progress at CPU "+ DProgCPU+ " times");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataAna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    static boolean CheckMist(int[] KI){
        int DMist=0;
        KI[27]=0;
        int[] Checks=new int[28];
        boolean[] KILogic=new boolean[13];
        if(DMist<=11){Checks[27]=1;}
        for(int i=0;i<5;i++){Checks[i]=1;}
        boolean Progress=true;
        boolean Dark=false;
        boolean Leg=false;
        boolean Adam=false;
        while( Progress){
            Progress=false;
        for(int i=0;i<28;i++){
            if(Checks[i]==1){
                switch (KI[i]) {
                    case 0://Nothing
                        Checks[i]=2;
                        break;
                    case 1://Magma
                        Checks[i] = 2;
                        if (DMist > 11 && DMist < 17 && Checks[27] != 2) {
                            Checks[27] = 1;
                        }
                        for (int j = 5; j < 11; j++) {
                            if (Checks[j] != 2) {Checks[j] = 1;}
                        }
                        KILogic[0] = true;
                        Progress=true;
                        break;
                    case 2://Hook
                        Checks[i] = 2;
                        if (DMist > 11 && DMist < 17 && Checks[27] != 2) {
                            Checks[27] = 1;
                        }
                        if ((DMist == 20 || DMist == 21) && Checks[27]!=2) {Checks[27] = 1;}
                        for (int j = 5; j < 11; j++) {
                            if (Checks[j] != 2) {Checks[j] = 1;}
                        }
                        KILogic[1] = true;
                        if (KILogic[2] && Checks[26] != 2) {Checks[26] = 1;}
                        Progress=true;
                        break;
                    case 3://Rat Tail
                        Checks[i] = 2;
                        KILogic[2] = true;
                        if (KILogic[1] && Checks[26] < 1) {
                            Checks[26] = 1;
                            Progress=true;
                        }
                        break;
                    case 4://Darkness
                        if(KILogic[0] || KILogic[1]){
                        Checks[i] = 2;
                        Dark=true;
                        KILogic[3]=true;
                        if (DMist > 26 && Checks[27] != 2) {
                            Checks[27] = 1;
                        }
                        for (int j = 19; j < 26; j++) {
                            if (Checks[j] != 2) {Checks[j] = 1;}
                        }
                        Progress=true;
                        }
                        break;
                    case 5://Baron
                        if ((DMist == 17 || DMist == 18) && Checks[27]!=2) {Checks[27] = 1;}
                        if (Checks[11] <1 ) {Checks[11] = 1;Progress=true;}
                        if(KILogic[0] || KILogic[1]){
                        Checks[i] = 2;
                        KILogic[4]=true;
                        if (DMist == 19 && Checks[27]!=2) {Checks[27] = 1;}
                        if (Checks[12] <1 ) {Checks[12] = 1;Progress=true;}
                        }
                        break;
                    case 6:// Pan
                        KILogic[5]=true;
                        if(KILogic[0] || KILogic[1]){
                            for (int j = 17; j < 19; j++) {
                                if (Checks[j] != 2) {Checks[j] = 1;}
                            }
                            Checks[i]=2;
                            Progress=true;
                        }
                        break;
                    case 7:// tower
                        KILogic[6]=true;
                        if(KILogic[0] || KILogic[1]){
                            if (Checks[16] != 2) {Checks[16] = 1;}
                            Checks[i]=2;
                            if (DMist == 25 && Checks[27]!=2){
                                Checks[27]=1;
                            }
                            Progress=true;
                        }
                        break;
                    case 8: //luca
                        KILogic[6]=true;
                        if(KILogic[0] || KILogic[1]){
                            if (Checks[15] < 1) {Checks[15] = 1;}
                            Checks[i]=2;
                            if (DMist == 26 && Checks[27]!=2){
                                Checks[27]=1;
                            }
                            Progress=true;
                        }
                        break;
                    case 9: //Twinharp
                        if (Checks[14] != 2) {Checks[14] = 1;}
                        Checks[i]=2;
                        if (DMist == 23 && Checks[27]!=2){
                            Checks[27]=1;
                        }
                        Progress=true;
                        break;
                    case 10: // earth
                        if (Checks[13] != 2) {Checks[13] = 1;}
                        Checks[i]=2;
                        if (DMist == 24 && Checks[27]!=2){
                            Checks[27]=1;
                        }
                        Progress=true;
                        break;
                    case 11:// Package
                        Checks[i]=2;
                        if (DMist == 22 && Checks[27]!=2){
                            Checks[27]=1;
                        }
                        Progress=true;
                        break;
                    case 12:// Adamant
                        Checks[i]=2;
                        Adam=true;
                        break;
                    case 13:// Legend
                        Checks[i]=2;
                        Leg=true;
                        break;
                }

            }
        }
    }
        if(Leg && Adam && Dark){
            return false;
        }
        return true;
    }
}
