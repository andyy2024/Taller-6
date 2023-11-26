package Consola;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import El_Corral.Combo;
import El_Corral.Ingrediente;
import El_Corral.Pedido;
import El_Corral.ProductoAjustado;
import El_Corral.ProductoMenu;
import El_Corral.Restaurante;

public class Aplicacion {

    static Restaurante restaurante;

    static {
        try {
            restaurante = new Restaurante();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void mostrarMenu() {

        String nombreCliente = input("\nIngrese su Nombre completo");
        String direccionCliente = input("Ingrese su Direccion");

        restaurante.iniciarPedido(nombreCliente, direccionCliente);

        boolean seguir = true;
        int respuesta = 0;
        while (seguir) {
            System.out.println("\n 1- Mira todas nuestras hamburguesas, papas y bebidas");
            System.out.println(" 2- Escoje entre todos nuestros combos");
            System.out.println(" 3- Selecciona un ingrediente que quieras adicionar o retirar");
            System.out.println(" 4- Finaliza tu pedido");

            respuesta = Integer.parseInt(input("Selecciona tu opcion"));

            if (respuesta == 1) {
                productos();
                continue;
            }

            if (respuesta == 2) {
                combos();
                continue;
            }

            if (respuesta == 3) {
                ingredientes();
                continue;
            }

            if (respuesta == 4) {
                restaurante.cerrarYGuardarPedido();
                System.out
                        .println("Listo! Tu pedido quedo guardado, consulta la factura en nuestro menu principal >-<");
                System.out.println("RECUERDA, tu numero de factura es " + restaurante.getPedidoEnCurso().idPedido);
                break;
            }
        }

    }

    static void productos() {

        System.out.println("\nTodos los productos de nuestro Menu");
        ArrayList<ProductoMenu> menu = restaurante.getMenuBase();
        for (int i = 0; i < menu.size(); i++) {
            ProductoMenu producto = menu.get(i);

            System.out.println(Integer.sum(i, 1) + " --------");
            System.out.println("    " + producto.getNombre());
            System.out.println("    " + producto.getPrecio() + "\n");
        }
        System.out.println("Ingresa 0 para volver");

        boolean seguir = true;
        while (seguir) {
            int respuesta = Integer.parseInt(input("\nSelecciona un producto"));
            if (respuesta == 0) {
                break;
            }
            respuesta -= 1;
            if (!(0 <= respuesta) || !(respuesta < menu.size())) {
                System.out.println("Ese numero no esta en el menu >:(");
                System.out.println("Escoje una opcion valida ^-^");
            } else {
                restaurante.agregarProducto(menu.get(respuesta));
                System.out.println("Bien! se agregó " + menu.get(respuesta).getNombre() + " a tu pedido!");
            }

        }
    }

    static void combos() {

        System.out.println("\nTodos nuestros combos");
        ArrayList<Combo> menu = restaurante.getCombos();
        for (int i = 0; i < menu.size(); i++) {
            Combo producto = menu.get(i);

            System.out.println(Integer.sum(i, 1) + " --------");
            System.out.println("    " + producto.getNombre());
            System.out.println("      " + producto.getHamburguesa());
            System.out.println("      " + producto.getBebida());
            System.out.println("      " + producto.getPapas());
            System.out.println("    " + producto.getPrecio() + "\n");
        }
        System.out.println("Ingresa 0 para volver");

        boolean seguir = true;
        while (seguir) {
            int respuesta = Integer.parseInt(input("\nSelecciona un Combo"));
            if (respuesta == 0) {
                break;
            }
            respuesta -= 1;
            if (!(0 <= respuesta) || !(respuesta < menu.size())) {
                System.out.println("Ese numero no esta en el menu >:(");
                System.out.println("Escoje una opcion valida ^-^");
            } else {
                restaurante.agregarProducto(menu.get(respuesta));
                System.out.println("Bien! se agregó " + menu.get(respuesta).getNombre() + " a tu pedido!");
            }

        }
    }

    static void ingredientes() {

        System.out.println("\nTodos nuestros ingredientes");
        ArrayList<Ingrediente> menu = restaurante.getIngredientes();
        for (int i = 0; i < menu.size(); i++) {
            Ingrediente producto = menu.get(i);

            System.out.println(Integer.sum(i, 1) + " --------");
            System.out.println("    " + producto.getNombre());
            System.out.println("    " + producto.getPrecio() + "\n");
        }
        System.out.println("Ingresa 0 para volver");

        boolean seguir = true;
        while (seguir) {
            int respuesta = Integer.parseInt(input("\nSelecciona un ingrediente"));
            if (respuesta == 0) {
                break;
            }
            respuesta -= 1;
            if (!(0 <= respuesta) || !(respuesta < menu.size())) {
                System.out.println("Ese numero no esta en el menu >:(");
                System.out.println("Escoje una opcion valida ^-^");
            } else {
                int respuesta2 = Integer
                        .parseInt(input("\nIngresa 1 para agregarlo como adicion, o 0 para quitarlo de tu pedido"));
                if (respuesta2 == 1) {
                    boolean adicion = true;
                    restaurante.agregarProducto(new ProductoAjustado(menu.get(respuesta), adicion));
                    System.out
                            .println("Bien! se agregó adicion de " + menu.get(respuesta).getNombre() + " a tu pedido!");
                } else if (respuesta2 == 0) {
                    boolean adicion = false;
                    restaurante.agregarProducto(new ProductoAjustado(menu.get(respuesta), adicion));
                    System.out.println("Bien! se agregó un sin " + menu.get(respuesta).getNombre() + " a tu pedido!");
                } else {
                    System.out.println("\nSelecciona una opcion valida");
                }
            }

        }

    }

    static void mostrarOpciones() {
        System.out.println("\n1 - Mira nuestro Menu e Inicia un nuevo pedido!");
        System.out.println("2 - Ya hiciste tu pedido? Consultalo con el numero de factura\n");
    }

    static void mostrarFactura() {
        int numero = Integer.parseInt(input("Por favor ingresa el numero de tu factura"));
        String rutaArchivo = ".//src//Facturas//" + numero + ".txt";

        try {
            Desktop desktop = Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.OPEN)) {
                File archivo = new File(rutaArchivo);

                desktop.open(archivo);
            } else {
                System.out.println("Lo sentimos, parace que en tu computador no se pueden abrir archivos :(");
                System.out.println(
                        "Puedes consultar la factura en la carpeta 'Facturas' que se encuentra dentro del directorio");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String input(String mensaje) {
        try {
            System.out.print(mensaje + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error leyendo de la consola");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        boolean seguir = true;
        int opcion_seleccionada = 0;
        while (seguir) {
            mostrarOpciones();
            try {
                opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
            } catch (Exception e) {
                System.out.println("Debes seleccionar una opcion del menu >_<");
                continue;
            }

            if (opcion_seleccionada == 0) {
                break;
            }

            else if (opcion_seleccionada == 1) {
                mostrarMenu();
            }

            else if (opcion_seleccionada == 2) {
                mostrarFactura();
            }
        }

    }

}