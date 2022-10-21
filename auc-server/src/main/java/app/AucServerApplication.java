package app;

import app.repositories.OffersRepositoryMockImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import app.rest.OffersController;

@SpringBootApplication
public class AucServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AucServerApplication.class, args);
		OffersRepositoryMockImpl offersRepositoryMock = applicationContext.getBean(OffersRepositoryMockImpl.class);
	}

}
