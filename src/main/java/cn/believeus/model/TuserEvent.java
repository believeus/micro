package cn.believeus.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class TuserEvent extends TbaseEntity {

	private static final long serialVersionUID = 8304380585138241948L;
	// 用户id
	private int userId;
	// 事件Id
	private int eventId;
	// 观察员
	private String observer;

	private String title;

	private String type;
	private String value;

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

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
