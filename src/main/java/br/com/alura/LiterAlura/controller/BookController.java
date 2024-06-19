package br.com.alura.LiterAlura.controller;



import br.com.alura.LiterAlura.dto.BookDTO;
import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return bookService.searchBooks(title);
    }

    @GetMapping("/search/external")
    public List<BookDTO> searchBooksFromApi(@RequestParam String title) {
        return bookService.searchBooksFromApi(title);
    }

    @GetMapping("/language")
    public List<Book> getBooksByLanguage(@RequestParam String language) {
        return bookService.listBooksByLanguage(language);
    }

    @PostMapping
    public void saveBook(@RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);
    }
}
