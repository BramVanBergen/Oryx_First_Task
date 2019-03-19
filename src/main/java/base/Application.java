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
import base.models.UserOrder;
import base.models.UserOrder.OrderStatus;
import base.repositories.ProductRepository;
import base.repositories.UserOrderRepository;
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
	public CommandLineRunner demo(UserRepository repository, ProductRepository productRepository,
			UserOrderRepository userOrderRepository) {
		return (args) -> {
			// Save some users
			User bram = new User("Bram", "Van Bergen", "bram@test.be", "bram", "test", Userrole.ADMIN);
			User dieter = new User("Dieter", "Verboven", "dieter@test.be", "dieter", "test", Userrole.USER);
			User jens = new User("Jens", "Sels", "jens@test.be", "jens", "test", Userrole.USER);
			repository.save(bram);
			repository.save(dieter);
			repository.save(jens);

			// Save some products
			Product gibson = new Product("Gibson Les Paul Studio 2019",
					"The Les Paul Studio embodies the essential Les Paul features with enhancements for playability and tonal versatility.",
					(long) 1699);
			Product fender = new Product("Fender American Professional Stratocaster",
					"Often copied, but never surpassed, the Stratocaster is arguably the world's most-loved electric guitar.",
					(long) 1725);
			Product prs = new Product("Paul Reed Smith SE Custom 24",
					"The SE Custom 24 brings the original PRS design platform to the high-quality, more affordable SE line up of instruments.",
					(long) 899);
			productRepository.save(gibson);
			productRepository.save(fender);
			productRepository.save(prs);

			// Save some orders
			userOrderRepository.save(new UserOrder(gibson, bram, (long) 2, (long) 3398, OrderStatus.ORDERED));
			userOrderRepository.save(new UserOrder(prs, bram, (long) 5, (long) 4495, OrderStatus.PAYED));

		};
	}
}