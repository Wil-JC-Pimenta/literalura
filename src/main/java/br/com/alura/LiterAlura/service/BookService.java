package br.com.alura.LiterAlura.service;


import br.com.alura.LiterAlura.client.BookClient;
import br.com.alura.LiterAlura.dto.BookDTO;
import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.repository.AuthorRepository;
import br.com.alura.LiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookClient bookClient;

    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookDTO> searchBooksFromApi(String title) {
        return bookClient.getBooksByTitle(title);
    }

    public void saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setLanguage(bookDTO.getLanguage());
        book.setYear(bookDTO.getYear());
        bookRepository.save(book);
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsByYear(int year) {
        return authorRepository.findByYear(year);
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
