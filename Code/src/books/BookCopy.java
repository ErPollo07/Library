package books;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * This class represents the book that can be sold.
 * It has the isbn and the isbn of the parent book.
 */
public class BookCopy {
    private String isbn;
    private String parentIsbn;

    public BookCopy(String isbn, String parentIsbn) {
        this.isbn = isbn;
        this.parentIsbn = parentIsbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getParentIsbn() {
        return parentIsbn;
    }

    public void setParentIsbn(String parentIsbn) {
        this.parentIsbn = parentIsbn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BookCopy book) {
            return this.isbn.equals(book.getIsbn());
        }

        return false;
    }

    public String toString(ArrayList<Book> books) {
        Book parentBook = null;

        for (Book book : books) {
            if (book.getIsbn().equals(parentIsbn)) {
                parentBook = book;
            }
        }

        return "Libro copia {" +
                "isbn='" + isbn + '\'' +
                ", Titolo = '" + parentBook.getTitle() + "'" +
                ", Prezzo = " + parentBook.getPrice() + "'" +
                ", Autori = " + parentBook.getAuthors() + "'" +
                ", Categoria = " + parentBook.getCategory() + "'" +
                '}';
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("isbn", isbn);
        obj.put("parentIsbn", parentIsbn);

        return obj;
    }
}
