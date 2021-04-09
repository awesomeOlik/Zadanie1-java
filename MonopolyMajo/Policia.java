package sk.stuba.fei.uim.oop;

public class Policia extends HraciaDoska{
    private final int kolaVoVazeni;
    public Policia(String meno, int indexpolicka) {
        super(meno, indexpolicka);
        kolaVoVazeni = 2;
    }

    public void chodDoVazenia(Hrac hrac){
        hrac.setIndexPola(6);
        hrac.setKolaVoVazeni(kolaVoVazeni);
    }
}
