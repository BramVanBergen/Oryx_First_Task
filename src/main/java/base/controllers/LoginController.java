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
import base.models.User;
import base.models.User.Userrole;
import base.repositories.UserRepository;

@Controller
public class LoginController {

	// Declare variables
	@Autowired
	UserRepository repository;

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private Boolean sessionExists = false;

	// Invalidate the session and set sessionExists to false
	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		setSessionExists(false);
		return "home";
	}

	// Load the login page
	@GetMapping("/login")
	public String loginLoad(Model model, HttpSession session, User user) {
		log.info("login Load");

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "login";
	}

	// Try to log the user in
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password, Model model, HttpSession session) {

		log.info("login");
		for (User user : repository.findAll()) {
			log.info(user.toString());
		}

		// Check if the username is in the database
		User user = repository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			model.addAttribute("user", user);

			// Add the user to the session
			session.setAttribute("id", user.getId());

			return "confirmRegistration";
		}
		return "login";
	}

	// Confirm a succesfull login or registration
	@GetMapping("/confirmRegistration")
	public String confirmRegistration(User user, Model model, HttpSession session) {

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "confirmRegistration";
	}

	// Load the homepage
	@GetMapping("/home")
	public String home(Model model, HttpSession session, User user) {
		log.info("home");

		for (User test : repository.findAll()) {
			log.info(test.toString());
		}

		// Check if a session exists
		CheckSessionExists(session, user, model);

		return "home";
	}

	// Load the homepage
	@GetMapping("/")
	public String Load(Model model, HttpSession session) {
		log.info("/");
		return "home";
	}

	// Load the registration page
	@GetMapping("/registration")
	public String registration(Model model, HttpSession session) {
		log.info("registration Load");
		return "registration";
	}

	// Try to register the new user
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "confirmPassword", required = true) String confirmPassword, Model model,
			HttpSession session, User user) {
		log.info("registration");

		// Check if the username is already taken
		if (username.equals(repository.findByUsername(username).getUsername())) {
			log.info("username already taken");
			model.addAttribute("error", "Username already taken!");
			return "registration";
		}

		// Check if the entered passwords match
		if (!password.equals(confirmPassword)) {
			log.info("password mismatch");
			model.addAttribute("error", "Passwords do not match!");
			return "registration";
		}

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(Userrole.USER);

		// Save the user
		repository.save(user);

		// Create a session and set sessionExists to true
		session.setAttribute("id", user.getId());
		setSessionExists(true);
		log.info(user.getId().toString());

		return "confirmRegistration";
	}

	public void CheckSessionExists(HttpSession session, User user, Model model) {
		// Check if a session exists
		Long id = (Long) session.getAttribute("id");
		if (id != null) {
			setSessionExists(true);
			// Get the userdata from the session
			Optional<User> userFound = repository.findById(id);
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