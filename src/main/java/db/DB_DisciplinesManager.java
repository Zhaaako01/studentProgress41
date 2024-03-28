package db;

import entities.Discipline;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db.Connection.*;

public class DB_DisciplinesManager {

    /**
     * Get a list of disciplines (active) with status==1
     */

    public static List<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from discipline where status = '1'");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline_name(rs.getString(2));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return disciplines;
    }

    public static void createNewDiscipline(String disciplineName) {

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `discipline`(`disclipline_name`) VALUES('" + disciplineName + "')");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Discipline getDisciplineById(String id) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from discipline where status = '1' and id =" + id);
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt(1));
                discipline.setDiscipline_name(rs.getString(2));
                return discipline;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void modifyDisciplineByID(String disciplineName, String idDisciplineToModify) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `disclipline_name` = '" + disciplineName + "' WHERE (`id` = '" + idDisciplineToModify + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDiscipline(String disciplinesIDs) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `status` = '0' WHERE (`id` = '" + disciplinesIDs + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
