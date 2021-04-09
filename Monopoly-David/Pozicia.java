package sk.stuba.fei.uim.oop;

public class Pozicia {
    private int index=0;
    private String nazov;

    public Pozicia( int index,String nazov) {
        this.index = index;
        this.nazov = nazov;
    }
    //volat nazov
    public String getNazov() {
        return nazov;
    }
}
