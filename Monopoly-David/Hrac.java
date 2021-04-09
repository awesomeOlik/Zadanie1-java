package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Hrac
{
    private String meno;
    private int bank=5000;
    private int pozicia=0;
    private boolean vazenie = false;
    private int kolaVoVazeni;
    private ArrayList<Miesta> vlastnictvo = new ArrayList<>();

    public int getKolaVoVazeni() {
        return kolaVoVazeni;
    }

    public void setKolaVoVazeni(int kolaVoVazeni) {
        this.kolaVoVazeni = kolaVoVazeni;
    }

    public Hrac(String meno){
        this.meno=meno;
    }

    public int getPozicia(){
        return pozicia;
    }

    public void setPozicia(int pozicia) {
        this.pozicia = pozicia;
    }

    public String getMeno() {
        return meno;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public boolean isVazenie() {
        return vazenie;
    }

    public void setVazenie(boolean voVazeni) {
        vazenie=voVazeni;
    }

    public ArrayList<Miesta> getVlastnictvo() {
        return vlastnictvo;
    }
}
