package sk.stuba.fei.uim.oop;

public class Nehnutelnost extends HraciaDoska{
    private final int cena;
    private String vlastnik;
    private final int cenaZaPrenajom;

    public Nehnutelnost(String meno, int indexpolicka, int cena,int cenaZaPrenajom) {
        super(meno, indexpolicka);
        this.cena = cena;
        this.cenaZaPrenajom=cenaZaPrenajom;
    }
    public void setVlastnik(String vlastnik) {
        this.vlastnik = vlastnik;
    }

    public int getCenaZaPrenajom() {
        return cenaZaPrenajom;
    }

    public int getCena() {
        return cena;
    }

    public String getVlastnik() {
        return vlastnik;
    }
}
