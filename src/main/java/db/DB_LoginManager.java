package db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static db.Connection.*;


public class DB_LoginManager {

    public static boolean canLogin(String login, String passwordDB, String role) {

        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where u.user = '" + login + "' and u.password = '" + passwordDB + "' and ur.id_role = '" + role + "'");
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
