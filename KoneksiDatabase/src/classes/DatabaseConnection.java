package classes;

// controller

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class DatabaseConnection {
    public static Connection getKoneksi(String host, String port, String username, String password, String db) {
        String connection = "jdbc:mysql://" + host + ":" + port + "/" + db;
        Connection koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            getConnect = DriverManager.getConnection(connection,
            username, password);
            System.out.println("Koneksi Berhasil");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Database Error");
            getConnect = null;
        }
        return getConnect;
    }
}