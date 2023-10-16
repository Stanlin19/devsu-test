package com.devsu.client.IntegrationTest;

/*import com.devsu.client.application.port.IClientService;
import com.devsu.client.domain.dto.Client;
import com.devsu.client.infrastructure.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTests {

    @Autowired
    private IClientService clientService;

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

        clientService.delete(clientDto.getId());
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

        Client savedClient = clientService.save(clientDto);


        clientService.delete(savedClient.getId());
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

        Client client1 = clientService.save(clientDto);

        Client clientDto2 = new Client();
        clientDto2.setName("John");
        clientDto2.setStatus(true);
        clientDto2.setAge(25);
        clientDto2.setPassword("123456");
        clientDto2.setAddres("57 street");
        clientDto2.setGenre("male");
        clientDto2.setIdentification("10204587");
        clientDto2.setPhone("1254789");

        Client client2 = clientService.save(clientDto2);


        clientService.delete(client1.getId());
        clientService.delete(client2.getId());
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

        Client savedClient = clientService.save(clientDto);

        Client clientDtoUpdated = new Client();
        clientDtoUpdated.setName("John");
        clientDtoUpdated.setStatus(true);
        clientDtoUpdated.setAge(25);
        clientDtoUpdated.setPassword("654321");
        clientDtoUpdated.setAddres("57 street");
        clientDtoUpdated.setGenre("male");
        clientDtoUpdated.setIdentification("10204587");
        clientDtoUpdated.setPhone("1254789");


        clientService.delete(savedClient.getId());
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

        Client savedClient = clientService.save(clientDto);


    }
}*/
