package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.input.AddAuthorInput;
import com.example.graphqlserver.dto.output.AddAuthorPayload;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Author> authors() {
        return authorRepository.getAuthors();
    }

    @QueryMapping
    public List<Author> authorsByLastName(@Argument String lastName) {
        return AuthorRepository.getAuthorsByLastName(lastName);
    }

    @QueryMapping
    public List<Author> authorsByFirstName(@Argument String lastName) {
        return AuthorRepository.getAuthorsByFirstName(lastName);
    }

    @QueryMapping
    public  Author authorById(@Argument("id") int id) {
        return authorRepository.getAuthorById(id);
    }

    @MutationMapping
    public AddAuthorPayload addAuthor(@Argument AddAuthorInput input) {
        var author = authorRepository.save(input.firstName(), input.lastName());
        var out = new AddAuthorPayload(author);
        return out;
    }

    @MutationMapping
    public String updateAuthorFirstName(@Argument int id, @Argument String firstName) {
        return AuthorRepository.updateAuthorFirstName(id, firstName);
    }
}
