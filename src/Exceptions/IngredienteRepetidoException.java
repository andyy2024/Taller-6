package Exceptions;

public class IngredienteRepetidoException extends HamburguesaException {

    public IngredienteRepetidoException(String ingrediente) {

        super("ERROR: Hay un ingrediente repetido en el archivo ingredientes.txt\n"
        		+ "El nombre del ingrediente es " + ingrediente + "\n" +
        		"Ocurrio en la clase 'Restaurante' en el metodo 'cargarIngredientes'");

    }
}