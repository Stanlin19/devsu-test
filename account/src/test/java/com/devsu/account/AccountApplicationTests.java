package com.devsu.account;

import com.devsu.account.domain.dto.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountApplicationTests {

	@Test
	void validCreateAccountTestSuccess() {
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setAccountType("savings");
		account.setInitBalance(500000L);
		account.setStatus(true);
		account.setClientId(1L);

		assertEquals(account.getAccountNumber(), "123456");
		assertEquals(account.getAccountType(), "savings");
		assertEquals(account.getInitBalance(), 500000L);
		assertEquals(account.getClientId(), 1L);
		assertTrue(account.isStatus());
	}

	@Test
	void validAllFieldsAccountTestSuccess() {
		Account account = new Account();
		account.setAccountNumber("123456");
		account.setAccountType("savings");
		account.setInitBalance(500000L);
		account.setStatus(true);
		account.setClientId(1L);

		assertNotNull(account.getAccountNumber());
		assertNotNull(account.getAccountType());
		assertNotNull(account.getClientId());
		assertNotNull(account.getInitBalance());
	}

	@Test
	void validAllFieldsAccountTestNotSuccess() {
		Account account = new Account();
		account.setAccountType("savings");
		account.setInitBalance(500000L);
		account.setStatus(true);
		account.setClientId(1L);

		assertNull(account.getAccountNumber());
	}

}
