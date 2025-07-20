public class VentanaRobo extends JFrame {
    public VentanaRobo(Jugador atacante, Jugador victima) {
        setTitle("Robar a " + victima.getNombre());
        setLayout(new BorderLayout());

        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> lista = new JList<>(modelo);
        for (Tesoro t : victima.getInventario().listarOrdenado()) {
            modelo.addElement(t.getNombre());
        }

        JButton btnRobar = new JButton("Robar");
        btnRobar.addActionListener(e -> {
            String seleccionado = lista.getSelectedValue();
            if (seleccionado != null) {
                boolean exito = atacante.intentarRobar(victima, seleccionado);
                JOptionPane.showMessageDialog(this,
                    exito ? "¡Robo exitoso!" : "¡Robo fallido!",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(new JScrollPane(lista), BorderLayout.CENTER);
        add(btnRobar, BorderLayout.SOUTH);
        setSize(300, 200);
        setVisible(true);
    }
}
