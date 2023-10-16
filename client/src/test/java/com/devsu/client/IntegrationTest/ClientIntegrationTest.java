package com.devsu.client.integrationTest;

import com.devsu.client.ClientApplication;
import com.devsu.client.domain.dto.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(ClientIntegrationTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllClients() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetClientById() {
        Client client = restTemplate.getForObject(getRootUrl() + "/clients/1", Client.class);
        logger.info(client.getName());
        assertNotNull(client);
    }

    @Test
    public void testCreateUser() {
        Client clientDto = new Client();
        clientDto.setName("John");
        clientDto.setStatus(true);
        clientDto.setAge(25);
        clientDto.setPassword("123456");
        clientDto.setAddres("57 street");
        clientDto.setGenre("male");
        clientDto.setIdentification("10204587");
        clientDto.setPhone("1254789");

        ResponseEntity<Client> postResponse = restTemplate.postForEntity(getRootUrl() + "/client", clientDto, Client.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateClient() {
        long id = 1;
        Client client = restTemplate.getForObject(getRootUrl() + "/clients/" + id, Client.class);
        client.setStatus(true);
        client.setAge(28);

        restTemplate.put(getRootUrl() + "/clients/" + id, client);

        Client updatedClient = restTemplate.getForObject(getRootUrl() + "/users/" + id, Client.class);
        assertNotNull(updatedClient);
    }

    @Test
    public void testDeleteClient() {
        long id = 3;
        Client client = restTemplate.getForObject(getRootUrl() + "/clients/" + id, Client.class);
        assertNotNull(client);

        restTemplate.delete(getRootUrl() + "/clients/" + id);

        try {
            client = restTemplate.getForObject(getRootUrl() + "/clients/" + id, Client.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
