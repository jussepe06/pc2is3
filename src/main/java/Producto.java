import java.util.Objects;

public class Producto {
    

    private String nombre;
    private double precio;
    private int cantidad;
    private String sku;
    private String categoria;
    private boolean esActivo;
    private boolean descuentoAplicable;


    // Constructor completo
    public Producto(String nombre, double precio, int cantidad, String sku, String categoria, boolean esActivo, boolean descuentoAplicable) {
        this.nombre = nombre;
        setPrecio(precio); // Usamos el setter para validar
        setCantidad(cantidad); // Usamos el setter para validar
        this.sku = sku;
        this.categoria = categoria;
        this.esActivo = esActivo;
        this.descuentoAplicable = descuentoAplicable;
    }

    // Constructor simplificado (útil para el método agregarProducto que crea copias)
    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        setPrecio(precio);
        setCantidad(cantidad);
    }

    // Getters y Setters con validaciones [cite: 148]
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precio = precio;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public boolean isEsActivo() { return esActivo; }
    public void setEsActivo(boolean esActivo) { this.esActivo = esActivo; }

    public boolean isDescuentoAplicable() { return descuentoAplicable; }
    public void setDescuentoAplicable(boolean descuentoAplicable) { this.descuentoAplicable = descuentoAplicable; }

    // equals y hashCode basados SOLO en SKU [cite: 149]
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(sku, producto.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Producto{sku='" + sku + "', nombre='" + nombre + "', cantidad=" + cantidad + "}";
    }
}

    
