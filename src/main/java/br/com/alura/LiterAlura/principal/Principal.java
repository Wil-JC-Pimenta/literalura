package br.com.alura.LiterAlura.principal;


import br.com.alura.LiterAlura.dto.BookDTO;
import br.com.alura.LiterAlura.model.Author;
import br.com.alura.LiterAlura.model.Book;
import br.com.alura.LiterAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private BookService bookService;

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Buscar livro pelo título: ");
            System.out.println("2. Listar livros registrados: ");
            System.out.println("3. Listar nossos autores: ");
            System.out.println("4. Listar autores em determinado ano: ");
            System.out.println("5. Listar livros em determinado idioma: ");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo(scanner);
                    break;
                case 2:
                    consultarLivros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno(scanner);
                    break;
                case 5:
                    consultarLivrosPorIdioma(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void buscarLivroPeloTitulo(Scanner scanner) {
        System.out.print("Insira o nome do livro que você deseja procurar: ");
        String nomeLivro = scanner.nextLine();

        List<BookDTO> books = bookService.searchBooksFromApi(nomeLivro);

        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título: " + nomeLivro);
        } else {
            books.forEach(book -> System.out.println("Título: " + book.getTitle() + ", Autor: " + book.getAuthor()));
        }
    }


    private void consultarLivros() {
        System.out.println("Livros cadastrados:");
        for (Book book : bookService.listAllBooks()) {
            System.out.println(book);
        }
    }

    private void consultarAutores() {
        System.out.println("Autores cadastrados:");
        for (Author author : bookService.listAllAuthors()) {
            System.out.println(author.getName());
        }
    }

    private void consultarAutoresPorAno(Scanner scanner) {
        System.out.print("Digite o ano para buscar autores: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        List<Author> authors = bookService.listAuthorsByYear(year);
        if (authors.isEmpty()) {
            System.out.println("Nenhum autor encontrado no ano: " + year);
        } else {
            authors.forEach(author -> System.out.println(author.getName()));
        }
    }

    private void consultarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Digite o idioma para buscar livros: ");
        String language = scanner.nextLine();
        List<Book> books = bookService.listBooksByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma: " + language);
        } else {
            books.forEach(book -> System.out.println(book.getTitle()));
        }
    }
}
