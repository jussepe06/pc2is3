import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    //  Se instancia la clase
    Calculadora calc = new Calculadora();

    // 1.1 Validar que 10/2 = 5
    @Test
    public void testDividirExitoso() {
        int resultado = calc.dividir(10, 2);
        assertEquals(5, resultado, "La división debería ser 5");
    }

    // 1.2 Validar excepción al dividir por 0
    @Test
    public void testDividirPorCero() {
        // Verifica que lance IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(10, 0);
        });
    }

    // 1.3 Validar multiplicar (7, 3) devuelve 21
    @Test
    public void testMultiplicar() {
        int resultado = calc.multiplicar(7, 3);
        assertEquals(21, resultado);
    }
}