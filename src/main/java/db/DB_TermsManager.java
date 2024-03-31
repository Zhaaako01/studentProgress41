package db;

import entities.Discipline;
import entities.Term;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.Connection.*;
import static db.Connection.password;

public class DB_TermsManager {

    public static List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term where status = '1'");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setDuration(rs.getString("term_duration"));
                term.setTermName(rs.getString("term_name"));
                terms.add(term);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return terms;
    }


    public static Term getTermByID(String id) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term where status = '1' and id = '" + id + "'");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setDuration(rs.getString("term_duration"));
                term.setTermName(rs.getString("term_name"));
                return term;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Discipline> getDisciplinesByTermID(int selectedTermId) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT disclipline_name FROM term_discipline as td\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "where td.id_term = '" + selectedTermId + "' and d.status = '1'");
            while (rs.next()) {
                Discipline discipline = new Discipline();
//                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline_name(rs.getString(1));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disciplines;
    }

    public static int getTermDurationBy(int selectedTermId) {
        int selectedTermDuration = -1;
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT term_duration FROM term where id= '" + selectedTermId + "' and status = '1'");
            while (rs.next()) {
                selectedTermDuration = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return selectedTermDuration;
    }


//    public static int createTermByUsingAndGetTermID(String termName, String termDuration) {
//        String createTermQuery = "INSERT INTO `term` (`term_name`, `term_duration`) VALUES (?, ?);";
//        int lastCreatedTermId = -1;
//        try {
//            Class.forName(driverName);
//            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement termStmn = conn.prepareStatement(createTermQuery, Statement.RETURN_GENERATED_KEYS);
//
//            conn.setAutoCommit(false);
//
//            // Добавление семестра
//            termStmn.setString(1, termName + termStmn.getGeneratedKeys());
//            termStmn.setString(2, termDuration);
//            termStmn.executeUpdate();
//
//
//            // Получение сгенерированного ID
//            try (ResultSet rs = termStmn.getGeneratedKeys()) {
//                if (rs.next()) {
//                    lastCreatedTermId = rs.getInt(1);
//                }
//            }
//
//            conn.commit();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return lastCreatedTermId;
//    }

    public static int createTermByUsingAndGetTermID(String termName, String termDuration) {
        String createTermQuery = "INSERT INTO `term` (`term_name`, `term_duration`) VALUES (?, ?);";
        String updateTermQuery = "UPDATE `term` SET `term_name` = ? WHERE `term_id` = ?;";
        int lastCreatedTermId = -1;
        PreparedStatement termStmn = null;
        PreparedStatement updateStmn = null;

        java.sql.Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            // Вставка семестра
            termStmn = conn.prepareStatement(createTermQuery, Statement.RETURN_GENERATED_KEYS);
            termStmn.setString(1, termName);
            termStmn.setString(2, termDuration);
            termStmn.executeUpdate();

            // Получение сгенерированного ID
            try (ResultSet rs = termStmn.getGeneratedKeys()) {
                if (rs.next()) {
                    lastCreatedTermId = rs.getInt(1);
                }
            }

            // Обновление termName с использованием сгенерированного ключа
            if (lastCreatedTermId != -1) {
                updateStmn = conn.prepareStatement(updateTermQuery);
                // Конкатенация termName с сгенерированным ID
                updateStmn.setString(1, termName + lastCreatedTermId);
                updateStmn.setInt(2, lastCreatedTermId);
                updateStmn.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to create term and get ID", e);
        } finally {
            try {
                if (termStmn != null) termStmn.close();
                if (updateStmn != null) updateStmn.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return lastCreatedTermId;
    }


//        public static void createTermByUsingAndGetTermID(String termName, String termDuration, String idOfSelectedDiscipline) {
//        String createTermQuery = "INSERT INTO `term` (`term_name`, `term_duration`) VALUES (?, ?);";
//        String createTermWithDisciplinesQuery = "INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES (?, ?);";
//        try {
//            Class.forName(driverName);
//            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement termStmn = conn.prepareStatement(createTermQuery, Statement.RETURN_GENERATED_KEYS);
//
//            conn.setAutoCommit(false);
//
//            // Добавление семестра
//            termStmn.setString(1, termName + termStmn.getGeneratedKeys());
//            termStmn.setString(2, termDuration);
//            termStmn.executeUpdate();
//
//
//            // Получение сгенерированного ID
//            try (ResultSet rs = termStmn.getGeneratedKeys()) {
//                if (rs.next()) {
//                    int lastCreatedTermId = rs.getInt(1);
//
//                    // Присвоение дисциплин к семестру
//                    try (PreparedStatement term_dcpStmt = conn.prepareStatement(createTermWithDisciplinesQuery)) {
//                        term_dcpStmt.setInt(1, lastCreatedTermId);
//                        term_dcpStmt.setInt(2, Integer.parseInt(idOfSelectedDiscipline));
//                        term_dcpStmt.executeUpdate();
//                    }
//                }
//            }
//
//            conn.commit(); // Подтверждение транзакции
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
