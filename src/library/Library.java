package library;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {

    private List<Item> items;
    private List<User> users;
    private List<Loan> loans;

    public Library() {
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<Item> searchByTitle(String title) {
        List<Item> results = new ArrayList<>();
        for (Item i : items) {
            if (i.getTitle().equalsIgnoreCase(title)) {
                results.add(i);
            }
        }
        return results;
    }

    public List<Item> searchBySubject(String subject) {
        List<Item> results = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Book book && book.getSubject().equalsIgnoreCase(subject)) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Item> searchByAuthor(String author) {
        List<Item> results = new ArrayList<>();
        for (Item i : items) {
            if (i instanceof Book book) {
                for (String a : book.getAuthors()) {
                    if (a.equalsIgnoreCase(author)) {
                        results.add(book);
                        break;
                    }
                }
            }
        }
        return results;
    }

    public void addItem(Item item) {
        if (item instanceof Book newBook) {

            for (Item i : items) {
                if (i instanceof Book existingBook && existingBook.getTitle().equalsIgnoreCase(newBook.getTitle())) {;
                    newBook.setIsUnique(false);
                }
            }
        }
        items.add(item);
    }

    public void removeItem(String title) throws NonExistentItemException, InUseException {
        Item itemToRemove = null;

        for (Item item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                if (item.isInUse()) {
                    throw new InUseException();
                }
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove == null) {
            throw new NonExistentItemException();
        }
        items.remove(itemToRemove);
    }

    public void consultItem(String userId, String itemTitle) throws NonExistentItemException, InUseException, NonExistentUserException {
        User user = null;
        Item itemToConsult = null;

        for (User u : users) {
            if (u.getId().equals(userId)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new NonExistentUserException();
        }
        for (Item item : items) {
            if (item.getTitle().equalsIgnoreCase(itemTitle)) {
                itemToConsult = item;
                break;
            }
        }
        if (itemToConsult == null) {
            throw new NonExistentItemException();
        }
        if (itemToConsult.isInUse()) {
            throw new InUseException();
        }
        itemToConsult.setInConsultation(true);
        itemToConsult.setInUse(true);
    }

    public void stopConsulting() {
        for (Item i : items) {
            if (i instanceof Book book && !book.isBorrowed()) {
                book.setInConsultation(false);
                book.setInUse(false);
            } else if (i instanceof Magazine magazine) {
                magazine.setInConsultation(false);
                magazine.setInUse(false);
            }
        }
    }

    public void removeUser(String id) throws NonExistentUserException, PendentLoanException {
        User userToRemove = null;
        for (User user : users) {
            if (user.getId().equals(id)) {
                userToRemove = user;
                break;
            }
        }

        for (Loan loan : loans) {
            if (loan.getU().equals(userToRemove)) {
                throw new PendentLoanException();
            }
        }

        if (userToRemove == null) {
            throw new NonExistentUserException();
        }
        users.remove(userToRemove);
    }

    public void lendBook(String id, String title) throws NonExistentUserException, HasNoRightsException, NonExistentItemException, UniqueBookException, InUseException, TooManyLoansException {

        User user = null;
        Book book = null;

        for (User u : users) {
            if (u.getId().equals(id)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new NonExistentUserException();
        }
        if (!user.isHasRights()) {
            throw new HasNoRightsException();
        }

        for (Item i : items) {
            if (i instanceof Book bk && bk.getTitle().equalsIgnoreCase(title) && !bk.isInUse() && !bk.isUnique()) {
                if (bk.isInConsultation() || bk.isBorrowed()) {
                    throw new InUseException();
                }
                book = bk;
                break;
            }
        }

        if (book == null) {
            for (Item i : items) {
                if (i instanceof Book bk && bk.getTitle().equalsIgnoreCase(title)) {
                    book = bk;
                    break;
                }
            }

        }

        if (book == null) {
            throw new NonExistentItemException();
        }
        if (book.isUnique()) {
            throw new UniqueBookException();
        }
        if (book.isBorrowed() || book.isInConsultation() || book.isInUse()) {
            throw new InUseException();
        }
        if (user.getCurrentLoans() >= user.getMaxLoans()) {
            throw new TooManyLoansException();
        }

        LocalDate dueDate = (user instanceof Student) ? LocalDate.now().plusWeeks(1) : LocalDate.now().plusWeeks(2);

        if (user.getRightsRefundDate() != null) {
            dueDate = (user instanceof Student) ? LocalDate.now().plusDays(4) : LocalDate.now().plusWeeks(1);
        }

        Loan loan = new Loan(user, book, dueDate);
        loans.add(loan);
        book.setIsBorrowed(true);
        book.setInUse(true);
        user.setCurrentLoans(user.getCurrentLoans() + 1);
    }

    public void requestExtension(String id, String title) throws NonExistentLoanException, MaxRenewalsExceededException {
        Loan loan = null;

        for (Loan l : loans) {
            if (l.getU().getId().equals(id) && l.getB().getTitle().equalsIgnoreCase(title)) {
                loan = l;
                break;
            }
        }
        if (loan == null) {
            throw new NonExistentLoanException();
        }
        int renewalLimit = loan.getU() instanceof Student ? 1 : 2;
        if (loan.getRenewalCount() >= renewalLimit) {
            throw new MaxRenewalsExceededException();
        }
        loan.setDueDate(loan.getDueDate().plusWeeks(1));
        loan.setRenewalCount(loan.getRenewalCount() + 1);
    }

    public void returnBook(String userId, String bookTitle) throws NonExistentLoanException {
        Loan loanToReturn = null;

        for (Loan loan : loans) {
            if (loan.getU().getId().equals(userId) && loan.getB().getTitle().equalsIgnoreCase(bookTitle)) {
                loanToReturn = loan;
                break;
            }
        }

        if (loanToReturn == null) {
            throw new NonExistentLoanException();
        }

        loanToReturn.getU().setCurrentLoans(loanToReturn.getU().getCurrentLoans() - 1);
        loanToReturn.getB().setIsBorrowed(false);
        loanToReturn.getB().setInUse(false);
        loans.remove(loanToReturn);

        updateUserStatus();
    }

    public void updateUserStatus() {
        LocalDate currentDate = LocalDate.now();
        for (Loan loan : loans) {
            User user = loan.getU();
            if (loan.checkDate()) {
                user.setHasRights(false);
            }
            if (!user.isHasRights() && user.getCurrentLoans() == 0 && user.getRightsRefundDate() == null) {
                user.setRightsRefundDate(currentDate.plusMonths(3));
            }
            if (user.getRightsRefundDate() != null && currentDate.isAfter(user.getRightsRefundDate())) {
                user.setHasRights(true);
                user.setRightsRefundDate(null);
            }
        }
    }
}
