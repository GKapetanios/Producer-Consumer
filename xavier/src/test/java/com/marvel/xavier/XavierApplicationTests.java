package com.marvel.xavier;

import com.marvel.xavier.controllers.ProducerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class XavierApplicationTests {
	@Autowired
	ProducerController producerController;

	@Test
	void contextLoads() {
		assertThat(producerController).isNotNull();
	}

}
