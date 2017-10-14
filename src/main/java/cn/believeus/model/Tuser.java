package cn.believeus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Tuser extends TbaseEntity {

	private static final long serialVersionUID = -7459216849514123828L;
	/** 注册用户名 */
	private String username;
	/** 注册密码 */
	private String password;
	private String sex;
	/** 联系方式 */
	private String contact;
	/** 籍贯 */
	private String provice;
	/** 用户的当前积分 */
	private Integer value;
	/** 紧急联系电话 */
	private String urgentPhone;
	/** 紧急联系人 */
	private String urgentContact;

	private List<Ttask> tasklist=new ArrayList<Ttask>();
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getUrgentPhone() {
		return urgentPhone;
	}

	public void setUrgentPhone(String urgentPhone) {
		this.urgentPhone = urgentPhone;
	}

	public String getUrgentContact() {
		return urgentContact;
	}

	public void setUrgentContact(String urgentContact) {
		this.urgentContact = urgentContact;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Ttask> getTasklist() {
		return tasklist;
	}

	public void setTasklist(List<Ttask> tasklist) {
		this.tasklist = tasklist;
	}
	
}
