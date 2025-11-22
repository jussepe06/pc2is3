public class Usuario {
    
    private int puntos;

    public void agregarPuntos(int p) {
        puntos += p;
    }

    public boolean esVip() {
        return puntos >= 50;
    }
    
}
