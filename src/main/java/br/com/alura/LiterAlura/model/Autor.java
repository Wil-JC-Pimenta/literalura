package br.com.alura.LiterAlura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAutor;
    private Integer anoNascimento;
    private Integer anoDeFalecimento;

    // Construtor sem argumentos
    public Autor() {
    }

    // Construtor com argumentos
    public Autor(String nomeAutor, Integer anoNascimento, Integer anoDeFalecimento) {
        this.nomeAutor = nomeAutor;
        this.anoNascimento = anoNascimento;
        this.anoDeFalecimento = anoDeFalecimento;
    }

    // Construtor que utiliza DadosAutor
    public Autor(DadosAutor dadosAutor) {
        this.nomeAutor = dadosAutor.nomeAutor();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoDeFalecimento = dadosAutor.anoDeFalecimento();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public void setAnoDeNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    // hashCode e equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
