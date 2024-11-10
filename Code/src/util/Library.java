package util;

import books.Book;
import books.BookCopy;
import events.Loan;
import events.Sell;

import java.util.ArrayList;

public class Library {
    private double yield;
    private ArrayList<Sell> sells;
    private ArrayList<Loan> loans;
    private ArrayList<Book> booksForLoan;
    private ArrayList<BookCopy> booksForSell;

    public Library(ArrayList<Book> booksForLoan, ArrayList<BookCopy> booksForSell) {
        this.booksForLoan = booksForLoan;
        this.booksForSell = booksForSell;
    }

    public Book findParentBookByIsbn(String parentIsbn) {
        for (Book book : booksForLoan) {
            if (book.getIsbn().equals(parentIsbn)) {
                return book;
            }
        }

        return null;
    }

    public void addBookForLoan(Book b) {
        this.booksForLoan.add(b);
    }

    public void addBookForSell(BookCopy b) {
        this.booksForSell.add(b);
    }

    public ArrayList<Book> getBooksForLoan() {
        return booksForLoan;
    }

    public ArrayList<BookCopy> getBooksForSell(boolean withoutDuplicates) {
        ArrayList<BookCopy> books = new ArrayList<>();

        BookCopy[] previous = {new BookCopy("123", "123")};

        if (withoutDuplicates) {
            booksForSell.forEach(book -> {
                // Verify if the previous obj is equals to the current one
                if (!previous[0].equals(book)) {
                    books.add(book);
                }
                // Set previous as the current book
                previous[0] = book;
            });

            return books;
        }

        return booksForSell;
    }
}