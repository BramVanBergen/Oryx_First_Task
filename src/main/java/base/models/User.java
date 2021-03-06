package base.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	@Email
	private String email;
	private String username;
	private String password;
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<UserOrder> orders;
	private Userrole role;

	public enum Userrole {
		USER, ADMIN
	}

	public User() {
	}

	public User(String firstName, String lastName, String email, String username, String password, Userrole role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return String.format(
				"User[id=%d, firstName='%s', lastName='%s', email='%s',username='%s', password='%s', role='%s']", id,
				firstName, lastName, email, username, password, role);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Userrole getRole() {
		return role;
	}

	public void setRole(Userrole role) {
		this.role = role;
	}
}