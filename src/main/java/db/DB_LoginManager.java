package db;

import entities.Role;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Role> getAllRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM role");
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRole(rs.getString("role"));
                roles.add(role);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return roles;
    }
}
