public class Tesoro implements Comparable<Tesoro> {
    private String nombre;
    private int valor;

    public Tesoro(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() { return nombre; }
    public int getValor() { return valor; }

    @Override
    public int compareTo(Tesoro otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre); // o por valor, si prefieres
    }

    @Override
    public String toString() {
        return nombre + " (Valor: " + valor + ")";
    }
}
