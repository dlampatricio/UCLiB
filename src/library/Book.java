package library;

import java.util.List;
import java.util.Objects;

public class Book extends Item {

    private List<String> authors;
    private String subject;
    private boolean unique;
    private boolean borrowed;

    public Book(List<String> authors, String subject, String title, int number, int year) {
        super(title, number, year);
        this.authors = authors;
        this.subject = subject;
        this.unique = true;
        this.borrowed = false;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setIsUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setIsBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(getTitle());
        hash = 31 * hash + Objects.hashCode(subject);
        hash = 31 * hash + Objects.hashCode(authors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.getTitle(), other.getTitle()) && Objects.equals(this.subject, other.subject) && Objects.equals(this.authors, other.authors);
    }
}
