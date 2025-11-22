import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {
    

    private Pedido pedido;
    private Producto productoBase;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        // Producto válido base para pruebas
        productoBase = new Producto("Laptop", 1500.0, 10, "SKU-001", "Tecnologia", true, true);
    }


    // 1. Cantidad no válida: cantidad <= 0 -> false 
    @Test
    void testAgregarProductoCantidadInvalida() {
        boolean resultado = pedido.agregarProducto(productoBase, 0);
        assertFalse(resultado, "Debe retornar false si la cantidad es 0");
        assertTrue(pedido.getDetallesPedido().isEmpty());
    }

    // 2. Producto duplicado (por sku) -> false 
    @Test
    void testAgregarProductoDuplicado() {
        pedido.agregarProducto(productoBase, 1);
        // Intentamos agregar otro objeto pero con MISMO SKU
        Producto duplicado = new Producto("Laptop 2", 2000.0, 5, "SKU-001", "Tecnologia", true, true);
        
        boolean resultado = pedido.agregarProducto(duplicado, 2);
        assertFalse(resultado, "No debe permitir agregar producto con el mismo SKU");
        assertEquals(1, pedido.getDetallesPedido().size());
    }

    // 3. Agregado correcto -> true y presente
    @Test
    void testAgregarProductoExitoso() {
        boolean resultado = pedido.agregarProducto(productoBase, 5);
        assertTrue(resultado);
        assertEquals(1, pedido.getDetallesPedido().size());
        assertEquals(5, pedido.getDetallesPedido().get(0).getCantidad());
    }

    // 4. Respeto de atributos 
    @Test
    void testAgregarProductoPreservaAtributos() {
        pedido.agregarProducto(productoBase, 3);
        Producto guardado = pedido.getDetallesPedido().get(0);
        
        assertAll("Verificando atributos",
            () -> assertEquals("Laptop", guardado.getNombre()),
            () -> assertEquals("SKU-001", guardado.getSku()),
            () -> assertEquals("Tecnologia", guardado.getCategoria()),
            () -> assertTrue(guardado.isEsActivo()),
            () -> assertTrue(guardado.isDescuentoAplicable())
        );
    }

    // 5. Producto inactivo
    @Test
    void testAgregarProductoInactivo() {
        productoBase.setEsActivo(false);
        boolean resultado = pedido.agregarProducto(productoBase, 1);
        assertTrue(resultado, "Política actual: permite inactivos (se podría restringir)");
    }


    // 1. Lista vacía -> true 
    @Test
    void testValidarStockListaVacia() {
        assertTrue(pedido.validarStock(), "Debe retornar true si no hay items que validen mal");
    }

    // 2. Todos con stock válido -> true 
    @Test
    void testValidarStockTodoValido() {
        pedido.agregarProducto(new Producto("P1", 10, 0, "S1", "C", true, true), 10);
        pedido.agregarProducto(new Producto("P2", 20, 0, "S2", "C", true, true), 5);
        assertTrue(pedido.validarStock());
    }

    // 3. Uno con stock cero -> false
    @Test
    void testValidarStockConCero() {
        pedido.agregarProducto(new Producto("P1", 100, 0, "S1", "C", true, true), 5);
        pedido.getDetallesPedido().get(0).setCantidad(0);
        
        assertFalse(pedido.validarStock());
    }

    // 4. Cantidad negativa -> false 
    @Test
    void testValidarStockNegativo() {
         // Al igual que arriba, inyectamos el error
         pedido.agregarProducto(productoBase, 5);
         try {
             pedido.getDetallesPedido().get(0).setCantidad(-1);
         } catch (IllegalArgumentException e) {
             assertTrue(true); 
             return;
         }
         assertFalse(pedido.validarStock());
    }

    // 5. Valores límite (Grandes cantidades)
    @Test
    void testValidarStockValoresLimite() {
        pedido.agregarProducto(productoBase, Integer.MAX_VALUE);
        assertTrue(pedido.validarStock());
    }
}

    


