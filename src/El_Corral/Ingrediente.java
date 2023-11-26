package El_Corral;

public class Ingrediente {
    private String nombre;
    private int costoAdicional;

    Ingrediente(String nombre, int costoAdicional) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return costoAdicional;
    }
}
