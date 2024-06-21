package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultado(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> listaAutores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer downloads
) {
    public List<DadosAutor> autorList() {
        return listaAutores;
    }

    public String idioma() {
        return idiomas.isEmpty() ? null : idiomas.get(0);
    }
}
