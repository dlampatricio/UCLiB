package library;

public class Student extends User {

    private String specialty;
    private String group;
    private boolean Assistant;

    public Student(String specialty, String group, boolean Assistant, String name, String id, String faculty) {
        super(name, id, faculty, 3);
        this.specialty = specialty;
        this.group = group;
        this.Assistant = Assistant;
    }

    public Student(String id) {
        super(id);
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getGroup() {
        return group;
    }

    public boolean isAssistant() {
        return Assistant;
    }
}
