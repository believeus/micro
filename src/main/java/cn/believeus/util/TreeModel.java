package cn.believeus.util;

import java.io.Serializable;

/**
 * 创建类目对象，封装类目信息；
 * @author momo
 * 2017-11-01
 *
 */
public class TreeModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5153295569202842533L;
	/*树控件数据格式化
	每个节点都具备以下属性：

	id：节点ID，对加载远程数据很重要。
	name：显示节点文本。
	pId：父节点
	open：节点状态，'true' 或 'false'，默认：'false'。
	*/
	
	private int id;
	private String name;
	private Integer pId;
	private boolean open;
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPId() {
		return pId;
	}


	public void setPId(Integer pId) {
		this.pId = pId;
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}
	public TreeModel(){}

	public TreeModel(int id, Integer pId,String name,  boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}


	

}
