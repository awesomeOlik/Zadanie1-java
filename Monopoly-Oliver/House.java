package sk.stuba.fei.uim.oop;

public class House extends Field{
    private final int price;
    private boolean isTaken;
    private final int value;
    private String owner;

    public House(String name, int price, int value, boolean isTaken, int position) {
        super(name, position);
        this.price = price;
        this.value = value;
        this.isTaken = isTaken;
    }

    public int getPrice() {
        return price;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean isTaken) {
        this.isTaken = isTaken;
    }

    public int getValue() {
        return value;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
