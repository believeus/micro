package cn.believeus.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ttask extends TbaseEntity {
	private static final long serialVersionUID = 2383130461826433412L;
	private String title;
	private String endTime;
	private String value;
	private Tuser user;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@ManyToOne
	@JoinColumn(name = "fk_userId", referencedColumnName = "id")
	public Tuser getUser() {
		return user;
	}

	public void setUser(Tuser user) {
		this.user = user;
	}
	
}
