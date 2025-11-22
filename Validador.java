public class Validador {
    
    public static boolean soloLetras(String t) {
        // 4.3 REFACTOR: Se mejoró usando "Regex" para validar el patrón en una sola línea sin bucles.
        return t != null && t.matches("^[a-zA-Z]+$");
    }
    
}
