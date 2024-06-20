package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoFalecimento
) {
    public String nomeAutor() {
        return nome;
    }

    public Integer anoDeNascimento() {
        return anoNascimento;
    }

    public Integer anoDeFalecimento() {
        return anoFalecimento;
    }
}
