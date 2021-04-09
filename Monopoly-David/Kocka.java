package sk.stuba.fei.uim.oop;

public class Kocka {
    private int Hodnota=0;
    public Kocka() {
    }

    public int getHodnota() { return Hodnota; }

    //nahodna hodnota od 1 po 6
    public void HodnotaKocky()
    {
        Hodnota +=(int)((Math.random()*6)+1);
    }
}
