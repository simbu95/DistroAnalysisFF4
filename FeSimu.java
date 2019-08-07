package fesimu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeSimu {

    public static void main(String[] args) {
        Random r=new Random();
        int[] KI={7, 3, 11, 5, 1, 0, 0, 8, 4, 0, 12, 6, 0, 9, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 2};//new int[28];//1=magma,2=hook,3=rat tail, 4=darkness, 5=baron, 6=pan, 7=tower, 8=luca, 9=twinharp, 10=earth, 11=package, 12=adamant, 13=legend
        String[] KIs={"magma","hook","rat tail","darkness","baron","pan","tower","luca","twinharp","earth","package","adamant","legend"};
        String[] Locations={"Start","Antlion","Fabul","Ordeals","Baron Inn","Feymarch 1","Feymarch 2","Feymarch 3","Top of Tower",
            "Shiela 1","Dwarf","Baron 1","Baron 2","Zot","Twinharp","luca","Cannon","Pan 1","Pan 2","Bahamut","PaleDim","Wyvern","Plague","D Lunar 1","D Lunar 2","Ogopogo","Rat Tail","D Mist"};
        int DMist=0;//1-11=overworld,12-16=underground,17-19= Baron Key, 20-21=Hook, 22=Package, 23=Twinharp, 24=Earth, 25=Tower, 26=luca, 27-33=Darkness, 34=CPU
        try {
            FileWriter prin=new FileWriter("Data.txt",true);
            int count=0;
            while(count<1000000){
                KI=new int[28];
                for (int i=1;i<14;i++){
                    int j=r.nextInt(28);
                    while (KI[j]!=0){
                        j=r.nextInt(28);
                    }
                    KI[j]=i;
                }
                DMist=r.nextInt(34)+1;
                if(LogicCheck(KI,DMist)){
                    System.out.println("Logic Pass "+ count);
                    prin.write(Arrays.toString(KI) +"\n"+DMist+"\n");
                    count+=1;
                    prin.flush();
                }
                else{
                    //System.out.println("Failed");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FeSimu.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(LogicCheck(KI,DMist)){
            System.out.println("Logic Pass");
        
        System.out.println(Arrays.toString(KI));
        
        for(int i=0;i<28;i++){
            if(KI[i]!=0){
                System.out.println(KIs[KI[i]-1]+" at "+Locations[i]);
            }
        }
        String Mist;
        if(DMist<12){
            Mist="overworld";
        } else if(DMist<17){
            Mist="underground";
        }else if(DMist<20){
            Mist="baron";
        }else if(DMist<22){
            Mist="hook";
        }else if(DMist<23){
            Mist="package";
        }else if(DMist<24){
            Mist="Twinharp";
        }else if(DMist<25){
            Mist="Earth Crystal";
        }else if(DMist<26){
            Mist="Tower Key";
        }else if(DMist<34){
            Mist="Darkness";
        }else{
            Mist="CPU";
        }
        System.out.println("Dmist at "+ Mist);
        }
        else{
            System.out.println("Logic Failed");
        }
    }

    private static boolean LogicCheck(int[] KI, int DMist) {
        int[] Checks=new int[28];
        boolean[] KILogic=new boolean[13];
        if(DMist<=11){Checks[27]=1;}
        for(int i=0;i<5;i++){Checks[i]=1;}
        boolean Progress=true;
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
                        break;
                    case 13:// Legend
                        Checks[i]=2;
                        break;
                }

            }
        }
    }
        for(int i=0;i<28;i++){
            if(Checks[i]!=2){
                return false;
            }
        }
        return true;
    }
}
