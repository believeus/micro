package cn.believeus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table
public class TCategory extends TbaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 696106835030171340L;
	/** 名称 */
	private String name;
	/** 上级分类 */
	private Integer parent;
	
	public TCategory(){}
	
	public TCategory(int id){
		this.id=id;
	}
	
	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 获取上级分类
	 * 
	 * @return 上级分类
	 */
	@Column(nullable = false)
	public Integer getParent() {
		return parent;
	}

	/**
	 * 设置上级分类
	 * 
	 * @param parent
	 *            上级分类
	 */
	public void setParent(Integer parent) {
		this.parent = parent;
	}

	
}
