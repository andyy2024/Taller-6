package Exceptions;

public class ValorMaximoException extends HamburguesaException {

    public ValorMaximoException(String producto, Double precio) {
        
    	super("ERROR: Agregar el producto '" + producto + "' con valor de " + precio + " excede el tope maximo de 150,000 COP\n"
        		+ "Ocurrio en la clase 'Pedido' en el metodo 'agregarProducto'");
    }
}