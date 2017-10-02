package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author felipe
 */
public class ConnectionMySQL {

    private String connectionString;
    private String driver;
    private String usuario;
    private String senha;
    private Connection connection = null;

    public ConnectionMySQL(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Connection conectar() {

        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(connectionString, usuario, senha);
                System.out.println("Estamos conectados ao BD " + connectionString);
            } catch (Exception ex) {
                System.out.println("Falha, detectamos um erro na conexao");
                System.out.println(ex.toString() + ex.getMessage());
            }
        }
        return connection;
    }
}
