package El_Corral;

public class ProductoMenu implements Producto {

    private String nombre;
    private int precioBase;

    ProductoMenu(String nombre, int precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precioBase;
    }

    @Override
    public void generarTextoFactura() {
    }
}
