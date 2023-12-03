package Tests;

import El_Corral.Pedido;
import El_Corral.Producto;
import El_Corral.ProductoMenu;
import Exceptions.ValorMaximoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PedidoTest {

	Pedido pedido;
	ProductoMenu productoMenu;

	@BeforeEach
	void crearPedido() {
		pedido = new Pedido("Hanni", "NewJeans");
	}

	@Test
	void testAgregarProducto() throws ValorMaximoException {
		productoMenu = new ProductoMenu("corral queso", 16000);
		pedido.agregarProducto(productoMenu);
		ArrayList<Producto> listaProductos = new ArrayList<>();
		listaProductos.add(productoMenu);
		assertEquals(pedido.listaProductos, listaProductos);
	}

	@Test
	// TEST DE LA EXCEPTION 'PRECIO EXCEDIDO'
	void testAgregarProductoException() {

		assertThrows(ValorMaximoException.class, () -> {
			productoMenu = new ProductoMenu("corral queso", 150001);
			pedido.agregarProducto(productoMenu);
		});
	}

	@Test
	void testPrecioIVA() throws ValorMaximoException {
		productoMenu = new ProductoMenu("corral queso", 16000);
		pedido.agregarProducto(productoMenu);
		assertEquals(pedido.getPrecioIVAPedido(pedido.getPrecioNetoPedido()), 3040, 0);
	}

	@Test
	void testPrecioNeto() throws ValorMaximoException {
		productoMenu = new ProductoMenu("corral queso", 16000);
		pedido.agregarProducto(productoMenu);
		assertEquals(pedido.getPrecioNetoPedido(), 16000);
	}

	@Test
	void testPrecioTotal() throws ValorMaximoException {
		productoMenu = new ProductoMenu("corral queso", 16000);
		pedido.agregarProducto(productoMenu);
		assertEquals(pedido.getPrecioTotalPedido(pedido.getPrecioNetoPedido(),
				pedido.getPrecioIVAPedido(pedido.getPrecioNetoPedido())), 19040.0, 0);
	}

	@Test
	void testFactura() throws ValorMaximoException, IOException {
		productoMenu = new ProductoMenu("corral queso", 16000);
		pedido.agregarProducto(productoMenu);
		File facturaTest = new File(".//data//Facturas///TestFactura.txt");
		List<String> factura1 = Files.readAllLines(Paths.get(facturaTest.getPath()));
		List<String> factura2 = Files.readAllLines(Paths.get(pedido.generarTextoFactura().getPath()));

		assertEquals(factura1, factura2);

	}

}
