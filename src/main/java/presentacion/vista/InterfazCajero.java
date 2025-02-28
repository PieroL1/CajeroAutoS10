package presentacion.vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import modelo.Transaccion;
import presentacion.controlador.CajeroController;

/**
 * Interfaz gráfica principal del cajero automático
 */
public class InterfazCajero extends JFrame implements ActionListener {
    
    private CajeroController controlador;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Paneles para diferentes "pantallas"
    private JPanel loginPanel;
    private JPanel menuPanel;
    private JPanel depositoPanel;
    private JPanel transferenciaPanel;
    private JPanel retiroPanel;
    private JPanel historialPanel;
    
    // Componentes del panel de login
    private JTextField txtNumeroCuenta;
    private JPasswordField txtClave;
    private JButton btnLogin;
    
    // Componentes del panel de menú
    private JLabel lblBienvenida;
    private JButton btnConsultarSaldo;
    private JButton btnDepositar;
    private JButton btnTransferir;
    private JButton btnRetirar;
    private JButton btnHistorial;
    private JButton btnSalir;
    
    // Componentes del panel de depósito
    private JTextField txtMontoDeposito;
    private JButton btnConfirmarDeposito;
    private JButton btnCancelarDeposito;
    
    // Componentes del panel de transferencia
    private JTextField txtNumeroCuentaDestino;
    private JTextField txtMontoTransferencia;
    private JButton btnConfirmarTransferencia;
    private JButton btnCancelarTransferencia;
    
    // Componentes del panel de retiro
    private JTextField txtMontoRetiro;
    private JButton btnConfirmarRetiro;
    private JButton btnCancelarRetiro;
    
    // Componentes del panel de historial
    private JTextArea txtAreaHistorial;
    private JButton btnVolverHistorial;
    
