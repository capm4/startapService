package startup.serviceapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JavaBean domain object that represent Permission.
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Entity
@Table(name = "permission")
public class Permission {
	@Id
	@Column(name = "permission_id", unique = true, nullable = false)
	private long id;

	@Column(name = "permission_name", nullable = false)
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "role_permission",
			joinColumns = @JoinColumn(name = "permission_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roleList = new ArrayList<>();

	public Permission(String name) {
		this.name = name;
	}

	public Permission(long id, String name) {
		this.id = id;
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

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
