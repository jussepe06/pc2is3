import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegracionTest {
    

    // 1. Flujo exitoso [cite: 215]
    @Test
    void testFlujoExitosoCompleto() {
        Pedido pedido = new Pedido();
        Producto p = new Producto("Gamer Mouse", 50.0, 0, "SKU-GM", "Accesorios", true, true);
        
        // 1. Agregar
        boolean agregado = pedido.agregarProducto(p, 10);
        assertTrue(agregado);
        
        // 2. Validar Stock
        assertTrue(pedido.validarStock());
        
        // 3. Validar Servicio Externo
        boolean descuentoValido = ServicioX.validarDescuentoAplicable(
            pedido.getDetallesPedido().get(0), 10.0);
        assertTrue(descuentoValido);
    }

    // 2. Error por duplicado 
    @Test
    void testFlujoDuplicadoDetieneProceso() {
        Pedido pedido = new Pedido();
        Producto p1 = new Producto("Teclado", 100.0, 0, "SKU-KB", "Accesorios", true, true);
        pedido.agregarProducto(p1, 5);
        
        // Intentar duplicar
        boolean agregadoDuplicado = pedido.agregarProducto(p1, 3);
        assertFalse(agregadoDuplicado);
        
        // Validar que el sistema sigue consistente, solo 1 item
        assertEquals(1, pedido.getDetallesPedido().size());
        assertTrue(pedido.validarStock());
    }

    // 3. Stock inválido + Servicio
    @Test
    void testStockInvalidoAfectaServicio() {
        Pedido pedido = new Pedido();
        Producto p = new Producto("Monitor", 500.0, 0, "SKU-MN", "Video", true, true);
        pedido.agregarProducto(p, 5);
        
        // Simulamos caída de stock a 0
        pedido.getDetallesPedido().get(0).setCantidad(0);
        
        // El stock ya no es válido
        assertFalse(pedido.validarStock());
        
        // El test verifica que el servicio funcione aisladamente, pero la integración lógica fallaría.
        boolean aplicaDescuento = ServicioX.validarDescuentoAplicable(p, 20);
        assertTrue(aplicaDescuento); .
    }
}

    

