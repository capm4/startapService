package startup.serviceapp.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represent a Startup.
 *
 * @author Alexandr Kruglov
 * @version 1.o
 */

@Entity
@Table(name = "startup")
public class Startup {
	@Id
	@Column(name="startup_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "startup_name", nullable =false)
	private String name;

	@Lob
	@Column(name = "picture", columnDefinition = "BLOB")
	private Blob image;

	@Lob
	@Column(name = "attachment", columnDefinition = "BLOB")
	private Blob attachment;

	@Column(name = "current_investment")
	private int current_investment;

	@Column(name = "needed_investment", nullable = false)
	private int needed_investment;

	@Column(name = "description")
	private String description;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private Category category;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "startup")
	private Set<StartupEvaluation> marks = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "user_startup",
			joinColumns = {@JoinColumn(name = "startup_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
	)
	private Set<UserDB> users = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

	public int getCurrent_investment() {
		return current_investment;
	}

	public void setCurrent_investment(int current_investment) {
		this.current_investment = current_investment;
	}

	public int getNeeded_investment() {
		return needed_investment;
	}

	public void setNeeded_investment(int needed_investment) {
		this.needed_investment = needed_investment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<StartupEvaluation> getMarks() {
		return marks;
	}

	public void setMarks(Set<StartupEvaluation> marks) {
		this.marks = marks;
	}

	public Set<UserDB> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDB> users) {
		this.users = users;
	}

	public Startup(String name, int needed_investment, String description, Category category) {
		this.name = name;
		this.needed_investment = needed_investment;
		this.description = description;
		this.category = category;
	}

	public Startup(String name) {
		this.name = name;
	}

	public Startup() {
	}

	public Startup(String name, Blob image, Blob attachment, int currnet_investment, int needed_investment, String description, Double rating, String status, Category category) {
		this.name = name;
		this.image = image;
		this.attachment = attachment;
		this.current_investment = current_investment;
		this.needed_investment = needed_investment;
		this.description = description;
		this.rating = rating;
		this.status = status;
		this.category = category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Startup)) return false;

		Startup startup = (Startup) o;

		if (needed_investment != startup.needed_investment) return false;
		if (name != null ? !name.equals(startup.name) : startup.name != null) return false;
		return category != null ? category.equals(startup.category) : startup.category == null;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + needed_investment;
		result = 31 * result + (category != null ? category.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Startup{" +
				"id=" + id +
				", name='" + name + '\'' +
				", current_investment=" + current_investment +
				", needed_investment=" + needed_investment +
				", description='" + description + '\'' +
				", rating=" + rating +
				", status='" + status + '\'' +
				", category=" + category +
				'}';
	}

}
