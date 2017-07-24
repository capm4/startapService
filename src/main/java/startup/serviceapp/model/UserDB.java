package startup.serviceapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Alexandr Kruglov
 * @version 1.0
 */

@Entity
@Table(name="user")
public class UserDB {
	@Id
	@Column(name="user_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="first_name", nullable = false)
	private String firstName;

	@Column(name="last_name", nullable = false)
	private String lastName;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name="country")
	private String country;

	@Column(name="city")
	private String city;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "is_active")
	private int active;

	@OneToMany(mappedBy = "user")
	private Set<StartupEvaluation> marks = new HashSet<>();

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "user_startup",
				joinColumns = {@JoinColumn(name = "user_id")},
				inverseJoinColumns = {@JoinColumn(name = "startup_id")})
	private Set<Startup> startups = new HashSet<>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "user_role",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private List<Role> roles = new ArrayList<>();

	public UserDB() {
	}

	public UserDB(String firstName, String lastName, String email, String login, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.login = login;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public int isActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<StartupEvaluation> getMarks() {
		return marks;
	}

	public void setMarks(Set<StartupEvaluation> marks) {
		this.marks = marks;
	}

	public Set<Startup> getStartups() {
		return startups;
	}

	public void setStartups(Set<Startup> startups) {
		this.startups = startups;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDB{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", country='" + country + '\'' +
				", city='" + city + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", active=" + active +
				'}';
	}
}
