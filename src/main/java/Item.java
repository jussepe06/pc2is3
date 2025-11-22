public class Item {
    private String nombre;
    private double precio;

    public Item(String n, double p) {
        this.nombre = n;
        this.precio = p;
    }

    public double getPrecio() {
        return precio;
    }
}
