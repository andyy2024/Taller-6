package Exceptions;

import Consola.Aplicacion;

public abstract class HamburguesaException extends Exception {

    public HamburguesaException(String mensaje) {
        super("\n\n"+Aplicacion.Colorear(mensaje, Aplicacion.BRIGHT_MAGENTA)+"\n");
    }

}