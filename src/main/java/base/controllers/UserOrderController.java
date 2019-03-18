package base.controllers;

import java.util.List;

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
import base.models.UserOrder;
import base.models.UserOrder.OrderStatus;
import base.repositories.ProductRepository;
import base.repositories.UserOrderRepository;
import base.repositories.UserRepository;

@Controller
public class UserOrderController {

	// Declare variables
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserOrderRepository userOrderRepository;

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private Boolean sessionExists = false;

	// Load the products page
	@GetMapping("/products")
	public String productsLoad(Model model, HttpSession session, User user) {
		log.info("products");
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

	// Save the order
	@RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
	public String saveOrders(@RequestParam(name = "amount", required = true) Long amount,
			@RequestParam(name = "userId", required = true) Long userId,
			@RequestParam(name = "productId", required = true) Long productId, Model model, HttpSession session,
			UserOrder order, Long totalPrice, Product product, User user) {
		log.info("saveOrder");

		product = productRepository.findById(productId).get();
		user = userRepository.findById(userId).get();
		// Set the order values and save the order
		order.setProductAmount(amount);
		order.setProduct(product);
		order.setUser(user);
		order.setTotalPrice(amount * product.getPricePerUnit());
		order.setOrderStatus(OrderStatus.ORDERED);
		userOrderRepository.save(order);

		System.out.println(userOrderRepository.findAllByUserId(user.getId()).toString());

		// Check if a session exists
		CheckSessionExists(session, user, model);

		model.addAttribute("products", productRepository.findAll());

		productsLoad(model, session, user);
		return "products";
	}

	// Load the orders page
	@GetMapping("/orders")
	public String ordersLoad(Model model, HttpSession session, User user, Product product) {
		log.info("orders");

		// Check if a session exists
		user = CheckSessionExists(session, model);

		System.out.println("user: " + user);

		List<UserOrder> userOrders = (List<UserOrder>) userOrderRepository.findAllByUserId(user.getId());

		model.addAttribute("productDelivered", true);
		userOrders.forEach(order -> {
			if (order.getOrderStatus() != OrderStatus.DELIVERED) {
				model.addAttribute("productDelivered", false);
			} else {
			}
		});

		System.out.println(userOrders);
		model.addAttribute("orders", userOrders);

		return "orders";
	}

	// Pay the order or remove the order
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public String handleOrders(@RequestParam(name = "orderId", required = true) Long orderId,
			@RequestParam(name = "submit", required = false) String action, Model model, HttpSession session, User user,
			UserOrder order, Long totalPrice) {
		log.info("payOrRemoveOrder");

		System.out.println(orderId);
		System.out.println(action);

		if (action == null) {
			// no button has been selected
		} else if (action.equals("Cancel order")) {
			// delete button was pressed
			userOrderRepository.deleteById(orderId);
		} else if (action.equals("Pay order")) {
			// update button was pressed
			order = userOrderRepository.findById(orderId).get();
			order.setOrderStatus(OrderStatus.PAYED);
			userOrderRepository.save(order);
		} else if (action.contentEquals("Cancel payment")) {
			order = userOrderRepository.findById(orderId).get();
			order.setOrderStatus(OrderStatus.ORDERED);
			userOrderRepository.save(order);
		} else if (action.contentEquals("Delivered")) {
			order = userOrderRepository.findById(orderId).get();
			order.setOrderStatus(OrderStatus.DELIVERED);
			userOrderRepository.save(order);
		}

		// Check if a session exists
		user = CheckSessionExists(session, model);

		System.out.println("user: " + user);

		System.out.println(userOrderRepository.findAllByUserId(user.getId()).toString());

		model.addAttribute("orders", userOrderRepository.findAllByUserId(user.getId()));

		return "orders";
	}

	// Load the edit order page
	@GetMapping("/editOrder")
	public String editOrderLoad(@RequestParam(name = "orderId", required = false) Long orderId, Model model,
			HttpSession session, UserOrder userOrder, Product product, User user) {
		log.info("edit orders");

		model.addAttribute("order", userOrderRepository.findById(orderId).get());

		// Check if a session exists
		CheckSessionExists(session, user, model);
		return "editOrder";
	}

	// Edit an order
	@RequestMapping(value = "/editOrder", method = RequestMethod.POST)
	public String editOrder(@RequestParam(name = "orderId", required = true) Long orderId,
			@RequestParam(name = "productId", required = true) Long productId,
			@RequestParam(name = "amount", required = true) Long amount, Model model, HttpSession session, User user,
			UserOrder userOrder) {

		// Get the new value and save the order
		userOrder = userOrderRepository.findById(orderId).get();
		userOrder.setProductAmount(amount);
		long totalPrice = amount * userOrder.getProduct().getPricePerUnit();
		userOrder.setTotalPrice(totalPrice);

		userOrderRepository.save(userOrder);

		// Check if a session exists
		user = CheckSessionExists(session, model);

		System.out.println("user: " + user);

		System.out.println(userOrderRepository.findAllByUserId(user.getId()).toString());

		model.addAttribute("orders", userOrderRepository.findAllByUserId(user.getId()));

		return "orders";
	}

	// Delete an existing order
	@GetMapping("/deleteOrder")
	public String deleteOrder(@RequestParam(name = "orderId", required = true) Long orderId,
			@RequestParam(name = "userId", required = true) Long userId, Model model, HttpSession session, User user) {
		log.info("deleteOrder");

		userOrderRepository.deleteById(orderId);

		model.addAttribute("orders", userOrderRepository.findAllByUserId(userId));

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "/products";
	}

	public User CheckSessionExists(HttpSession session, Model model) {
		User user = new User();
		// Check if a session exists
		Long id = (Long) session.getAttribute("id");
		System.out.println(id);
		if (id != null) {
			setSessionExists(true);
			System.out.println("ID != null");
			// Get the userdata from the session
			user = userRepository.findById(id).get();
			System.out.println(user);
			model.addAttribute("user", user);
			return user;
		} else

		{
			setSessionExists(false);
			return user;
		}

	}

	public User CheckSessionExists(HttpSession session, User user, Model model) {
		// Check if a session exists
		Long id = (Long) session.getAttribute("id");
		System.out.println(id);
		if (id != null) {
			setSessionExists(true);
			System.out.println("ID != null");
			// Get the userdata from the session
			user = userRepository.findById(id).get();
			System.out.println(user);
			model.addAttribute("user", user);

		} else

		{
			setSessionExists(false);
		}
		return user;
	}

	public Boolean getSessionExists() {
		return sessionExists;
	}

	public void setSessionExists(Boolean sessionExists) {
		this.sessionExists = sessionExists;
	}

}