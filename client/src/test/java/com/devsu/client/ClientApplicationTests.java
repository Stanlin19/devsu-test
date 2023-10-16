package com.devsu.client;

import com.devsu.client.domain.dto.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientApplicationTests {

	@Test
	void validCreateClientTestSuccess() {
		Client clientDto = new Client();
		clientDto.setName("John");
		clientDto.setStatus(true);
		clientDto.setAge(25);
		clientDto.setPassword("123456");
		clientDto.setAddres("57 street");
		clientDto.setGenre("male");
		clientDto.setIdentification("10204587");
		clientDto.setPhone("1254789");

		assertEquals(clientDto.getAge(), 25);
		assertEquals(clientDto.getAddres(), "57 street");
		assertEquals(clientDto.getGenre(), "male");
		assertEquals(clientDto.getPassword(), "123456");
		assertEquals(clientDto.isStatus(), true);
		assertEquals(clientDto.getIdentification(), "10204587");
		assertEquals(clientDto.getPhone(), "1254789");
		assertEquals(clientDto.getName(), "John");
	}

	@Test
	void validAllFieldsClientTestSuccess() {
		Client clientDto = new Client();
		clientDto.setName("John");
		clientDto.setStatus(true);
		clientDto.setAge(25);
		clientDto.setPassword("123456");
		clientDto.setAddres("57 street");
		clientDto.setGenre("male");
		clientDto.setIdentification("10204587");
		clientDto.setPhone("1254789");

		assertNotNull(clientDto.getName());
		assertNotNull(clientDto.getAddres());
		assertNotNull(clientDto.getPhone());
		assertNotNull(clientDto.getPassword());
	}

	@Test
	void validAllFieldsClientTestNotSuccess() {
		Client clientDto = new Client();
		clientDto.setStatus(true);
		clientDto.setAge(25);
		clientDto.setPassword("123456");
		clientDto.setGenre("male");
		clientDto.setIdentification("10204587");
		clientDto.setPhone("1254789");

		assertNull(clientDto.getName());
		assertNotEquals(clientDto.getAge(), 0);
	}

}