    public InterfazCajero() {
        controlador = new CajeroController();
        controlador.setVista(this);
        
        setTitle("Cajero Automático");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        crearPanelLogin();
        crearPanelMenu();
        crearPanelDeposito();
        crearPanelTransferencia();
        crearPanelRetiro();
        crearPanelHistorial();
        
        mainPanel.add(loginPanel, "login");
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(depositoPanel, "deposito");
        mainPanel.add(transferenciaPanel, "transferencia");
        mainPanel.add(retiroPanel, "retiro");
        mainPanel.add(historialPanel, "historial");
        
        cardLayout.show(mainPanel, "login");
        
        add(mainPanel);
    }
    
private void crearPanelLogin() {
    loginPanel = new JPanel();
    loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
    loginPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
    loginPanel.setBackground(new Color(34, 40, 49)); // Fondo oscuro elegante

    JLabel lblTitulo = new JLabel("Bienvenido al Cajero Automático");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel lblCuenta = new JLabel("Número de Cuenta:");
    lblCuenta.setFont(new Font("Arial", Font.PLAIN, 16));
    lblCuenta.setForeground(new Color(200, 200, 200));
    lblCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtNumeroCuenta = new JTextField(10);
    txtNumeroCuenta.setMaximumSize(new Dimension(220, 40));
    txtNumeroCuenta.setFont(new Font("Arial", Font.PLAIN, 16));
    txtNumeroCuenta.setHorizontalAlignment(JTextField.CENTER);
    txtNumeroCuenta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true), // Borde azul brillante
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    JLabel lblClave = new JLabel("Clave:");
    lblClave.setFont(new Font("Arial", Font.PLAIN, 16));
    lblClave.setForeground(new Color(200, 200, 200));
    lblClave.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtClave = new JPasswordField(10);
    txtClave.setMaximumSize(new Dimension(220, 40));
    txtClave.setFont(new Font("Arial", Font.PLAIN, 16));
    txtClave.setHorizontalAlignment(JTextField.CENTER);
    txtClave.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true), // Borde azul brillante
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    // Color para el botón
    Color colorLogin = new Color(0, 123, 255); // Azul brillante
    Color botonTexto = Color.WHITE;
    Dimension buttonSize = new Dimension(220, 50);

    btnLogin = crearBoton("Iniciar Sesión", colorLogin, botonTexto, buttonSize);

    // Agregar componentes al panel
    loginPanel.add(lblTitulo);
    loginPanel.add(Box.createVerticalStrut(30));
    loginPanel.add(lblCuenta);
    loginPanel.add(Box.createVerticalStrut(10));
    loginPanel.add(txtNumeroCuenta);
    loginPanel.add(Box.createVerticalStrut(20));
    loginPanel.add(lblClave);
    loginPanel.add(Box.createVerticalStrut(10));
    loginPanel.add(txtClave);
    loginPanel.add(Box.createVerticalStrut(30));
    loginPanel.add(btnLogin);
}

    
private void crearPanelMenu() {
    menuPanel = new JPanel();
    setSize(450, 600);

    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
    menuPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
    menuPanel.setBackground(new Color(34, 40, 49)); // Fondo oscuro, más suave

    lblBienvenida = new JLabel("Bienvenido/a");
    lblBienvenida.setFont(new Font("Arial", Font.BOLD, 20));
    lblBienvenida.setForeground(Color.WHITE);
    lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel lblOpciones = new JLabel("Seleccione una operación:");
    lblOpciones.setForeground(new Color(200, 200, 200)); // Gris claro
    lblOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Colores brillantes para los botones
    Color botonColor = new Color(0, 123, 255); // Azul brillante
    Color botonTexto = Color.WHITE;
    Dimension buttonSize = new Dimension(220, 50); // Botones un poco más grandes

    btnConsultarSaldo = crearBoton("Consultar Saldo", botonColor, botonTexto, buttonSize);
    btnDepositar = crearBoton("Depositar", new Color(40, 167, 69), botonTexto, buttonSize); // Verde brillante
    btnTransferir = crearBoton("Transferir", new Color(255, 193, 7), botonTexto, buttonSize); // Amarillo brillante
    btnRetirar = crearBoton("Retirar", new Color(220, 53, 69), botonTexto, buttonSize); // Rojo brillante
    btnHistorial = crearBoton("Historial", new Color(23, 162, 184), botonTexto, buttonSize); // Azul más suave
    btnSalir = crearBoton("Cerrar Sesión", new Color(233, 30, 99), botonTexto, buttonSize); // Rosa vibrante

    // Agregar componentes al panel
    menuPanel.add(lblBienvenida);
    menuPanel.add(Box.createVerticalStrut(30));
    menuPanel.add(lblOpciones);
    menuPanel.add(Box.createVerticalStrut(20));
    menuPanel.add(btnConsultarSaldo);
    menuPanel.add(Box.createVerticalStrut(15));
    menuPanel.add(btnDepositar);
    menuPanel.add(Box.createVerticalStrut(15));
    menuPanel.add(btnTransferir);
    menuPanel.add(Box.createVerticalStrut(15));
    menuPanel.add(btnRetirar);
    menuPanel.add(Box.createVerticalStrut(15));
    menuPanel.add(btnHistorial);
    menuPanel.add(Box.createVerticalStrut(40));
    menuPanel.add(btnSalir);
}

