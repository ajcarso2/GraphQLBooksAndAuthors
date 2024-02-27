package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class BookRepository {

    private static ArrayList<Book> dummyBooks = new ArrayList<>();

    static {
        dummyBooks.addAll(Arrays.asList(
                new Book("123456789", "The Road Not Taken", 0),
                new Book("987654321", "To Kill a Mockingbird", 1),
                new Book("456789123", "The Great Gatsby", 2)
        ));
    }

    public List<Book> getBooks() {
        return dummyBooks;
    }

    public Book getBookByISBN(String isbn) {
        for (Book book : dummyBooks) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public static List<String> findBookTitlesByAuthorFirstName(String firstName) {
        List<Author> authors = AuthorRepository.getAuthorsByFirstName(firstName);
        List<String> bookTitles = new ArrayList<>();
        for (Author author : authors) {
            for (Book book : author.getBooks()) {
                bookTitles.add(book.getTitle());
            }
        }

        return bookTitles;
    }

    public static String deleteBookByISBN(String isbn) {
        Iterator<Book> iterator = dummyBooks.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getIsbn().equals(isbn)) {
                iterator.remove();
                return isbn; // Return the ISBN of the deleted book
            }
        }
        return null; // Return null if the book is not found
    }

    public Book save(String isbn, String title, int authorId) {
        Book newBook = new Book(isbn, title, authorId);
        dummyBooks.add(newBook);
        return newBook;
    }

    public static ArrayList<Book> getBooksByAuthorId(int id) {
        ArrayList<Book> bookList = new ArrayList<>();
        for (Book book : dummyBooks) {
            if (book.getAuthorId() == id) {
                bookList.add(book);
            }
        }
        return bookList;
    }

}
