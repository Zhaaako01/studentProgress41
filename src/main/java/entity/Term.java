package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Term {
    private int id;
    private String termName;
    private String duration;
    private int status;
    private List<Discipline> disciplines = new ArrayList<>();

    public Term() {
    }

    public Term(int id, String termName, String duration, int status) {
        this.id = id;
        this.termName = termName;
        this.duration = duration;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        if (id != term.id) return false;
        if (status != term.status) return false;
        if (!Objects.equals(termName, term.termName)) return false;
        if (!Objects.equals(duration, term.duration)) return false;
        return Objects.equals(disciplines, term.disciplines);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (termName != null ? termName.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (disciplines != null ? disciplines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Term{" +
                "id=" + id +
                ", termName='" + termName + '\'' +
                ", duration='" + duration + '\'' +
                ", status=" + status +
                ", disciplines=" + disciplines +
                '}';
    }
}
