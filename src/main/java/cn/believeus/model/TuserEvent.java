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
	private String truename;
	private String title;

	private String type;
	private int liveValue;
	private int learnValue;
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

	public int getLiveValue() {
		return liveValue;
	}

	public void setLiveValue(int liveValue) {
		this.liveValue = liveValue;
	}

	public int getLearnValue() {
		return learnValue;
	}

	public void setLearnValue(int learnValue) {
		this.learnValue = learnValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	

}
