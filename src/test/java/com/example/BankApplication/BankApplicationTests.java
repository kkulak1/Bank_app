package com.example.BankApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankApplicationTests {

	@Test	// JUnit
	void itShouldCheckNumbers() {
		assertThat(2).isEqualTo(2);
		//given
		//when
		//then
	}
}
