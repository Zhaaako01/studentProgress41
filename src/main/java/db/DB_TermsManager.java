package db;

import entities.Discipline;
import entities.Term;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
                    "where td.id_term = '"+selectedTermId+"' and d.status = '1'");
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
            ResultSet rs = stmt.executeQuery("SELECT term_duration FROM term where id= '"+selectedTermId+"' and status = '1'");
            while (rs.next()) {
                selectedTermDuration = rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return selectedTermDuration;
    }
}
