package de.neuefische.neuefischeanimalswebclientaufgabe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class NeuefischeAnimalsWebclientAufgabeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void animalsAufgabe () {
        WebClient getWebClient = WebClient.create(
            "https://eoq2vuf7lltn3qi.m.pipedream.net"
        );

        Animal animal = getWebClient.get()
            .uri("/8")
            .retrieve()
            .toEntity(Animal.class)
            .block()
            .getBody();

        WebClient postWebClient = WebClient.create(
            "https://eokz7vcsigzeiih.m.pipedream.net"
        );

        Message message = postWebClient.post()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(new Message("Ich mag diese Antwort " + animal.getAnimal()))
            .retrieve()
            .toEntity(Message.class)
            .block()
            .getBody();

        System.out.println(message);
    }

}
