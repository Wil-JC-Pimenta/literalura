package br.com.alura.LiterAlura.controller;



import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return bookService.listAllAuthors();
    }

    @GetMapping("/year")
    public List<Author> getAuthorsByYear(@RequestParam int year) {
        return bookService.listAuthorsByYear(year);
    }
}
