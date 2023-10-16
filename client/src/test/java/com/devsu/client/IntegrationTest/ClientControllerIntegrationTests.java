package com.devsu.client.IntegrationTest;

import com.devsu.client.application.port.IClientService;
import com.devsu.client.domain.dto.Client;
import com.devsu.client.infrastructure.repository.ClientRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTests {

    @Autowired
    private IClientService clientService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void saveClientTest() throws Exception {
        Client clientDto = new Client();
        clientDto.setName("John");
        clientDto.setStatus(true);
        clientDto.setAge(25);
        clientDto.setPassword("123456");
        clientDto.setAddres("57 street");
        clientDto.setGenre("male");
        clientDto.setIdentification("10204587");
        clientDto.setPhone("1254789");

         EntityExchangeResult<byte[]> result = webTestClient.post().uri("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(clientDto), Client.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.name").isEqualTo(clientDto.getName())
                .jsonPath("$.status").isEqualTo(clientDto.isStatus())
                .jsonPath("$.addres").isEqualTo(clientDto.getAddres())
                .jsonPath("$.phone").isEqualTo(clientDto.getPhone())
                .jsonPath("$.identification").isEqualTo(clientDto.getIdentification())
                .jsonPath("$.password").isEqualTo(clientDto.getPassword())
                .jsonPath("$.age").isEqualTo(clientDto.getAge())
                .jsonPath("$.genre").isEqualTo(clientDto.getGenre())
                .returnResult();

        String s = new String(Objects.requireNonNull(result.getResponseBody()), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Client client = gson.fromJson(s, Client.class);
        clientService.delete(client.getId()).block();
    }

    @Test
    public void getClientTest(){

        Client clientDto = new Client();
        clientDto.setName("John");
        clientDto.setStatus(true);
        clientDto.setAge(25);
        clientDto.setPassword("123456");
        clientDto.setAddres("57 street");
        clientDto.setGenre("male");
        clientDto.setIdentification("10204587");
        clientDto.setPhone("1254789");

        Client savedClient = clientService.save(clientDto).block();

        webTestClient.get().uri("/clients/{id}", Collections.singletonMap("id",savedClient.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.name").isEqualTo(savedClient.getName())
                .jsonPath("$.status").isEqualTo(clientDto.isStatus())
                .jsonPath("$.addres").isEqualTo(clientDto.getAddres())
                .jsonPath("$.phone").isEqualTo(clientDto.getPhone())
                .jsonPath("$.identification").isEqualTo(clientDto.getIdentification())
                .jsonPath("$.password").isEqualTo(clientDto.getPassword())
                .jsonPath("$.age").isEqualTo(clientDto.getAge())
                .jsonPath("$.genre").isEqualTo(clientDto.getGenre());

        clientService.delete(savedClient.getId()).block();
    }

    @Test
    public void getAllClientsTest(){

        Client clientDto = new Client();
        clientDto.setName("John");
        clientDto.setStatus(true);
        clientDto.setAge(25);
        clientDto.setPassword("123456");
        clientDto.setAddres("57 street");
        clientDto.setGenre("male");
        clientDto.setIdentification("10204587");
        clientDto.setPhone("1254789");

        Client client1 = clientService.save(clientDto).block();

        Client clientDto2 = new Client();
        clientDto2.setName("John");
        clientDto2.setStatus(true);
        clientDto2.setAge(25);
        clientDto2.setPassword("123456");
        clientDto2.setAddres("57 street");
        clientDto2.setGenre("male");
        clientDto2.setIdentification("10204587");
        clientDto2.setPhone("1254789");

        Client client2 = clientService.save(clientDto2).block();

        webTestClient.get().uri("/clients")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Client.class)
                .consumeWith(System.out::println);

        clientService.delete(client1.getId()).block();
        clientService.delete(client2.getId()).block();
    }

    @Test
    public void updateClientTest(){

        Client clientDto = new Client();
        clientDto.setName("John");
        clientDto.setStatus(true);
        clientDto.setAge(25);
        clientDto.setPassword("123456");
        clientDto.setAddres("57 street");
        clientDto.setGenre("male");
        clientDto.setIdentification("10204587");
        clientDto.setPhone("1254789");

        Client savedClient = clientService.save(clientDto).block();

        Client clientDtoUpdated = new Client();
        clientDtoUpdated.setName("John");
        clientDtoUpdated.setStatus(true);
        clientDtoUpdated.setAge(25);
        clientDtoUpdated.setPassword("654321");
        clientDtoUpdated.setAddres("57 street");
        clientDtoUpdated.setGenre("male");
        clientDtoUpdated.setIdentification("10204587");
        clientDtoUpdated.setPhone("1254789");

        webTestClient.put().uri("/clients/{id}", Collections.singletonMap("id", savedClient.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(clientDtoUpdated), Client.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.name").isEqualTo(clientDtoUpdated.getName())
                .jsonPath("$.status").isEqualTo(clientDtoUpdated.isStatus())
                .jsonPath("$.addres").isEqualTo(clientDtoUpdated.getAddres())
                .jsonPath("$.phone").isEqualTo(clientDtoUpdated.getPhone())
                .jsonPath("$.identification").isEqualTo(clientDtoUpdated.getIdentification())
                .jsonPath("$.password").isEqualTo(clientDtoUpdated.getPassword())
                .jsonPath("$.age").isEqualTo(clientDtoUpdated.getAge())
                .jsonPath("$.genre").isEqualTo(clientDtoUpdated.getGenre());

        clientService.delete(savedClient.getId()).block();
    }

    @Test
    public void deleteClientTest(){

        Client clientDto = new Client();
        clientDto.setName("John");
        clientDto.setStatus(true);
        clientDto.setAge(25);
        clientDto.setPassword("123456");
        clientDto.setAddres("57 street");
        clientDto.setGenre("male");
        clientDto.setIdentification("10204587");
        clientDto.setPhone("1254789");

        Client savedClient = clientService.save(clientDto).block();

        webTestClient.delete().uri("/clients/{id}", Collections.singletonMap("id", savedClient.getId()))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .consumeWith(System.out::println);

    }
}
