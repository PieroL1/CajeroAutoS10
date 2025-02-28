
package presentacion;

import dominio.CuentaService;

public class CajeroAutomaticoApp {
    public static void main(String[] args) {
        CuentaService cuentaService = new CuentaService();
        LoginView loginView = new LoginView(cuentaService);
        loginView.setVisible(true);
    }
}