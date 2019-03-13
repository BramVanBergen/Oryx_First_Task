package base;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import base.models.Product;
import base.models.User;
import base.models.User.Userrole;
import base.repositories.ProductRepository;
import base.repositories.UserRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner createUsers(UserRepository repository) {
		return (args) -> {
			// save some users
			repository.save(new User("Bram", "Van Bergen", "bram@test.be", "bram", "test", Userrole.ADMIN));
			repository.save(new User("Dieter", "Verboven", "dieter@test.be", "dieter", "test", Userrole.USER));
			repository.save(new User("Jens", "Sels", "jens@test.be", "jens", "test", Userrole.USER));
		};
	}

	@Bean
	public CommandLineRunner createProducts(ProductRepository repository) {
		return (args) -> {
			// save some products
			repository.save(new Product("Gibson Les Paul Studio 2019",
					"The Les Paul Studio embodies the essential Les Paul features with enhancements for playability and tonal versatility.",
					(long) 1699.99));
			repository.save(new Product("Fender American Professional Stratocaster",
					"Often copied, but never surpassed, the Stratocaster is arguably the world's most-loved electric guitar.",
					(long) 1725.00));
			repository.save(new Product("Paul Reed Smith SE Custom 24",
					"The SE Custom 24 brings the original PRS design platform to the high-quality, more affordable SE line up of instruments.",
					(long) 899.99));
		};
	}

}