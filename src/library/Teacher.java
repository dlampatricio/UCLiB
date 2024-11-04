package library;

public class Teacher extends User {

    private String department;

    public Teacher(String department, String name, String id, String faculty) {
        super(name, id, faculty, 5);
        this.department = department;
    }

    public Teacher(String id) {
        super(id);
    }

    public String getDepartment() {
        return department;
    }
}