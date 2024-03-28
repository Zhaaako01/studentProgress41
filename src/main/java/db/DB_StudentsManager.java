package db;

import entities.Discipline;
import entities.Mark;
import entities.Student;
import entities.Term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static db.Connection.*;

public class DB_StudentsManager {

    public static List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_41?user=root&password=Qwerty123");
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student where status = 1");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("groupe"));
                student.setDate(rs.getDate("date"));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }


    public static void createNewStudent(String surname, String name, String group, String date) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`surname`, `name`, `groupe`, `date`) VALUES ('" + surname + "', '" + name + "', '" + group + "', '" + date + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Student getStudentById(String id) {

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student where status = 1 and id =" + id);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("groupe"));
                student.setDate(rs.getDate("date"));
                return student;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void modifyStudentById(String surname, String name, String group, String date, String id) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `surname` = '" + surname + "', `name` = '" + name + "', `groupe` = '" + group + "', `date` = '" + date + "' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteStudent(String id) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
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
            Connection conn = DriverManager.getConnection(url, user, password);
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

    public static List<Mark> getMarksByStudentAndTerm(String idStud, int idTerm) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT d.id, d.disclipline_name, m.mark FROM mark as m\n" +
                    "left join term_discipline as td on m.id_term_discipline = td.id\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "where m.id_student = '" + idStud + "' and td.id_term = '" + idTerm + "' and d.status = '1'");
            while (rs.next()) {
                Mark mark = new Mark();
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline_name(rs.getString("disclipline_name"));
                mark.setDiscipline(discipline);
                mark.setMark(rs.getInt("mark"));
                marks.add(mark);
            }
            if (marks.size() == 0) {
                rs = stmt.executeQuery("SELECT * FROM term_discipline as td\n" +
                        "left join discipline as d on td.id_discipline = d.id\n" +
                        "where td.id_term = '"+idTerm+"' and d.status = '1'");
                while (rs.next()) {
                    Mark mark = new Mark();
                    Discipline discipline = new Discipline();
                    discipline.setDiscipline_name(rs.getString("disclipline_name"));
                    mark.setDiscipline(discipline);
                    mark.setMark(-1);
                    marks.add(mark);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return marks;
    }
}
