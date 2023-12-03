package El_Corral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.ValorMaximoException;

public class Pedido  {

    public ArrayList<Producto> listaProductos;

    public int idPedido;

    private String nombreCliente;

    private String direccionCliente;

    public Pedido(String nombreCliente, String direccionCliente) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto nuevoItem) throws ValorMaximoException {

        double precioRelativo = getPrecioNetoPedido() + getPrecioIVAPedido(idPedido) + nuevoItem.getPrecio();
        if (precioRelativo > 150000) {
            throw new ValorMaximoException(nuevoItem.getNombre(), nuevoItem.getPrecio());
        }

        listaProductos.add(nuevoItem);
    }

    public int getPrecioNetoPedido() {
        int total = 0;
        for (Producto producto : listaProductos) {
            total += producto.getPrecio();
        }
        return total;
    }

    public double getPrecioIVAPedido(int precioNeto) {
        return precioNeto * 0.19;
    }

    public double getPrecioTotalPedido(int precioNeto, double precioIVA) {
        return precioIVA + precioNeto;
    }

    public File generarTextoFactura() {

        String filePath = ".//src//Facturas//" + idPedido + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("-------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write("FACTURA No. " + idPedido);
            bufferedWriter.newLine();
            bufferedWriter.write("PRODUCTOS");
            bufferedWriter.newLine();
            bufferedWriter.write("Nombre: " + nombreCliente);
            bufferedWriter.newLine();
            bufferedWriter.write("Direccion: " + direccionCliente);
            for (Producto producto : listaProductos) {
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                bufferedWriter.write(producto.generarTextoFactura());
            }
            int costoNeto = getPrecioNetoPedido();
            double IVA = getPrecioIVAPedido(costoNeto);

            bufferedWriter.newLine();
            bufferedWriter.write("Costo Neto " + costoNeto);
            bufferedWriter.newLine();
            bufferedWriter.write("IVA " + IVA);
            bufferedWriter.newLine();
            bufferedWriter.write("Costo Total " + getPrecioTotalPedido(costoNeto, IVA));
            bufferedWriter.newLine();
            bufferedWriter.write("-------------------------");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    return new File(filePath);
    }
}
