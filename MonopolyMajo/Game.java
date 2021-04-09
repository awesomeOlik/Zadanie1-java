package sk.stuba.fei.uim.oop;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    public void zacatHru() {
        int poradie=0;
        int cisloNaKocke;
        int kupit;
        int nahodnaKarta=ThreadLocalRandom.current().nextInt(1, 6);
        Scanner scan=new Scanner(System.in);
        var kocka=new Kocka();
        int pocetHracov=0;

        HraciaDoska[] hraciePole= new HraciaDoska[]{
                new HraciaDoska("Start",0),
                new Nehnutelnost("Azerbajdzan",1,1200,600),
                new Nehnutelnost("Angola",2,1500,750),
                new Sanca("Sanca1",3),
                new Nehnutelnost("Slovensko",4,2000,1000),
                new Nehnutelnost("Cesko",5,2225,1120),
                new HraciaDoska("Vazenie Leopoldov",6),
                new Nehnutelnost("Island",7,2500,1225),
                new Nehnutelnost("Ukrajina",8,2700,1300),
                new Sanca("Sanca1",9),
                new Nehnutelnost("Nemecko",10,3000,1500),
                new Nehnutelnost("Mexiko",11,3200,1600),
                new Policia("Policia",12),
                new Nehnutelnost("Svajciarsko",13,3600,1800),
                new Nehnutelnost("Svedsko",14,3800,600),
                new Sanca("Sanca1",15),
                new Nehnutelnost("Anglicko",16,3850,600),
                new Nehnutelnost("Taliansko",17,3990,625),
                new Dane("Platba Dane",18),
                new Nehnutelnost("Japonsko",19,4125,649),
                new Nehnutelnost("Rusko",20,4300,700),
                new Sanca("Sanca1",21),
                new Nehnutelnost("USA",22,4700,750),
                new Nehnutelnost("Mars",23,5000,820),
        };

        System.out.println("Zadaj počet hráčov,počet musí byť väčší ako jedna:");
        while(pocetHracov<1) {
            pocetHracov = vyhodnotenieVstupov();
        }
        Hrac[] hraci= new Hrac[pocetHracov];
        ArrayList<String> menaHracov=new ArrayList<>();

        for(int i=0;i<pocetHracov;i++){
            System.out.println("Zadaj meno "+(i+1)+". hráča: ");
            String meno=scan.next();
            if(!menaHracov.contains(meno)){
                menaHracov.add(meno);
                hraci[i] = new Hrac(meno);
            }
            else{
                System.out.println("Hráč s týmto menom už existuje.");
                i--;
            }
        }

        while(true) {
           if(pocetHracov==1){
               System.out.println("VÍŤAZ: "+hraci[0].getMeno());
               break;
           }
            Hrac hrac = hraci[poradie];
            cisloNaKocke =kocka.hodKockou();

            if(hrac.getKolaVoVazeni()!=0){
                hrac.cakatVoVazeni();
                System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_--_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_--_-_-_-_-_-_-_-_-_-_-_-_-");
                poradie++;
                if (poradie>= pocetHracov){
                    poradie=0;
                }
                continue;
            }
            hrac.vypisHraca(hraciePole);
            zadajEnter();
            System.out.println("Kocka: " + cisloNaKocke);
            hrac.pohybHraca(cisloNaKocke);
            System.out.println("Po hode kockou si na políčku s číslom: "+hrac.getIndexPola()+".Na danom políčku sa nachádza "+ hraciePole[hrac.getIndexPola()].getMeno());

            if(hraciePole[hrac.getIndexPola()] instanceof Sanca){
                Sanca karta=(Sanca)hraciePole[hrac.getIndexPola()];
                System.out.println("Potiahni si kartu.");
                karta.tahanieKariet(hrac,nahodnaKarta);
                nahodnaKarta++;
                if(nahodnaKarta>=6){
                    nahodnaKarta=1;
                }
            }
            else if(hraciePole[hrac.getIndexPola()] instanceof Policia){
                Policia policia=(Policia)hraciePole[hrac.getIndexPola()];
                System.out.println("Chytila ťa polícia,presuň sa do väzenia.");
                policia.chodDoVazenia(hrac);
            }
            else if(hraciePole[hrac.getIndexPola()] instanceof Dane){
                Dane dane=(Dane)hraciePole[hrac.getIndexPola()];
                System.out.println("Zaplať 500 za kazdú nehnutelnosť ktorú vlastníš.");
                dane.zaplatitDan(hrac);
            }
            else if(hraciePole[hrac.getIndexPola()] instanceof Nehnutelnost){
                Nehnutelnost stat=(Nehnutelnost)hraciePole[hrac.getIndexPola()];
                if(stat.getVlastnik()==null) {
                    System.out.println("Daná nehnutelnosť stojí " + stat.getCena());
                    System.out.println("Chceš daný štát kúpiť?  ano(1)   nie(0)");
                    do {
                        kupit = hrac.kupitNehnutelnost(stat);
                    } while ((kupit != 1) && (kupit != 0));
                }
                else{
                    if(hrac.getMeno().equals(stat.getVlastnik())) {
                        System.out.println("Daný štát je v tvojom vlasnictve;");
                    }
                    else {
                        System.out.println("Daný štát vlastní " + stat.getVlastnik() + " Zaplať mu " + stat.getCenaZaPrenajom());
                        hrac.platba(najstHracaPodlaMena(stat.getVlastnik(),hraci), stat.getCenaZaPrenajom());
                        System.out.println("Zostatok na účte hráča "+hrac.getMeno()+" je:"+hrac.getBitcoin());
                        System.out.println("Zostatok na účte hráča "+stat.getVlastnik()+" je:"+najstHracaPodlaMena(stat.getVlastnik(),hraci).getBitcoin());
                    }
                }
            }
            if(hrac.getBitcoin()<0){
                hrac.vymazanieVlastnictva();
                hraci=vymazatHraca(hraci,poradie);
                System.out.println("ZBANKROTOVAL SI, VYPADÁVAŠ Z HRY HRÁČ "+hrac.getMeno());
                poradie--;
                pocetHracov-=1;
            }
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_--_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_--_-_-_-_-_-_-_-_-_-_-_-_-");
            poradie++;
            if (poradie>= pocetHracov){
                poradie=0;
            }
        }
        scan.close();
    }

    public Hrac najstHracaPodlaMena(String meno,Hrac[] pole){
        for (Hrac hrac : pole) {
            if (hrac.getMeno().equals(meno)) {
                return hrac;
            }
        }
        return null;
    }
    public Hrac[] vymazatHraca(Hrac[] array,int index){
        Hrac[] copyArray = new Hrac[array.length - 1];
        System.arraycopy(array, 0, copyArray, 0, index);
        System.arraycopy(array, index + 1, copyArray, index, array.length - index - 1);
        return copyArray;
    }
    private int vyhodnotenieVstupov(){
        int cislo;
        Scanner scan=new Scanner(System.in);
        try{
            cislo=scan.nextInt();
            if (cislo > 1) {
                return cislo;
            }
            System.out.println("Zadaj číslo väčšie ako 1");
        }catch (InputMismatchException error) {
            System.out.println("Zadaj číslo väčšie ako 1");
        }
        return -1;
    }
    private void zadajEnter(){
        String s;
        var br= new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Stlač enter na hod kockou.");
            s = null;
            try {
                s = br.readLine();
            } catch (IOException ignored) {
            }
        } while (s == null || s.length() != 0);
    }
}
