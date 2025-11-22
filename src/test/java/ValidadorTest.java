import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorTest {

    @Test
    public void testSoloLetras() {
        // 4.1 FASE RED: Test obligatorio que valida que "Hola" son solo letras
        assertTrue(Validador.soloLetras("Hola"));
        
        // Agregamos un caso extra de seguridad (opcional pero recomendado)
        assertFalse(Validador.soloLetras("Hola123")); 
    }
}