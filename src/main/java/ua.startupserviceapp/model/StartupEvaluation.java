package ua.startupserviceapp.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple JavaBean domain object that represent StartupEvaluation.
 *
 * @author Alexander Kruglov
 * @version 1.0
 */

@Entity
@Table(name = "startup_evaluation")
public class StartupEvaluation implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserDB user;

	@Id
	@ManyToOne
	@JoinColumn(name = "startup_id", nullable = false)
	private Startup startup;

	@Column(name = "mark")
	private int mark;

	public StartupEvaluation() {
	}

	public StartupEvaluation(UserDB user, Startup startup, int mark) {
		this.user = user;
		this.startup = startup;
		this.mark = mark;
	}

	public UserDB getUser() {
		return user;
	}

	public void setUser(UserDB user) {
		this.user = user;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "StartupEvaluation{" +
				"user=" + user +
				", \n startup=" + startup +
				", \n mark=" + mark +
				'}';
	}
}
