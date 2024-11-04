package library;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class User implements Serializable{

    protected String name;
    protected String id;
    protected String faculty;
    protected int maxLoans;
    protected int currentLoans;
    protected boolean hasRights;
    protected LocalDate rightsRefundDate;

    public User(String name, String id, String faculty, int maxLoans) {
        this.name = name;
        this.id = id;
        this.faculty = faculty;
        this.maxLoans = maxLoans;
        this.currentLoans = 0;
        this.hasRights = true;
        this.rightsRefundDate = null;
    }

    public User(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getMaxLoans() {
        return maxLoans;
    }

    public int getCurrentLoans() {
        return currentLoans;
    }

    public void setCurrentLoans(int currentLoans) {
        this.currentLoans = currentLoans;
    }

    public boolean isHasRights() {
        return hasRights;
    }

    public void setHasRights(boolean hasRights) {
        this.hasRights = hasRights;
    }

    public LocalDate getRightsRefundDate() {
        return rightsRefundDate;
    }

    public void setRightsRefundDate(LocalDate rightsRefundDate) {
        this.rightsRefundDate = rightsRefundDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }
}
