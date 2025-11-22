import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CarritoIntegrationTest {

    private Usuario usuario;
    private Carrito carrito;

    // Esto se ejecuta antes de CADA prueba para asegurar que empezamos desde cero
    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        carrito = new Carrito(usuario);
    }

    // 2.1 TOTAL EXACTO SIN DESCUENTO (2 pts)
    // 2.3 CANTIDAD DE ÍTEMS (1 pt)
    @Test
    public void testTotalSinDescuento() {
        // Agregar ítems en orden: 10.0, 20.0, 30.0 [cite: 68-71]
        carrito.agregar(new Item("Item1", 10.0));
        carrito.agregar(new Item("Item2", 20.0));
        carrito.agregar(new Item("Item3", 30.0));

        // Validar que el resultado sea 60.0 exactos [cite: 73]
        // El 0.001 es la tolerancia para comparar decimales (doubles)
        assertEquals(60.0, carrito.total(), 0.001, "El total sin descuento debe ser 60.0");

        // Validar que getCantidad() retorne 3 [cite: 79]
        assertEquals(3, carrito.getCantidad(), "La cantidad de items debe ser 3");
    }

    // 2.2 TOTAL CON DESCUENTO VIP (2 pts)
    @Test
    public void testTotalConDescuentoVip() {
        // Convertir al usuario en VIP (puntos >= 50) [cite: 75]
        usuario.agregarPuntos(50);
        
        // Verificamos que sea VIP antes de seguir
        assertTrue(usuario.esVip(), "El usuario debería ser VIP");

        // Agregar nuevamente los mismos ítems [cite: 76]
        carrito.agregar(new Item("Item1", 10.0));
        carrito.agregar(new Item("Item2", 20.0));
        carrito.agregar(new Item("Item3", 30.0));

        // Validar cálculo: 60.0 * 0.8 = 48.0 [cite: 77]
        assertEquals(48.0, carrito.total(), 0.001, "El total VIP debe tener 20% de descuento (48.0)");
    }
}