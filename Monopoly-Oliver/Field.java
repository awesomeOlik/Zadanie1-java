package sk.stuba.fei.uim.oop;

public class Field {
    private final String name;
    private final int position;

    public Field(String name, int position) {
        super();
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
