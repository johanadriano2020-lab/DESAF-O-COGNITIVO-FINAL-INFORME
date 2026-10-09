import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Paciente {
    private String dni;
    private String nombre;
    private String diagnostico;

    public Paciente(String dni, String nombre, String diagnostico) {
        this.dni = dni;
        this.nombre = nombre;
        this.diagnostico = diagnostico;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDiagnostico() { return diagnostico; }
}

public class SistemaHospital extends JFrame {

    private JTextField txtDni, txtNombre, txtDiagnostico;
    private JButton btnRegistrar, btnConsultar, btnLimpiar;
    private JTextArea txtResultado;

    private List<Paciente> listaPacientes = new ArrayList<>();

    public SistemaHospital() {

        setTitle("Sistema Hospitalario - Validación de Eventos");
        setSize(460, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("DNI:"));
        txtDni = new JTextField(8);
        add(txtDni);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(18);
        add(txtNombre);

        add(new JLabel("Diagnóstico:"));
        txtDiagnostico = new JTextField(18);
        add(txtDiagnostico);

        btnRegistrar = new JButton("Registrar Paciente");
        btnConsultar = new JButton("Consultar Paciente");

        add(btnRegistrar);
        add(btnConsultar);

        txtResultado = new JTextArea(10, 36);
        txtResultado.setEditable(false);
        add(new JScrollPane(txtResultado));

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = txtDni.getText().trim();
                String nombre = txtNombre.getText().trim();
                String diagnostico = txtDiagnostico.getText().trim();

                if (dni.isEmpty() || nombre.isEmpty()) {
                    txtResultado.setText("Error: Ingrese DNI y Nombre.");
                    return;
                }

                for (Paciente p : listaPacientes) {
                    if (p.getDni().equals(dni)) {
                        txtResultado.setText("Error: El paciente con DNI " + dni + " ya está registrado.");
                        return;
                    }
                }

                listaPacientes.add(new Paciente(dni, nombre, diagnostico));
                txtResultado.setText(" Éxito: Paciente registrado correctamente.\nTotal registrados: " + listaPacientes.size());

            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = txtDni.getText().trim();

                if (dni.isEmpty()) {
                    txtResultado.setText(" Ingrese un DNI en la casilla para consultar.");
                    return;
                }

                for (Paciente p : listaPacientes) {
                    if (p.getDni().equals(dni)) {
                        txtResultado.setText(" PACIENTE ENCONTRADO \n" +
                                "DNI: " + p.getDni() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Diagnóstico: " + p.getDiagnostico());
                        return;
                    }
                }
                txtResultado.setText("Resultado: Paciente no encontrado.");
            }
        });
    }

    public static void main(String[] args) {
        // Ejecución de la GUI
        SwingUtilities.invokeLater(() -> {
            new SistemaHospital().setVisible(true);
        });
    }
}

