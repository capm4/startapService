package ua.startupserviceapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Simple JavaBean domain object that represents UserStartup.
 *
 * @author Alexander Kruglov
 * @version 1.0
 */
@Entity
@Table(name = "user_startup")
public class UserStartup implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserDB user;

	@Id
	@ManyToOne
	@JoinColumn(name = "startup_id", nullable = false)
	private Startup startup;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "UserStartup{" +
				"user=" + user +
				", startup=" + startup +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
