package com.acceture.app.franquicia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EntityScan("com.delivery.domain")
@EnableJpaRepositories("com.delivery.repository")
class FranquiciaApplicationTests {

	@Test
	void contextLoads() {
	}

}
