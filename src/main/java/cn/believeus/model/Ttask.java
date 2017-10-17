package cn.believeus.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ttask extends TbaseEntity {
	private static final long serialVersionUID = 2383130461826433412L;
	private String title;
	private String begintime;
	private String endtime;
	private int learnValue;
	private int liveValue;
	//发布任务的人
	private Tuser user;
	//接受任务的人
	private Tuser aidUser;
	
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	public int getLearnValue() {
		return learnValue;
	}

	public void setLearnValue(int learnValue) {
		this.learnValue = learnValue;
	}

	public int getLiveValue() {
		return liveValue;
	}

	public void setLiveValue(int liveValue) {
		this.liveValue = liveValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@ManyToOne
	@JoinColumn(name = "fk_aidUserId", referencedColumnName = "id")
	public Tuser getAidUser() {
		return aidUser;
	}

	public void setAidUser(Tuser aidUser) {
		this.aidUser = aidUser;
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
