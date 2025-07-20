public class VentanaInventario extends JFrame {
    private JList<String> listaTesoros;
    private DefaultListModel<String> modelo;

    public VentanaInventario(Jugador jugador) {
        setTitle("Inventario de " + jugador.getNombre());
        modelo = new DefaultListModel<>();
        listaTesoros = new JList<>(modelo);

        for (Tesoro t : jugador.getInventario().listarOrdenado()) {
            modelo.addElement(t.toString());
        }

        add(new JScrollPane(listaTesoros));
        setSize(300, 300);
        setVisible(true);
    }
}
