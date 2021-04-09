package sk.stuba.fei.uim.oop;

public class HraciaDoska {
    private final String meno;
    protected final int indexPolicka;

    public HraciaDoska(String meno, int indexPolicka) {
        this.meno = meno;
        this.indexPolicka = indexPolicka;
    }
    public String getMeno() {
        return meno;
    }
    @Override
    public String toString() {
        return "Názov:" + meno +
                "\nPolíčko:" + indexPolicka;
    }
}
