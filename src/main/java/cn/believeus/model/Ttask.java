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
	private String type;
	private Integer value;
	//发布任务的人
	private Tuser user;
	//接受任务的人
	private Tuser aidUser;
	
	private String message;

	public Ttask() {
		
	}
	public Ttask(Tuser user,String type,int value,Tuser aidUser,String status){
		this.user=user;
		this.type=type;
		this.aidUser=aidUser;
		this.value=value;
		this.status=status;
	}
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
