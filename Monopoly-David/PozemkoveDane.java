package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class PozemkoveDane extends Pozicia{
    private ArrayList<Hrac> zoznamHracovVPozemkovychDaniach=new ArrayList<>();
    private int vyskaDane;

    public PozemkoveDane(int index,String nazov, int vyskaDane) {
        super(index,nazov);
        this.vyskaDane = vyskaDane;
    }


    public int getVyskaDane() {
        return vyskaDane;
    }
}
