package library;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Loan implements Serializable{

    private User u;
    private Book b;
    private LocalDate startDate;
    private LocalDate dueDate;
    private int renewalCount;

    public Loan(User u, Book b, LocalDate dueDate) {
        this.u = u;
        this.b = b;
        this.startDate = LocalDate.now();
        this.dueDate = dueDate;
        this.renewalCount = 0;
    }

    public User getU() {
        return u;
    }

    public Book getB() {
        return b;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getRenewalCount() {
        return renewalCount;
    }

    public void setRenewalCount(int renewalCount) {
        this.renewalCount = renewalCount;
    }

    public boolean checkDate() {
        boolean limitExceeded = false;
        if (LocalDate.now().isAfter(dueDate)) {
            limitExceeded = true;
        }
        return limitExceeded;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Loan other = (Loan) obj;
        if (!Objects.equals(this.u, other.u)) {
            return false;
        }
        return Objects.equals(this.b, other.b);
    }

}
