package presentacion.vista;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
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
    private JPanel retiroPanel;
    
    // Componentes del panel de login
    private JTextField txtNumeroCuenta;
    private JPasswordField txtClave;
    private JButton btnLogin;
    
    // Componentes del panel de menú
    private JLabel lblBienvenida;
    private JButton btnConsultarSaldo;
    private JButton btnDepositar;
    private JButton btnRetirar;
    private JButton btnSalir;
    
    // Componentes del panel de depósito
    private JTextField txtNumeroCuentaDestino;
    private JTextField txtMontoDeposito;
    private JButton btnConfirmarDeposito;
    private JButton btnCancelarDeposito;
    
    // Componentes del panel de retiro
    private JTextField txtMontoRetiro;
    private JButton btnConfirmarRetiro;
    private JButton btnCancelarRetiro;
    
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
        crearPanelRetiro();
        
        mainPanel.add(loginPanel, "login");
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(depositoPanel, "deposito");
        mainPanel.add(retiroPanel, "retiro");
        
        cardLayout.show(mainPanel, "login");
        
        add(mainPanel);
    }
    
    private void crearPanelLogin() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        JLabel lblTitulo = new JLabel("Bienvenido al Cajero Automático");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lblCuenta = new JLabel("Número de Cuenta:");
        lblCuenta.setAlignmentX(CENTER_ALIGNMENT);
        
        txtNumeroCuenta = new JTextField(10);
        txtNumeroCuenta.setMaximumSize(new Dimension(200, 30));
        txtNumeroCuenta.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lblClave = new JLabel("Clave:");
        lblClave.setAlignmentX(CENTER_ALIGNMENT);
        
        txtClave = new JPasswordField(10);
        txtClave.setMaximumSize(new Dimension(200, 30));
        txtClave.setAlignmentX(CENTER_ALIGNMENT);
        
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        btnLogin.addActionListener(this);
        
        loginPanel.add(lblTitulo);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(lblCuenta);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(txtNumeroCuenta);
        loginPanel.add(Box.createVerticalStrut(15));
        loginPanel.add(lblClave);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(txtClave);
        loginPanel.add(Box.createVerticalStrut(25));
        loginPanel.add(btnLogin);
    }
    
    private void crearPanelMenu() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        lblBienvenida = new JLabel("Bienvenido/a");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 16));
        lblBienvenida.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lblOpciones = new JLabel("Seleccione una operación:");
        lblOpciones.setAlignmentX(CENTER_ALIGNMENT);
        
        btnConsultarSaldo = new JButton("Consultar Saldo");
        btnConsultarSaldo.setAlignmentX(CENTER_ALIGNMENT);
        btnConsultarSaldo.addActionListener(this);
        
        btnDepositar = new JButton("Depositar");
        btnDepositar.setAlignmentX(CENTER_ALIGNMENT);
        btnDepositar.addActionListener(this);
        
        btnRetirar = new JButton("Retirar");
        btnRetirar.setAlignmentX(CENTER_ALIGNMENT);
        btnRetirar.addActionListener(this);
        
        btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setAlignmentX(CENTER_ALIGNMENT);
        btnSalir.addActionListener(this);
        
        menuPanel.add(lblBienvenida);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(lblOpciones);
        menuPanel.add(Box.createVerticalStrut(15));
        menuPanel.add(btnConsultarSaldo);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnDepositar);
        menuPanel.add(Box.createVerticalStrut(10));
        menuPanel.add(btnRetirar);
        menuPanel.add(Box.createVerticalStrut(30));
        menuPanel.add(btnSalir);
    }
    
    private void crearPanelDeposito() {
        depositoPanel = new JPanel();
        depositoPanel.setLayout(new BoxLayout(depositoPanel, BoxLayout.Y_AXIS));
        depositoPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        JLabel lblTitulo = new JLabel("Depósito de Efectivo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lblCuentaDestino = new JLabel("Número de Cuenta Destino:");
        lblCuentaDestino.setAlignmentX(CENTER_ALIGNMENT);
        
        txtNumeroCuentaDestino = new JTextField(10);
        txtNumeroCuentaDestino.setMaximumSize(new Dimension(200, 30));
        txtNumeroCuentaDestino.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lblMonto = new JLabel("Ingrese el monto a depositar:");
        lblMonto.setAlignmentX(CENTER_ALIGNMENT);
        
        txtMontoDeposito = new JTextField(10);
        txtMontoDeposito.setMaximumSize(new Dimension(200, 30));
        txtMontoDeposito.setAlignmentX(CENTER_ALIGNMENT);
        
        btnConfirmarDeposito = new JButton("Confirmar");
        btnConfirmarDeposito.setAlignmentX(CENTER_ALIGNMENT);
        btnConfirmarDeposito.addActionListener(this);
        
        btnCancelarDeposito = new JButton("Cancelar");
        btnCancelarDeposito.setAlignmentX(CENTER_ALIGNMENT);
        btnCancelarDeposito.addActionListener(this);
        
        depositoPanel.add(lblTitulo);
        depositoPanel.add(Box.createVerticalStrut(30));
        depositoPanel.add(lblCuentaDestino);
        depositoPanel.add(Box.createVerticalStrut(5));
        depositoPanel.add(txtNumeroCuentaDestino);
        depositoPanel.add(Box.createVerticalStrut(15));
        depositoPanel.add(lblMonto);
        depositoPanel.add(Box.createVerticalStrut(5));
        depositoPanel.add(txtMontoDeposito);
        depositoPanel.add(Box.createVerticalStrut(25));
        depositoPanel.add(btnConfirmarDeposito);
        depositoPanel.add(Box.createVerticalStrut(10));
        depositoPanel.add(btnCancelarDeposito);
    }
    
    private void crearPanelRetiro() {
        retiroPanel = new JPanel();
        retiroPanel.setLayout(new BoxLayout(retiroPanel, BoxLayout.Y_AXIS));
        retiroPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        
        JLabel lblTitulo = new JLabel("Retiro de Efectivo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lblMonto = new JLabel("Ingrese el monto a retirar:");
        lblMonto.setAlignmentX(CENTER_ALIGNMENT);
        
        txtMontoRetiro = new JTextField(10);
        txtMontoRetiro.setMaximumSize(new Dimension(200, 30));
        txtMontoRetiro.setAlignmentX(CENTER_ALIGNMENT);
        
        btnConfirmarRetiro = new JButton("Confirmar");
        btnConfirmarRetiro.setAlignmentX(CENTER_ALIGNMENT);
        btnConfirmarRetiro.addActionListener(this);
        
        btnCancelarRetiro = new JButton("Cancelar");
        btnCancelarRetiro.setAlignmentX(CENTER_ALIGNMENT);
        btnCancelarRetiro.addActionListener(this);
        
        retiroPanel.add(lblTitulo);
        retiroPanel.add(Box.createVerticalStrut(30));
        retiroPanel.add(lblMonto);
        retiroPanel.add(Box.createVerticalStrut(5));
        retiroPanel.add(txtMontoRetiro);
        retiroPanel.add(Box.createVerticalStrut(25));
        retiroPanel.add(btnConfirmarRetiro);
        retiroPanel.add(Box.createVerticalStrut(10));
        retiroPanel.add(btnCancelarRetiro);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
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
            txtNumeroCuentaDestino.setText("");
            cardLayout.show(mainPanel, "deposito");
        }
        else if (source == btnRetirar) {
            txtMontoRetiro.setText("");
            cardLayout.show(mainPanel, "retiro");
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
                
                boolean depositoExitoso = controlador.realizarDeposito(numeroCuentaDestino, monto);
                if (depositoExitoso) {
                    JOptionPane.showMessageDialog(this, 
                            "Depósito realizado con éxito", 
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