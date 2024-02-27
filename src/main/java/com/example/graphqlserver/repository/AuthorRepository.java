package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class AuthorRepository {
    private static ArrayList<Author> dummyAuthors = new ArrayList<>();

    static {
        Author author1 = new Author(0, "Robert", "Frost", BookRepository.getBooksByAuthorId(0));
        Author author2 = new Author(1, "Martin", "Fowler", BookRepository.getBooksByAuthorId(1));
        Author author3 = new Author(2, "Kevin", "Gary", BookRepository.getBooksByAuthorId(2));

        dummyAuthors.addAll(Arrays.asList(author1, author2, author3));
    }

    public static List<Author> getAuthorsByFirstName(String firstName) {
        return dummyAuthors.stream()
                .filter(author -> author.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }

    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        for (Author author : dummyAuthors) {
            authors.add(author);
        }
        return authors;
    }

    public static List<Author> getAuthorsByLastName(String lastName) {
        return dummyAuthors.stream()
                .filter(author -> author.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public static String updateAuthorFirstName(int id, String newFirstName) {
        for (Author author : dummyAuthors) {
            if (author.getId() == id) {
                String oldFirstName = author.getFirstName();
                author.setFirstName(newFirstName);
                return oldFirstName; // Return the old first name
            }
        }
        return null; // Return null if the author is not found
    }

    public Author getAuthorById(int id) {
        for (Author author : dummyAuthors) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    public Author save(String firstName, String lastName) {
        List<Book> book = new ArrayList<>();
        int nextId = dummyAuthors.isEmpty() ? 0 : dummyAuthors.get(dummyAuthors.size() - 1).getId() + 1;
        Author newAuthor = new Author(nextId, firstName, lastName, book);
        dummyAuthors.add(newAuthor);
        return newAuthor;
    }
}
