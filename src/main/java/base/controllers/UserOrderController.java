package base.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import base.Application;
import base.models.Product;
import base.models.User;
import base.models.User.Userrole;
import base.repositories.ProductRepository;
import base.repositories.UserRepository;

@Controller
public class UserOrderController {

	// Declare variables
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private Boolean sessionExists = false;

	// Load the products page
	@GetMapping("/products")
	public String productsLoad(Model model, HttpSession session, User user) {
		log.info("products");
		Iterable<Product> products = productRepository.findAll();
		products.forEach(product -> System.out.println(product.toString()));
		model.addAttribute("products", productRepository.findAll());

		// Check if a session exists
		CheckSessionExists(session, user, model);
		return "/products";
	}

	// Load the edit/add product page
	@GetMapping("/editProduct")
	public String editProductsLoad(@RequestParam(name = "id", required = false) Long productId, Model model,
			HttpSession session, Product newProduct, Product product, User user) {
		log.info("edit products");

		// Check if edit or add
		if (productId == null) {
			newProduct.setProductName("");
			newProduct.setProductDescription("");
			newProduct.setPricePerUnit((long) 0);

			productId = productRepository.save(newProduct).getId();
		}

		model.addAttribute("product", productRepository.findById(productId).get());

		// Check if a session exists
		CheckSessionExists(session, user, model);
		return "editProduct";
	}

	// Add or edit a product
	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	public String editProduct(@RequestParam(name = "id", required = true) Long productId,
			@RequestParam(name = "productName", required = true) String productName,
			@RequestParam(name = "productDescription", required = true) String productDescription,
			@RequestParam(name = "pricePerUnit", required = true) Long pricePerUnit, Model model, HttpSession session,
			User user) {

		// Get the new values and save the product
		Product product = productRepository.findById(productId).get();
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setPricePerUnit(pricePerUnit);

		productRepository.save(product);

		// Check if a session exists
		CheckSessionExists(session, user, model);

		model.addAttribute("products", productRepository.findAll());

		return "/products";
	}

	// Delete an existing product
	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam(name = "id", required = true) Long productId, Model model,
			HttpSession session, User user) {
		log.info("deleteProduct");

		productRepository.deleteById(productId);
		Iterable<Product> products = productRepository.findAll();
		products.forEach(product -> System.out.println(product.toString()));

		model.addAttribute("products", productRepository.findAll());

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "/products";
	}

	// Load profile
	@GetMapping("/profile")

	public String loadProfile(Model model, HttpSession session, User user) {

		// Check if a session exists
		CheckSessionExists(session, user, model);
		return "profile";
	}

	// Update a user
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestParam(name = "id", required = true) Long id,
			@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "username", required = true) String username, Model model, HttpSession session,
			User user) {

		// Update the values and save the user
		user = userRepository.findById(id).get();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setUsername(username);

		userRepository.save(user);

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "home";
	}

	// Load the user page
	@GetMapping("/users")
	public String editUsersLoad(Model model, HttpSession session, User user) {
		log.info("users");

		// Check if a session exists
		CheckSessionExists(session, user, model);

		model.addAttribute("users", userRepository.findAll());

		return "users";
	}

	// Load the edit user page
	@GetMapping("/editUser")
	public String editUserLoad(@RequestParam(name = "id", required = true) Long userId, Model model,
			HttpSession session, User user) {
		log.info("edit users");
		user = userRepository.findById(userId).get();
		model.addAttribute("userEdit", user);

		log.info("user" + user.toString());
		if (user.getRole() == Userrole.ADMIN) {
			model.addAttribute("admin", "checked");
			log.info("Admin");
		} else {
			model.addAttribute("admin", "");
			log.info("User");
		}

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "editUser";
	}

	// Edit a user
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@RequestParam(name = "id", required = true) Long userId,
			@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "role", required = false) String role, Model model, HttpSession session, User user) {

		log.info("Save user");

		user = userRepository.findById(userId).get();

		// Check the role of the user
		log.info(role);
		if (role != null) {
			user.setRole(Userrole.ADMIN);
		} else {
			user.setRole(Userrole.USER);
		}

		// Get the new values and save the user
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		log.info(user.toString());

		userRepository.save(user);

		// Check if a session exists
		CheckSessionExists(session, user, model);

		model.addAttribute("users", userRepository.findAll());

		return "/users";
	}

	// Delete an existing user
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(name = "id", required = true) Long userId, Model model, HttpSession session,
			User user) {
		log.info("deleteUser");

		userRepository.deleteById(userId);
		Iterable<User> users = userRepository.findAll();
		users.forEach(userlog -> System.out.println(userlog.toString()));

		model.addAttribute("users", userRepository.findAll());

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "/users";
	}

	public void CheckSessionExists(HttpSession session, User user, Model model) {
		// Check if a session exists
		Long id = (Long) session.getAttribute("id");
		if (id != null) {
			setSessionExists(true);
			// Get the userdata from the session
			Optional<User> userFound = userRepository.findById(id);
			if (userFound.isPresent()) {
				user = userFound.get();
				model.addAttribute("user", user);
			}

			log.info(user.toString());
		} else {
			setSessionExists(false);
		}
	}

	public Boolean getSessionExists() {
		return sessionExists;
	}

	public void setSessionExists(Boolean sessionExists) {
		this.sessionExists = sessionExists;
	}

}