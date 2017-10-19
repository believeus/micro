package cn.believeus.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**status=0 为规定职责管理规则,status=1为积分奖励规则*/
@Entity
@Table
public class Tevent extends TbaseEntity {

	private static final long serialVersionUID = -2097455863944057668L;
	private String title;
	private String type;
	private String typeName;
	private int value;
	private String description;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
	
	

}
