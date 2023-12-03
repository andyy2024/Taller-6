package El_Corral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.IngredienteRepetidoException;
import Exceptions.ProductoRepetidoException;
import Exceptions.ValorMaximoException;

import java.util.HashMap;

public class Restaurante {

    Pedido pedidoEnCurso;

    ArrayList<ProductoMenu> menu;

    ArrayList<Ingrediente> ingredientes;

    ArrayList<Combo> combos;

    public ArrayList<Pedido> pedidos;

    HashMap<String, Double> mapaDePrecios;

    public Restaurante() throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
        this.menu = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.combos = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.mapaDePrecios = new HashMap<>();

        cargarInformacionRestaurante();
        mapaDePrecios();
        cargarCombos(".//data//combos.txt");
    }

    private void mapaDePrecios() {
        for (int i = 0; i < menu.size(); i++) {
            ProductoMenu producto = menu.get(i);
            mapaDePrecios.put(producto.getNombre(), producto.getPrecio());
        }
    }

    public void iniciarPedido(String nombreCliente, String direccionCliente) {
        pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
    }

    public void cerrarYGuardarPedido() {
        pedidos.add(pedidoEnCurso);
        pedidoEnCurso.idPedido = pedidos.size();
        pedidoEnCurso.generarTextoFactura();

    }

    public Pedido getPedidoEnCurso() {
        return pedidoEnCurso;

    }

    public void agregarProducto(Producto producto) throws ValorMaximoException {
        pedidoEnCurso.agregarProducto(producto);
    }

    public void guardarFactura(File archivo) {
    	//useless function
    }

    public ArrayList<ProductoMenu> getMenuBase() {
        return menu;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }

    public void cargarInformacionRestaurante()
            throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
        cargarIngredientes(".//data//ingredientes.txt");
        cargarMenu(".//data//menu.txt");
    }

    public void cargarIngredientes(String nombreArchivo) throws IOException, IngredienteRepetidoException {
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        String linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(";");
            Ingrediente nuevoIngrediente = new Ingrediente(partes[0], Integer.parseInt(partes[1]));

            // Exception
            if (ingredientes.contains(nuevoIngrediente)) {
                throw new IngredienteRepetidoException(nuevoIngrediente.getNombre());
            }

            ingredientes.add(nuevoIngrediente);
            linea = br.readLine();
        }
        br.close();
    }

    public void cargarMenu(String nombreArchivo) throws IOException, ProductoRepetidoException {
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        String linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(";");
            ProductoMenu nuevoProducto = new ProductoMenu(partes[0], Integer.parseInt(partes[1]));
            
            // Exception
            if (menu.contains(nuevoProducto)) {
                throw new ProductoRepetidoException(nuevoProducto.getNombre());
            }

            menu.add(nuevoProducto);
            linea = br.readLine();
        }
        br.close();
    }

    public void cargarCombos(String nombreArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        String linea = br.readLine();
        while (linea != null) {
            String[] partes = linea.split(";");
            Combo nuevoProducto = new Combo(partes[0], Integer.parseInt(partes[1].replace("%", "")), partes[2],
                    partes[3], partes[4], mapaDePrecios);
            combos.add(nuevoProducto);
            linea = br.readLine();
        }
        br.close();
    }
}
