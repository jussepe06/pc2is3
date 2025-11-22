public class ServicioX {
    
    // Retorna true si aplica descuento y porcentaje es válido (0-50)
    public static boolean validarDescuentoAplicable(Producto p, double porcentaje) {
        if (p.isDescuentoAplicable() && porcentaje >= 0 && porcentaje <= 50) {
            return true;
        }
        return false;
    }
}



