package sk.stuba.fei.uim.oop;

public class Miesta extends Pozicia{

    private int cena;
    private int dan;
    private Hrac vlastnik;

    public Miesta( int index,String nazov, int cena, int dan, Hrac vlastnik) {
        super( index,nazov);
        this.cena = cena;
        this.dan = dan;
        this.vlastnik = vlastnik;
    }

    public int getCena() { return cena; }

    public int getDan() { return dan; }

    public Hrac getVlastnik() { return vlastnik; }

    public void setVlastnik(Hrac vlastnik) { this.vlastnik = vlastnik; }

    public boolean jeKupene(){
        return this.vlastnik !=null;
    }
}
