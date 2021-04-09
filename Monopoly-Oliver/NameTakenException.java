package sk.stuba.fei.uim.oop;

public class NameTakenException extends Exception {

    public NameTakenException(String name_taken) {
        super(name_taken);
    }
}
