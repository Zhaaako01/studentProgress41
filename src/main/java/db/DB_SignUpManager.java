package db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static db.Connection.*;

public class DB_SignUpManager {

    public static void createAnUser(String login, String passwordDB, Integer role) {
        String userInsertQuery = "INSERT INTO `user` (`user`, `password`) VALUES (?, ?)";
        String roleInsertQuery = "INSERT INTO `user_role` (`id_user`, `id_role`) VALUES (?, ?)";

        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement userStmt = conn.prepareStatement(userInsertQuery, Statement.RETURN_GENERATED_KEYS);

            conn.setAutoCommit(false);

            // Добавление пользователя
            userStmt.setString(1, login);
            userStmt.setString(2, passwordDB);
            userStmt.executeUpdate();

            // Получение сгенерированного ID
            try (ResultSet rs = userStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int userId = rs.getInt(1);

                    // Добавление роли пользователя
                    try (PreparedStatement roleStmt = conn.prepareStatement(roleInsertQuery)) {
                        roleStmt.setInt(1, userId);
                        roleStmt.setInt(2, role);
                        roleStmt.executeUpdate();
                    }
                }
            }

            conn.commit(); // Подтверждение транзакции
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    public static void createAnUserAndGetHisID(String login, String passwordDB, Integer role) {
//        try {
//            Class.forName(driverName);
//            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
//            Statement stmt = conn.createStatement();
//            stmt.execute("INSERT INTO `user` (`user`, `password`) VALUES ('" + login + "', '" + passwordDB + "')");
//            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
//            while (rs.next()) {
//            stmt.execute("INSERT INTO `user_role` (`id_user`, `id_role`) VALUES ('" + rs.getInt(1) + "', '" + role + "');");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
