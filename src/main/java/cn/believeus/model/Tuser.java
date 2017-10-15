package cn.believeus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.CallbackException;
import org.hibernate.Session;

@Entity
@Table
public class Tuser extends TbaseEntity {

	private static final long serialVersionUID = -7459216849514123828L;
	/** 联系方式 */
	private String contact;
	private String description;
	/** 注册密码 */
	private String password;
	/** 籍贯 */
	private String provice;
	private Trole role;
	private String sex;
	private List<Ttask> tasklist = new ArrayList<Ttask>();
	/** 紧急联系人 */
	private String urgentContact;

	/** 紧急联系电话 */
	private String urgentPhone;
	/** 注册用户名 */
	private String username;

	/** 用户的当前积分 */
	private Integer value;

	public String getContact() {
		return contact;
	}

	public String getDescription() {
		return description;
	}

	public String getPassword() {
		return password;
	}

	public String getProvice() {
		return provice;
	}

	@OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
	public Trole getRole() {
		return role;
	}

	public String getSex() {
		return sex;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Ttask> getTasklist() {
		return tasklist;
	}

	public String getUrgentContact() {
		return urgentContact;
	}

	public String getUrgentPhone() {
		return urgentPhone;
	}

	public String getUsername() {
		return username;
	}

	public Integer getValue() {
		return value;
	}

	@Override
	public boolean onUpdate(Session s) throws CallbackException {
		return super.onUpdate(s);
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public void setRole(Trole role) {
		this.role = role;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setTasklist(List<Ttask> tasklist) {
		this.tasklist = tasklist;
	}

	public void setUrgentContact(String urgentContact) {
		this.urgentContact = urgentContact;
	}

	public void setUrgentPhone(String urgentPhone) {
		this.urgentPhone = urgentPhone;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
