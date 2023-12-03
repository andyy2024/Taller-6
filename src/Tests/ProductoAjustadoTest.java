package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import El_Corral.Ingrediente;
import El_Corral.ProductoAjustado;
import El_Corral.ProductoMenu;

public class ProductoAjustadoTest {
	
	ProductoAjustado adicionCebolla;
	ProductoAjustado quitarCebolla;
	Ingrediente ingrediente;
	
	@BeforeEach
	void crearProducto() {
		Ingrediente ingrediente = new Ingrediente("cebolla",1000);
		
		adicionCebolla = new ProductoAjustado(ingrediente, true);
		quitarCebolla = new ProductoAjustado(ingrediente, false);
	}

	@Test
	void testPrecio() {
    	assertEquals(quitarCebolla.getPrecio(), 0, 0);
    	assertEquals(adicionCebolla.getPrecio(), 1000, 0);
	}

	@Test
    void testFactura() {
    	String textoFactura = "    Adicion de cebolla\n    --->1000";
    	assertEquals(textoFactura, adicionCebolla.generarTextoFactura());
    	
    	textoFactura = "    Sin cebolla\n    --->0";
    	assertEquals(textoFactura, quitarCebolla.generarTextoFactura());
    }

}
