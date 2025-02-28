
package presentacion;

import dominio.CuentaService;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginView extends JFrame {
    private JTextField txtCuenta;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private CuentaService cuentaService;
    
    public LoginView(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
        setTitle("Cajero Automático - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        
        add(new JLabel("Número de Cuenta:"));
        txtCuenta = new JTextField();
        add(txtCuenta);
        
        add(new JLabel("Clave:"));
        txtClave = new JPasswordField();
        add(txtClave);
        
        btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });
        add(btnIngresar);
    }
    
    private void autenticarUsuario() {
        String cuenta = txtCuenta.getText();
        String clave = new String(txtClave.getPassword());
        
        if (cuentaService.autenticarUsuario(cuenta, clave)) {
            JOptionPane.showMessageDialog(this, "Ingreso exitoso");
        } else {
            JOptionPane.showMessageDialog(this, "Número de cuenta o clave incorrectos");
        }
    }
}