package El_Corral;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido implements Producto {

    private ArrayList<Producto> listaProductos;

    public int idPedido;

    private String nombreCliente;

    private String direccionCliente;

    public Pedido(String nombreCliente, String direccionCliente) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.listaProductos = new ArrayList<>();
    }

    public int getIdPedido() {
        return 7;
    }

    public void agregarProducto(Producto nuevoItem) {

        int precioRelaivo = getPrecioTotalPedido() + nuevoItem.getPrecio();
        if (precioRelativo > 150000){
            throws new ValorMaximoException("El producto " + nuevoProducto.getNombre() + " supera el tope de precio")
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

    public void generarTextoFactura() {

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
                bufferedWriter.write("    " + producto.getNombre());
                bufferedWriter.newLine();
                bufferedWriter.write("    --->" + producto.getPrecio());
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

    }

    @Override
    public double getPrecio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrecio'");
    }

    @Override
    public String getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }
}