private JButton crearBoton(String texto, Color colorFondo, Color colorTexto, Dimension size) {
    JButton boton = new JButton(texto);
    boton.setAlignmentX(Component.CENTER_ALIGNMENT);
    boton.setBackground(colorFondo);
    boton.setForeground(colorTexto);
    boton.setFont(new Font("Arial", Font.BOLD, 16));
    boton.setFocusPainted(false);
    boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    boton.setPreferredSize(size);
    boton.setMaximumSize(size);
    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // Efecto cuando el cursor pasa sobre el botón
    boton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            boton.setBackground(colorFondo.darker()); // Color más oscuro cuando se pasa el mouse
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            boton.setBackground(colorFondo); // Vuelve al color original
        }
    });

    boton.addActionListener(this);
    return boton;
}


    
private void crearPanelDeposito() {
    depositoPanel = new JPanel();
    depositoPanel.setLayout(new BoxLayout(depositoPanel, BoxLayout.Y_AXIS));
    depositoPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
    depositoPanel.setBackground(new Color(34, 40, 49)); // Fondo oscuro elegante

    JLabel lblTitulo = new JLabel("Depósito de Efectivo");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel lblMonto = new JLabel("Ingrese el monto a depositar:");
    lblMonto.setFont(new Font("Arial", Font.PLAIN, 16));
    lblMonto.setForeground(new Color(200, 200, 200)); // Gris claro para mejor contraste
    lblMonto.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtMontoDeposito = new JTextField(10);
    txtMontoDeposito.setMaximumSize(new Dimension(220, 40)); // Más grande para mejor ingreso de datos
    txtMontoDeposito.setFont(new Font("Arial", Font.PLAIN, 16));
    txtMontoDeposito.setHorizontalAlignment(JTextField.CENTER);
    txtMontoDeposito.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true), // Borde azul con esquinas redondeadas
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    // Colores para los botones
    Color colorConfirmar = new Color(40, 167, 69); // Verde brillante
    Color colorCancelar = new Color(220, 53, 69); // Rojo brillante
    Color botonTexto = Color.WHITE;
    Dimension buttonSize = new Dimension(220, 50);

    btnConfirmarDeposito = crearBoton("Confirmar", colorConfirmar, botonTexto, buttonSize);
    btnCancelarDeposito = crearBoton("Cancelar", colorCancelar, botonTexto, buttonSize);

    // Agregar componentes al panel
    depositoPanel.add(lblTitulo);
    depositoPanel.add(Box.createVerticalStrut(30));
    depositoPanel.add(lblMonto);
    depositoPanel.add(Box.createVerticalStrut(10));
    depositoPanel.add(txtMontoDeposito);
    depositoPanel.add(Box.createVerticalStrut(25));
    depositoPanel.add(btnConfirmarDeposito);
    depositoPanel.add(Box.createVerticalStrut(15));
    depositoPanel.add(btnCancelarDeposito);
}


    
private void crearPanelTransferencia() {
    transferenciaPanel = new JPanel();
    transferenciaPanel.setLayout(new BoxLayout(transferenciaPanel, BoxLayout.Y_AXIS));
    transferenciaPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
    transferenciaPanel.setBackground(new Color(34, 40, 49)); // Fondo oscuro elegante

    JLabel lblTitulo = new JLabel("Transferencia de Efectivo");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel lblCuentaDestino = new JLabel("Número de Cuenta Destino:");
    lblCuentaDestino.setFont(new Font("Arial", Font.PLAIN, 16));
    lblCuentaDestino.setForeground(new Color(200, 200, 200));
    lblCuentaDestino.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtNumeroCuentaDestino = new JTextField(10);
    txtNumeroCuentaDestino.setMaximumSize(new Dimension(220, 40));
    txtNumeroCuentaDestino.setFont(new Font("Arial", Font.PLAIN, 16));
    txtNumeroCuentaDestino.setHorizontalAlignment(JTextField.CENTER);
    txtNumeroCuentaDestino.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 172, 193), 2, true), // Borde turquesa
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    JLabel lblMonto = new JLabel("Ingrese el monto a transferir:");
    lblMonto.setFont(new Font("Arial", Font.PLAIN, 16));
    lblMonto.setForeground(new Color(200, 200, 200));
    lblMonto.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtMontoTransferencia = new JTextField(10);
    txtMontoTransferencia.setMaximumSize(new Dimension(220, 40));
    txtMontoTransferencia.setFont(new Font("Arial", Font.PLAIN, 16));
    txtMontoTransferencia.setHorizontalAlignment(JTextField.CENTER);
    txtMontoTransferencia.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 172, 193), 2, true), // Borde turquesa
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    // Colores para los botones
    Color colorConfirmar = new Color(0, 172, 193); // Turquesa brillante
    Color colorCancelar = new Color(220, 53, 69); // Rojo vibrante
    Color botonTexto = Color.WHITE;
    Dimension buttonSize = new Dimension(220, 50);

    btnConfirmarTransferencia = crearBoton("Confirmar", colorConfirmar, botonTexto, buttonSize);
    btnCancelarTransferencia = crearBoton("Cancelar", colorCancelar, botonTexto, buttonSize);

    // Agregar componentes al panel
    transferenciaPanel.add(lblTitulo);
    transferenciaPanel.add(Box.createVerticalStrut(30));
    transferenciaPanel.add(lblCuentaDestino);
    transferenciaPanel.add(Box.createVerticalStrut(10));
    transferenciaPanel.add(txtNumeroCuentaDestino);
    transferenciaPanel.add(Box.createVerticalStrut(20));
    transferenciaPanel.add(lblMonto);
    transferenciaPanel.add(Box.createVerticalStrut(10));
    transferenciaPanel.add(txtMontoTransferencia);
    transferenciaPanel.add(Box.createVerticalStrut(30));
    transferenciaPanel.add(btnConfirmarTransferencia);
    transferenciaPanel.add(Box.createVerticalStrut(15));
    transferenciaPanel.add(btnCancelarTransferencia);
}

    
private void crearPanelRetiro() {
    retiroPanel = new JPanel();
    retiroPanel.setLayout(new BoxLayout(retiroPanel, BoxLayout.Y_AXIS));
    retiroPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
    retiroPanel.setBackground(new Color(34, 40, 49)); // Fondo oscuro elegante

    JLabel lblTitulo = new JLabel("Retiro de Efectivo");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel lblMonto = new JLabel("Ingrese el monto a retirar:");
    lblMonto.setFont(new Font("Arial", Font.PLAIN, 16));
    lblMonto.setForeground(new Color(200, 200, 200)); // Gris claro para mejor contraste
    lblMonto.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtMontoRetiro = new JTextField(10);
    txtMontoRetiro.setMaximumSize(new Dimension(220, 40)); // Más grande para mejor ingreso de datos
    txtMontoRetiro.setFont(new Font("Arial", Font.PLAIN, 16));
    txtMontoRetiro.setHorizontalAlignment(JTextField.CENTER);
    txtMontoRetiro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 87, 34), 2, true), // Borde naranja con esquinas redondeadas
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
    ));

    // Colores para los botones
    Color colorConfirmar = new Color(255, 87, 34); // Naranja brillante
    Color colorCancelar = new Color(220, 53, 69); // Rojo brillante
    Color botonTexto = Color.WHITE;
    Dimension buttonSize = new Dimension(220, 50);

    btnConfirmarRetiro = crearBoton("Confirmar", colorConfirmar, botonTexto, buttonSize);
    btnCancelarRetiro = crearBoton("Cancelar", colorCancelar, botonTexto, buttonSize);

    // Agregar componentes al panel
    retiroPanel.add(lblTitulo);
    retiroPanel.add(Box.createVerticalStrut(30));
    retiroPanel.add(lblMonto);
    retiroPanel.add(Box.createVerticalStrut(10));
    retiroPanel.add(txtMontoRetiro);
    retiroPanel.add(Box.createVerticalStrut(25));
    retiroPanel.add(btnConfirmarRetiro);
    retiroPanel.add(Box.createVerticalStrut(15));
    retiroPanel.add(btnCancelarRetiro);
}

    
private void crearPanelHistorial() {
    historialPanel = new JPanel();
    historialPanel.setLayout(new BoxLayout(historialPanel, BoxLayout.Y_AXIS));
    historialPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
    historialPanel.setBackground(new Color(34, 40, 49)); // Fondo oscuro elegante

    JLabel lblTitulo = new JLabel("Historial de Transacciones");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    lblTitulo.setForeground(Color.WHITE);
    lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

    txtAreaHistorial = new JTextArea(15, 40);
    txtAreaHistorial.setEditable(false);
    txtAreaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Fuente monoespaciada para alineación uniforme
    txtAreaHistorial.setBackground(new Color(44, 52, 62)); // Fondo oscuro para mejor contraste
    txtAreaHistorial.setForeground(Color.WHITE);
    txtAreaHistorial.setMargin(new Insets(10, 10, 10, 10)); // Márgenes internos para evitar que el texto toque los bordes
    txtAreaHistorial.setLineWrap(true); // Ajusta las líneas largas automáticamente
    txtAreaHistorial.setWrapStyleWord(true); // Evita cortar palabras a la mitad

    JScrollPane scrollPane = new JScrollPane(txtAreaHistorial);
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 136), 2, true)); // Borde verde azulado
    scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Color para el botón
    Color colorVolver = new Color(0, 150, 136); // Verde azulado brillante
    Color botonTexto = Color.WHITE;
    Dimension buttonSize = new Dimension(220, 50);

    btnVolverHistorial = crearBoton("Volver", colorVolver, botonTexto, buttonSize);

    // Agregar componentes al panel
    historialPanel.add(lblTitulo);
    historialPanel.add(Box.createVerticalStrut(20));
    historialPanel.add(scrollPane);
    historialPanel.add(Box.createVerticalStrut(20));
    historialPanel.add(btnVolverHistorial);
}


    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
    UIManager.put("OptionPane.background", new Color(34, 40, 49)); // Fondo oscuro
    UIManager.put("Panel.background", new Color(34, 40, 49)); // Panel del mensaje
    UIManager.put("OptionPane.messageForeground", Color.WHITE); // Texto en blanco
    UIManager.put("Button.background", new Color(0, 123, 255)); // Azul para botones
    UIManager.put("Button.foreground", Color.WHITE); // Texto blanco en botones
    UIManager.put("Button.border", BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Bordes más limpios
    UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14)); // Fuente más grande
    UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 16)); // Fuente del mensaje


        
        
        // Manejo de eventos del panel de login
        if (source == btnLogin) {
            String numeroCuenta = txtNumeroCuenta.getText();
            String clave = new String(txtClave.getPassword());
            
            if (numeroCuenta.isEmpty() || clave.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor, ingrese número de cuenta y clave", 
                        "Error de validación", 
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean autenticado = controlador.iniciarSesion(numeroCuenta, clave);
            if (autenticado) {
                lblBienvenida.setText("Bienvenido/a a su cuenta " + numeroCuenta);
                cardLayout.show(mainPanel, "menu");
                txtClave.setText("");
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Número de cuenta o clave incorrectos", 
                        "Error de autenticación", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Manejo de eventos del panel de menú
        else if (source == btnConsultarSaldo) {
            double saldo = controlador.consultarSaldo();
            if (saldo >= 0) {
                JOptionPane.showMessageDialog(this, 
                        "Su saldo actual es: $" + String.format("%.2f", saldo), 
                        "Consulta de Saldo", 
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Error al consultar el saldo", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (source == btnDepositar) {
            txtMontoDeposito.setText("");
            cardLayout.show(mainPanel, "deposito");
        }
        else if (source == btnTransferir) {
            txtMontoTransferencia.setText("");
            txtNumeroCuentaDestino.setText("");
            cardLayout.show(mainPanel, "transferencia");
        }
        else if (source == btnRetirar) {
            txtMontoRetiro.setText("");
            cardLayout.show(mainPanel, "retiro");
        }
        else if (source == btnHistorial) {
            List<Transaccion> transacciones = controlador.obtenerHistorialTransacciones();
            if (transacciones != null) {
                StringBuilder historial = new StringBuilder();
                for (Transaccion transaccion : transacciones) {
                    historial.append(transaccion.getFecha()).append(" - ")
                             .append(transaccion.getTipo()).append(" - $")
                             .append(transaccion.getMonto()).append(" - Saldo: $")
                             .append(transaccion.getSaldoDespues()).append("\n");
                }
                txtAreaHistorial.setText(historial.toString());
                cardLayout.show(mainPanel, "historial");
            } else {
                JOptionPane.showMessageDialog(this, 
                        "No se pudo obtener el historial de transacciones", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (source == btnSalir) {
            controlador.cerrarSesion();
            txtNumeroCuenta.setText("");
            txtClave.setText("");
            cardLayout.show(mainPanel, "login");
        }
        
        // Manejo de eventos del panel de depósito
        else if (source == btnConfirmarDeposito) {
            try {
                double monto = Double.parseDouble(txtMontoDeposito.getText());
                
                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, 
                            "El monto debe ser mayor a cero", 
                            "Error de validación", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                boolean depositoExitoso = controlador.realizarDeposito(monto);
                if (depositoExitoso) {
                    JOptionPane.showMessageDialog(this, 
                            "Depósito realizado con éxito\nNuevo saldo: $" + 
                                    String.format("%.2f", controlador.consultarSaldo()), 
                            "Depósito Exitoso", 
                            JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "menu");
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Error al realizar el depósito", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor, ingrese un monto válido", 
                        "Error de formato", 
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (source == btnCancelarDeposito) {
            cardLayout.show(mainPanel, "menu");
        }
        
        // Manejo de eventos del panel de transferencia
        else if (source == btnConfirmarTransferencia) {
            try {
                double monto = Double.parseDouble(txtMontoTransferencia.getText());
                String numeroCuentaDestino = txtNumeroCuentaDestino.getText();
                
                if (numeroCuentaDestino.isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                            "Por favor, ingrese el número de cuenta destino", 
                            "Error de validación", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, 
                            "El monto debe ser mayor a cero", 
                            "Error de validación", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                boolean transferenciaExitosa = controlador.realizarTransferencia(numeroCuentaDestino, monto);
                if (transferenciaExitosa) {
                    JOptionPane.showMessageDialog(this, 
                            "Transferencia realizada con éxito", 
                            "Transferencia Exitosa", 
                            JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "menu");
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Error al realizar la transferencia", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor, ingrese un monto válido", 
                        "Error de formato", 
                        JOptionPane.WARNING_MESSAGE);
            }
        }
       else if (source == btnCancelarTransferencia) {
            cardLayout.show(mainPanel, "menu");
        }
        
        // Manejo de eventos del panel de retiro
        else if (source == btnConfirmarRetiro) {
            try {
                double monto = Double.parseDouble(txtMontoRetiro.getText());
                if (monto <= 0) {
                    JOptionPane.showMessageDialog(this, 
                            "El monto debe ser mayor a cero", 
                            "Error de validación", 
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                boolean retiroExitoso = controlador.realizarRetiro(monto);
                if (retiroExitoso) {
                    JOptionPane.showMessageDialog(this, 
                            "Retiro realizado con éxito\nNuevo saldo: $" + 
                                    String.format("%.2f", controlador.consultarSaldo()), 
                            "Retiro Exitoso", 
                            JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "menu");
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Error al realizar el retiro. Verifique su saldo.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor, ingrese un monto válido", 
                        "Error de formato", 
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (source == btnCancelarRetiro) {
            cardLayout.show(mainPanel, "menu");
        }
        
        // Manejo de eventos del panel de historial
        else if (source == btnVolverHistorial) {
            cardLayout.show(mainPanel, "menu");
        }
    }
    
    /**
     * Método principal para iniciar la aplicación
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazCajero cajero = new InterfazCajero();
            cajero.setVisible(true);
        });
    }
}