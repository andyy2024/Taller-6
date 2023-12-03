package El_Corral;

public class ProductoMenu implements Producto {

    private String nombre;
    private int precioBase;

    public ProductoMenu(String nombre, int precioBase) {
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
    public String generarTextoFactura() {
    	return "    " + nombre + "\n" + "    --->" + precioBase;
    }
    
    @Override
    public boolean equals(Object obj) {
    	
        // Check if the object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        ProductoMenu newObj = (ProductoMenu) obj;
                
        if (newObj.nombre.equals(this.nombre)) {
        	return true;
        }
        
        return false;

    }
}
