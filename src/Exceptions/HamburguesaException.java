package Exceptions;

public abstract class HamburguesaException extends Exception {

    public HamburguesaException(String mensaje) {
        super("\n\n"+mensaje+"\n");
    }

}