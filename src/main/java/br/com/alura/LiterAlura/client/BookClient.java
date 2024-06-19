package br.com.alura.LiterAlura.client;

import br.com.alura.LiterAlura.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class BookClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<BookDTO> getBooksByTitle(String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<BookDTO>> response = restTemplate.exchange(
                "https://gutendex.com/books/?search=" + title,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<BookDTO>>() {
                });

        return response.getBody();
    }

}