package db;

import entity.Discipline;


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
//            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
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
}
