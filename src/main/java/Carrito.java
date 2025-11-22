import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private Usuario usuario;
    private List<Item> items = new ArrayList<>();

    public Carrito(Usuario u) {
        this.usuario = u;
    }

    public void agregar(Item i) {
        items.add(i);
    }

    public int getCantidad() {
        return items.size();
    }

    public double total() {
        double t = items.stream().mapToDouble(Item::getPrecio).sum();
        if (usuario.esVip()) {
            t *= 0.8; // 20% de descuento
        }
        return t;
    }
}