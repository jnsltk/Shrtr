package tk.jnsl.shrtr;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tk.jnsl.shrtr.entity.UrlEntity;
import tk.jnsl.shrtr.repository.UrlRepository;

import java.util.Optional;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ShrtrApplicationTests {

	@Container
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
			.withDatabaseName("test")
			.withUsername("sa")
			.withPassword("sa");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}

	@Autowired
	private UrlRepository urlRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldBeAbleToRetrieveUrl() {
		// arrange
		UrlEntity urlEntity = new UrlEntity("testAlias", "https://example.org/", 0);
		UrlEntity savedEntity = urlRepository.save(urlEntity);

		// act
		Optional<UrlEntity> urlEntityFromDb = urlRepository.findByAlias("testAlias");

		// assert
		Assertions.assertThat(urlEntityFromDb.isPresent()).isEqualTo(true);
		Assertions.assertThat(urlEntityFromDb.get().getUrl()).isEqualTo("https://example.org/");
	}
}
