package com.marvel.magneto;

import com.marvel.magneto.services.ConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MagnetoApplicationTests {
	@Autowired
	ConsumerService consumerService;

	@Test
	void contextLoads() {
		assertThat(consumerService).isNotNull();
	}

}
