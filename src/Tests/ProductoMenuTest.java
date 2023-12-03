package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


import El_Corral.ProductoMenu;

public class ProductoMenuTest {

	ProductoMenu productoMenu;
	
	@BeforeEach
	void crearProducto() {
		productoMenu = new ProductoMenu("corral queso",16000);
	}
	
    @Test
    void testFactura() {
    	String textoFactura = "    corral queso\n    --->16000";
    	assertEquals(textoFactura, productoMenu.generarTextoFactura());
    }

}
