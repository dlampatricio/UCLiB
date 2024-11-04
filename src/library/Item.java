package library;

import java.io.Serializable;

public abstract class Item implements Serializable{

    protected String title;
    protected int number;
    protected int year;
    protected boolean inConsultation;
    protected boolean inUse;

    public Item(String title, int number, int year) {
        this.title = title;
        this.number = number;
        this.year = year;
        this.inConsultation = false;
        this.inUse = false;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }

    public boolean isInConsultation() {
        return inConsultation;
    }

    public void setInConsultation(boolean inConsultation) {
        this.inConsultation = inConsultation;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
