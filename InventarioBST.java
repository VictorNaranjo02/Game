import java.util.ArrayList;
import java.util.Random;

public class InventarioBST {
    private NodoTesoro raiz;
    private Random rand = new Random();

    public void insertar(Tesoro tesoro) {
        raiz = insertarRec(raiz, tesoro);
    }

    private NodoTesoro insertarRec(NodoTesoro nodo, Tesoro t) {
        if (nodo == null) return new NodoTesoro(t);
        if (t.compareTo(nodo.tesoro) < 0)
            nodo.izquierdo = insertarRec(nodo.izquierdo, t);
        else
            nodo.derecho = insertarRec(nodo.derecho, t);
        return nodo;
    }

    public boolean eliminar(String nombre) {
        if (buscar(nombre) == null) return false;
        raiz = eliminarRec(raiz, nombre);
        return true;
    }

    private NodoTesoro eliminarRec(NodoTesoro nodo, String nombre) {
        if (nodo == null) return null;
        int cmp = nombre.compareToIgnoreCase(nodo.tesoro.getNombre());

        if (cmp < 0)
            nodo.izquierdo = eliminarRec(nodo.izquierdo, nombre);
        else if (cmp > 0)
            nodo.derecho = eliminarRec(nodo.derecho, nombre);
        else {
            // Nodo con solo un hijo o sin hijos
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;

            // Nodo con dos hijos: obtener el sucesor
            NodoTesoro min = minValor(nodo.derecho);
            nodo.tesoro = min.tesoro;
            nodo.derecho = eliminarRec(nodo.derecho, min.tesoro.getNombre());
        }
        return nodo;
    }

    private NodoTesoro minValor(NodoTesoro nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public Tesoro buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Tesoro buscarRec(NodoTesoro nodo, String nombre) {
        if (nodo == null) return null;
        int cmp = nombre.compareToIgnoreCase(nodo.tesoro.getNombre());
        if (cmp == 0) return nodo.tesoro;
        if (cmp < 0) return buscarRec(nodo.izquierdo, nombre);
        else return buscarRec(nodo.derecho, nombre);
    }

    public ArrayList<Tesoro> listarOrdenado() {
        ArrayList<Tesoro> lista = new ArrayList<>();
        inOrden(raiz, lista);
        return lista;
    }

    private void inOrden(NodoTesoro nodo, ArrayList<Tesoro> lista) {
        if (nodo != null) {
            inOrden(nodo.izquierdo, lista);
            lista.add(nodo.tesoro);
            inOrden(nodo.derecho, lista);
        }
    }

    public boolean buscarConProbabilidad(String nombre) {
        Tesoro t = buscar(nombre);
        if (t != null) {
            return rand.nextDouble() < 0.7; // 70% de probabilidad de éxito
        }
        return false;
    }
}
