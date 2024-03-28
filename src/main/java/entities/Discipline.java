package entities;

import java.util.Objects;

public class Discipline {
    private int id;
    private String discipline_name;
    private int status = 1;

    public Discipline() {
    }

    public Discipline(int id, String discipline_name, int status) {
        this.id = id;
        this.discipline_name = discipline_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline_name() {
        return discipline_name;
    }

    public void setDiscipline_name(String discipline_name) {
        this.discipline_name = discipline_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline that = (Discipline) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        return Objects.equals(discipline_name, that.discipline_name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (discipline_name != null ? discipline_name.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", discipline_name='" + discipline_name + '\'' +
                ", status=" + status +
                '}';
    }
}
