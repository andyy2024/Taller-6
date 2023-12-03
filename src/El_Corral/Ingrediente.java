package El_Corral;

public class Ingrediente {
    private String nombre;
    private int costoAdicional;

    public Ingrediente(String nombre, int costoAdicional) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return costoAdicional;
    }
    
    @Override
    public boolean equals(Object obj) {
    	
        // Check if the object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Ingrediente newObj = (Ingrediente) obj;
                
        if (newObj.nombre.equals(this.nombre)) {
        	return true;
        }
        
        return false;

    }
}
