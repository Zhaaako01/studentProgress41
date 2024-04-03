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


    public static int createTermByUsingAndGetTermID(String termName, String termDuration) {
        String createTermQuery = "INSERT INTO `term` (`term_name`, `term_duration`) VALUES (?, ?);";
        int lastCreatedTermId = -1;
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement termStmn = conn.prepareStatement(createTermQuery, Statement.RETURN_GENERATED_KEYS);

            conn.setAutoCommit(false);

            // Добавление семестра
            termStmn.setString(1, termName);
            termStmn.setString(2, termDuration);
            termStmn.executeUpdate();

            // Получение сгенерированного ID
            try (ResultSet rs = termStmn.getGeneratedKeys()) {
                if (rs.next()) {
                    lastCreatedTermId = rs.getInt(1);
                }
            }

            conn.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lastCreatedTermId;
    }

    public static void createTermWithDisciplines(int termID, String idOfSelectedDiscipline) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + termID + "', '" + idOfSelectedDiscipline + "')");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNumberForTermName(String nameOfLastCreatedTerm, int idOfLastCreatedTerm) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `term_name` = '" + nameOfLastCreatedTerm + " " + idOfLastCreatedTerm + "' WHERE (`id` = '" + idOfLastCreatedTerm + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeDurationBy(String idOfSelectedTerm, int newDuration) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `term` SET `term_duration` = '" + newDuration + "' WHERE (`id` = '" + idOfSelectedTerm + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateDisciplinesInTermBy(String idOfTermToModify, String idOfSelectedDiscipline) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + idOfTermToModify + "', '" + idOfSelectedDiscipline + "')");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearAllDisciplinesInTermBy(String idOfTerm) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE td FROM term_discipline AS td\n" +
                    "JOIN discipline AS d ON td.id_discipline = d.id\n" +
                    "WHERE td.id_term = '"+idOfTerm+"' AND d.status = '1';");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTermBy(String idTerm) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `students_41`.`term` SET `status` = '0' WHERE (`id` = '"+idTerm+"');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteTermInTermDisciplineTableBy(String idTermToDelete) {
        try {
            Class.forName(driverName);
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE td FROM term_discipline AS td WHERE td.id_term = '"+idTermToDelete+"' ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
