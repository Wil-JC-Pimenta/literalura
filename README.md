# LiterAlura - Busca de Livros e Autores

## Descrição do Projeto

O projeto LiterAlura foi desenvolvido como parte do programa Oracle Next Education - ONE, em colaboração com a Oracle e a Alura. Esta aplicação Java permite a busca e o registro de livros e autores, utilizando o Spring Boot como framework base e integração com uma API externa para obter informações sobre livros.

## Arquitetura

A arquitetura do projeto segue o padrão MVC (Model-View-Controller), que separa as responsabilidades em diferentes camadas:

- **Model**: Representa as entidades do sistema, como `Livro` e `Autor`.
- **Repository**: Responsável pela interação com o banco de dados, utilizando Spring Data JPA para facilitar as operações de CRUD.
- **Service**: Contém a lógica de negócios e manipulação de dados.

## Princípios de Orientação a Objetos

O projeto aplica princípios de orientação a objetos, como encapsulamento e abstração, para garantir que as classes e métodos sejam coesos e que a lógica de negócios seja separada da lógica de apresentação.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Spring Boot**: Framework que simplifica a configuração e o desenvolvimento de aplicações Java.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados utilizado para armazenar informações sobre livros e autores.

## Funcionalidades

- **Buscar um livro**: Realiza uma busca por um livro específico e armazena suas informações no banco de dados local.
- **Listar livros registrados**: Exibe todos os livros armazenados no banco de dados.
- **Listar autores registrados**: Exibe todos os autores armazenados no banco de dados.
- **Listar autores vivos em um determinado ano**: Permite buscar autores que estavam vivos em um determinado ano.
- **Listar livros em um determinado idioma**: Permite buscar livros em um idioma específico.
- **Obter dados do livro**: Obtém e exibe os dados detalhados de um livro específico.

## Como Usar

1. **Buscar um livro**: Selecione a opção 1 e insira o nome do livro desejado.
2. **Listar livros registrados**: Selecione a opção 2 para visualizar todos os livros registrados.
3. **Listar autores registrados**: Selecione a opção 3 para visualizar todos os autores registrados.
4. **Listar autores vivos em um determinado ano**: Selecione a opção 4 e insira o ano desejado.
5. **Listar livros em um determinado idioma**: Selecione a opção 5 e escolha o idioma desejado.
6. **Obter dados do livro**: Selecione a opção 6 e siga as instruções para buscar e exibir os dados de um livro.

## Observações

O projeto utiliza o padrão MVC para organização e separação de responsabilidades. As informações dos livros e autores são obtidas através de uma API externa e armazenadas localmente no banco de dados.
