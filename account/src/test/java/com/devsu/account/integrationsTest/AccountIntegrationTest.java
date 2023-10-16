package com.devsu.account.integrationsTest;

import com.devsu.account.AccountApplication;
import com.devsu.account.domain.dto.Account;
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
@SpringBootTest(classes = AccountApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountIntegrationTest.class);

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

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetClientById() {
        Account account = restTemplate.getForObject(getRootUrl() + "/accounts/1", Account.class);
        logger.info(account.getAccountNumber());
        assertNotNull(account);
    }

    @Test
    public void testCreateUser() {
        Account account = new Account();
        account.setAccountNumber("123456");
        account.setAccountType("savings");
        account.setInitBalance(500000L);
        account.setStatus(true);
        account.setClientId(1L);

        ResponseEntity<Account> postResponse = restTemplate.postForEntity(getRootUrl() + "/accounts", account, Account.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateClient() {
        long id = 1;
        Account account = restTemplate.getForObject(getRootUrl() + "/accounts/" + id, Account.class);
        account.setStatus(true);
        account.setAccountType("current");

        restTemplate.put(getRootUrl() + "/accounts/" + id, account);

        Account updatedClient = restTemplate.getForObject(getRootUrl() + "/accounts/" + id, Account.class);
        assertNotNull(updatedClient);
    }

    @Test
    public void testDeleteClient() {
        long id = 3;
        Account account = restTemplate.getForObject(getRootUrl() + "/accounts/" + id, Account.class);
        assertNotNull(account);

        restTemplate.delete(getRootUrl() + "/accounts/" + id);

        try {
            account = restTemplate.getForObject(getRootUrl() + "/accounts/" + id, Account.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
