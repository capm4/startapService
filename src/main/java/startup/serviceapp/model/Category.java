package startup.serviceapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represent Category.
 *
 * @author Alexander Kruglov
 * @Version 1.0
 */

@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name = "category_id", unique = true, nullable = false)
	private long id;

	@Column(name = "category_name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "category")
	private Set<Startup> startups = new HashSet<>();

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

	public Set<Startup> getStartups() {
		return startups;
	}

	public void setStartups(Set<Startup> startups) {
		this.startups = startups;
	}

	public Category(long id, String name, Set<Startup> startups) {
		this.id = id;
		this.name = name;
		this.startups = startups;
	}

	public Category(String name) {
		this.name = name;
	}

	public Category() {
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", \n ame='" + name + '\'' +
				'}';
	}
}

