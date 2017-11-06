package cn.believeus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table
public class TCourse extends TbaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 696106835030171340L;
	/** 名称 */
	private String name;
	/** 分类 */
	private TCategory category;
	/** 视频总时长    */
	private double vidHour;
	/** 学习时间规划 */
	private String studyTime;
	/** 课程简介 */
	private String introduce;
	
	
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

	@ManyToOne
	public TCategory getCategory() {
		return category;
	}

	public void setCategory(TCategory category) {
		this.category = category;
	}

	@Column(nullable = false)
	public double getVidHour() {
		return vidHour;
	}

	public void setVidHour(double vidHour) {
		this.vidHour = vidHour;
	}

	@Column(nullable = false)
	@Length(max = 20)
	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
