package startup.serviceapp.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple JavaBean domain object that represent projectEvaluation.
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Entity
@Table(name = "project_evaluation")
public class ProjectEvaluation implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserDB user;

	@Id
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Startup project;

	@Column(name = "mark")
	private int mark;

	public UserDB getUser() {
		return user;
	}

	public void setUser(UserDB user) {
		this.user = user;
	}

	public Startup getProject() {
		return project;
	}

	public void setProject(Startup project) {
		this.project = project;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "ProjectEvaluation{" +
				"user=" + user +
				", project=" + project +
				", mark=" + mark +
				'}';
	}
}
