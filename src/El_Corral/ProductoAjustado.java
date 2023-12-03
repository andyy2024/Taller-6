package El_Corral;

public class ProductoAjustado implements Producto {

    Ingrediente producto;
    int precio;
    String nombre;

    public ProductoAjustado(Ingrediente ingrediente, boolean adicion) {
        if (adicion) {
            this.precio = ingrediente.getPrecio();
            this.nombre = "Adicion de " + ingrediente.getNombre();
        } else {
            this.precio = 0;
            this.nombre = "Sin " + ingrediente.getNombre();
        }
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String generarTextoFactura() {
    	return "    " + nombre + "\n" + "    --->" + precio;
    }

}
