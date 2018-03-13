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

@Entity
@Table(name="book")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Book implements Serializable{

	private static final long serialVersionUID = 1508370752961063104L;
	
	public String id;
	public String name;
	public String intro;
	public String info;
	
	@Id
	@GeneratedValue(generator="u_assigned")//自定义主键生成策略
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
	
	@Column(name="intro")
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
		return "Book [id=" + id + ", name=" + name + ", intro=" + intro
				+ ", info=" + info + "]";
	}
	

	
}
