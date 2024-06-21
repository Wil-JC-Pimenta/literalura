package br.com.alura.LiterAlura.main;

import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.DadosLivro;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConverteDados;
import br.com.alura.LiterAlura.service.RequestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {
    private final RequestAPI requestAPI = new RequestAPI();
    private final Scanner scanner = new Scanner(System.in);
    private final String urlBase = "https://gutendex.com/books/?search=";
    private final ConverteDados converteDados = new ConverteDados();
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private List<Livro> livros;
    private List<Autor> autores;

    @Autowired
    public Main(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        showMenu();
    }

    // Exibir dados no console
    public void showMenu() {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    **************************************************
                        LiterAlura - Busca de Livros e Autores
                    **************************************************

                    Selecione uma das opções:

                    1 - Buscar um livro
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Obter dados do livro
                    0 - Sair
                    """;

            try {
                System.out.println(menu);
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Insira uma opção válida!");
                scanner.next(); // Limpar o buffer
            }

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> consultarLivros();
                case 3 -> consultarAutores();
                case 4 -> consultarAutoresPorAno();
                case 5 -> consultarLivrosPorIdioma();
                case 6 -> verificarDados();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Insira uma opção válida!");
            }
        }
    }

    // Extrai os dados do livro
    private DadosLivro obterDadosLivro() {
        System.out.println("Insira o nome do livro:");
        String busca = scanner.nextLine().toLowerCase().replace(" ", "%20");
        String json = requestAPI.get(urlBase + busca);  // Chama o método get da RequestAPI

        return converteDados.obterDados(json, DadosLivro.class);
    }

    // Busca um livro e guarda informações no banco de dados em suas tabelas correspondentes.
    private void buscarLivro() {
        DadosLivro dadosLivro = obterDadosLivro();

        try {
            // Verifica se o autor já existe no banco de dados
            Optional<Autor> autorExistente = autorRepository.findByNomeAutor(dadosLivro.resultados().get(0).autorList().get(0).nomeAutor());

            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                // Cria um novo autor se não existir
                autor = new Autor(
                        dadosLivro.resultados().get(0).autorList().get(0).nomeAutor(),
                        dadosLivro.resultados().get(0).autorList().get(0).anoNascimento(),
                        dadosLivro.resultados().get(0).autorList().get(0).anoDeFalecimento()
                );
                autorRepository.save(autor); // Salva o novo autor
            }

            // Cria um novo livro associando ao autor (existente ou recém-criado)
            Livro livro = new Livro(
                    dadosLivro.resultados().get(0).titulo(),
                    autor, // Usa o autor encontrado ou criado
                    dadosLivro.resultados().get(0).idioma(), // Aqui usamos o método idioma()
                    dadosLivro.resultados().get(0).downloads()
            );

            System.out.println("""
                Livro[
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Downloads: %s
                ]
                """.formatted(livro.getTitulo(), livro.getAutor().getNomeAutor(), livro.getIdioma(), livro.getDownloads().toString()));

            livroRepository.save(livro); // Salva o livro

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    // Consulta os livros guardados no BD
    private void consultarLivros() {
        livros = livroRepository.findAll();
        livros.forEach(l -> {
            System.out.println("""
                        Título: %s
                        Autor: %s
                        Idioma: %s
                        Downloads: %s
                    """.formatted(l.getTitulo(), l.getAutor().getNomeAutor(), l.getIdioma(), l.getDownloads().toString()));
        });
    }

    // Consulta todos os autores dos livros guardados no BD
    private void consultarAutores() {
        autores = autorRepository.findAll();
        autores.forEach(a -> {
            System.out.println("""
                        Autor: %s
                        Ano de nascimento: %s
                        Ano de falecimento: %s
                    """.formatted(a.getNomeAutor(), a.getAnoNascimento().toString(), a.getAnoDeFalecimento().toString()));
        });
    }

    // Consulta autores a partir de um certo ano
    public void consultarAutoresPorAno() {
        System.out.println("Insira o ano a partir do qual buscar:");
        int anoBusca = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = autorRepository.findByAnoNascimento(anoBusca);
        autores.forEach(a -> {
            System.out.println("""
                    Nome: %s
                    Data de nascimento: %s
                    Data de falecimento: %s
                    """.formatted(a.getNomeAutor(), a.getAnoNascimento().toString(), a.getAnoDeFalecimento().toString()));
        });
    }

    private void consultarLivrosPorIdioma() {
        System.out.println("""
                ****************************************************************
                    Selecione o idioma que deseja consultar:
                ****************************************************************
                1 - Inglês (en)
                2 - Português (pt-br)
                3 - Espanhol (es)
                4-  Francês (fr)
                """);

        try {
            int opcaoIdioma = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoIdioma) {
                case 1 -> livros = livroRepository.findByIdioma("en");
                case 2 -> livros = livroRepository.findByIdioma("pt-br");
                case 3 -> livros = livroRepository.findByIdioma("es");
                case 4 -> livros = livroRepository.findByIdioma("fr");
                default -> System.out.println("Selecione uma opção válida!");
            }

            livros.forEach(l -> {
                System.out.println("""
                        Título: %s
                        Autor: %s
                        Idioma: %s
                        Downloads: %s
                        """.formatted(l.getTitulo(), l.getAutor().getNomeAutor(), l.getIdioma(), l.getDownloads().toString()));
            });

        } catch (Exception e) {
            System.out.println("Insira uma opção válida!");
            scanner.next(); // Limpar o buffer
        }
    }

    // Verificar dados inseridos no banco de dados
    private void verificarDados() {
        List<Livro> livros = livroRepository.findAll();
        System.out.println("Livros no banco de dados:");
        livros.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor().getNomeAutor()));

        List<Autor> autores = autorRepository.findAll();
        System.out.println("Autores no banco de dados:");
        autores.forEach(autor -> System.out.println("Nome: " + autor.getNomeAutor() + ", Ano de Nascimento: " + autor.getAnoNascimento()));
    }
}
