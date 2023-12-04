package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import El_Corral.Combo;

import java.util.HashMap;

public class ComboTest {

	Combo combo;
	HashMap<String, Double> mapaDePrecios; 
	
	@BeforeEach
	void crearCombo() {
		mapaDePrecios = new HashMap<>();
		mapaDePrecios.put("especial",24000.0);
		mapaDePrecios.put("papas medianas",5500.0);
		mapaDePrecios.put("gaseosa",5000.0);
		combo = new Combo("combo especial",7.0,"especial","gaseosa","papas medianas",mapaDePrecios);
	}
	
	@Test
    void testFactura() {
    	String textoFactura = "    combo especial\n    --->32085.0";
    	assertEquals(textoFactura, combo.generarTextoFactura());
    }
	
	@Test
	void testPrecio() {
		double precioReal = 32085.0;
		assertEquals(precioReal, combo.calcularPrecio(mapaDePrecios),0);
	}
	
	@Test
	void testBebida(){
		assertEquals(combo.getBebida(),"gaseosa");
	}
	
	@Test
	void testPapas(){
		assertEquals(combo.getPapas(),"papas medianas");
	}
	
	@Test
	void testHamburguesa(){
		assertEquals(combo.getHamburguesa(),"especial");
	}
	
	//Los getters no se prueban segun el enunciado

}
