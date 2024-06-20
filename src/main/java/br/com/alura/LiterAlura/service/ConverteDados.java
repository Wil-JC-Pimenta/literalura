package br.com.alura.LiterAlura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.alura.LiterAlura.model.DadosLivro;

public class ConverteDados {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DadosLivro obterDados(String json, Class<DadosLivro> dadosLivroClass) {
        try {
            return objectMapper.readValue(json, dadosLivroClass);
        } catch (Exception e) {
            System.out.println("Erro ao converter JSON para objeto: " + e.getMessage());
            return null;
        }
    }
}
