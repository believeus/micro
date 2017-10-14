package cn.believeus.model;

import javax.persistence.Entity;

@Entity
public class Treject extends TbaseEntity {
	private static final long serialVersionUID = -4426922121753696944L;
	private int userId;
	private String username;
	private int eventId;
	private String title;
	private String message;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
