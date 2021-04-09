package sk.stuba.fei.uim.oop;

public class Tax extends Field{
    private int tax;

    public Tax(String name, int tax, int position) {
        super(name, position);
        this.tax = tax;
    }

    public int getTax(Player p) {
        this.tax = p.getHouses().size() * 500;
        return tax;
    }
}
