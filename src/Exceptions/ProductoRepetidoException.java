package Exceptions;

public class ProductoRepetidoException extends HamburguesaException {

    public ProductoRepetidoException(String productoMenu) {
        
    	super("ERROR: Hay un ingrediente repetido en el archivo menu.txt\n"
        		+ "El nombre del ingrediente es " + productoMenu + "\n" +
        		"Ocurrio en el metodo 'cargarMenu' en la clase Restaurante");
    }
}