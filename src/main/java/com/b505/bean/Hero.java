package com.b505.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;








import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
   


//新建的一个表
@Entity
@Table(name="hero")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Hero implements Serializable{

	//用于控制版本
	private static final long serialVersionUID = 6173321287384987967L;
	private String   id;
	private String name;
	private String info;
	
	@Id
	@GeneratedValue(generator="u_assigned")
	@GenericGenerator(name="u_assigned",strategy="assigned")
	@Column(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="info")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", info=" + info + "]";
	}
	
	
	
	
	
}
