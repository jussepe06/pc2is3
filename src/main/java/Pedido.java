import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private List<Producto> detallesPedido = new ArrayList<>();

    public boolean agregarProducto(Producto producto, int cantidad) {
        // Validación 1: Cantidad positiva 
        if (cantidad <= 0) {
            System.err.println("Error: La cantidad a agregar debe ser positiva.");
            return false;
        }

        // Validación 2: Producto duplicado por SKU 
        // Usamos stream().anyMatch verificando el SKU para cumplir el requisito 
        boolean productoYaExiste = detallesPedido.stream()
                .anyMatch(p -> p.getSku() != null && p.getSku().equals(producto.getSku()));

        if (productoYaExiste) {
            return false;
        } else {
            // Se crea nueva instancia preservando atributos
            Producto nuevo = new Producto(producto.getNombre(), producto.getPrecio(), cantidad,
                    producto.getSku(), producto.getCategoria(), producto.isEsActivo(), producto.isDescuentoAplicable());
            detallesPedido.add(nuevo);
            return true;
        }
    }

    public boolean validarStock() { 
        if (detallesPedido.isEmpty()) return true; 

        for (Producto p : detallesPedido) {
            if (p.getCantidad() <= 0) {
                return false;
            }
        }
        return true;
    }
    
    // Getter para facilitar las pruebas
    public List<Producto> getDetallesPedido() {
        return detallesPedido;
    }
}

    


