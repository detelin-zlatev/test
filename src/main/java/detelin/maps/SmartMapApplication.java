package detelin.maps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SmartMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMapApplication.class, args);
	}
}
