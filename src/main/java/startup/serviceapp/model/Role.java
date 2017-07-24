package startup.serviceapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JavaBean domain object that represent Role.
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "role_name")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "role_permission",
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private List<Permission> permissions = new ArrayList<>();

	@ManyToMany
	private List<UserDB> users = new ArrayList<>();

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<UserDB> getUsers() {
		return users;
	}

	public void setUsers(List<UserDB> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;

		Role role = (Role) o;

		return name != null ? name.equals(role.name) : role.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
