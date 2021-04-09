package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hrac{
    private final String meno;
    private int bitcoin;
    private int indexPola;
    private int kolaVoVazeni;
    private final ArrayList<Nehnutelnost> nehnutelnostiVoVlastnictve=new ArrayList<>();

    public Hrac(String meno) {
        this.meno = meno;
        this.indexPola=0;
        this.bitcoin = 15000;
    }

    public ArrayList<Nehnutelnost> getNehnutelnostiVoVlastnictve() {
        return nehnutelnostiVoVlastnictve;
    }

    public int getKolaVoVazeni() {
        return kolaVoVazeni;
    }

    public void setKolaVoVazeni(int kolaVoVazeni) {
        this.kolaVoVazeni = kolaVoVazeni;
    }

    public int getIndexPola() {
        return indexPola;
    }

    protected void setIndexPola(int indexPola) {
        this.indexPola = indexPola;
    }

    public String getMeno() {

        return meno;
    }
    public int getBitcoin() {

        return bitcoin;
    }
    public void setBitcoin(int bitcoin) {
        this.bitcoin = bitcoin;
    }

    public void pohybHraca(int oKolkoPolicok){
        indexPola+=oKolkoPolicok;
        if(indexPola>23){
            bitcoin+=2000;
            System.out.println("Za prechod Startom si získal 2000 bitcoinov.");
            indexPola-=24;
        }
    }
   public void vypisHraca(HraciaDoska[] hraciePole){
       System.out.println("Hrac: " + meno);
       System.out.println(hraciePole[indexPola].toString());
       System.out.println("Peniaze: "+bitcoin);
   }

    public void platba(Hrac vlastnik, int cena){
        bitcoin-=cena;
        if(bitcoin<0){
            vlastnik.setBitcoin(vlastnik.getBitcoin() + (bitcoin+cena));
        }
        else {
            vlastnik.setBitcoin(vlastnik.getBitcoin() + cena);
        }
    }
    private void vypisNehnutelnosti(){
        System.out.print("Vlastnis: [ ");
        for(Nehnutelnost budova:nehnutelnostiVoVlastnictve){
            System.out.print(budova.getMeno()+" ");
        }
        System.out.print("]\n");
    }
    private void vypisPriKupe(int kupit,Nehnutelnost stat){
        if (kupit == 1) {
            if (bitcoin < stat.getCena()) {
                System.out.println("Na daný štát nemáš peniaze.");
            } else {
                this.nehnutelnostiVoVlastnictve.add(stat);
                System.out.println("Gratulujeme kúpil si " + stat.getMeno() + ",daný štát sme pridali do tvojho vlastnictva.");
                bitcoin -= (stat.getCena());
                System.out.println("Aktualny zostatok na účte je:" + bitcoin);
                stat.setVlastnik(meno);
                vypisNehnutelnosti();
            }
        }
    }
    public int kupitNehnutelnost(Nehnutelnost stat){
        Scanner scan=new Scanner(System.in);
        int kupit=-1;
        try {
            kupit = scan.nextInt();
            vypisPriKupe(kupit,stat);
            if(kupit!=0 && kupit!=1){
                throw new JednaAleboNula("error");
            }
        } catch (JednaAleboNula | InputMismatchException error) {
            System.out.println("Zadaj cislo 1 alebo 0.");
        }
        return kupit;
    }

    public void cakatVoVazeni(){
        kolaVoVazeni--;
        System.out.println("Hráč "+meno+" si vo väzaní");
        System.out.println("Kola vo väzení: "+(kolaVoVazeni+1));
    }
    public void vymazanieVlastnictva(){
        for(Nehnutelnost majetokNaVymazaie:nehnutelnostiVoVlastnictve){
            majetokNaVymazaie.setVlastnik(null);

        }
    }
}
