/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.schoolmanage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 学生管理Entity
 * @author 陈南阳
 * @version 2018-05-25
 */
public class Student extends DataEntity<Student> {
	
	private static final long serialVersionUID = 1L;
	private String claszId;		// 班级
	private String cId;		// 学生编号
	private String name;		// 学生姓名
	private String age;		// 年龄
	private Date birthDate;		// 生日
	private String sex;		// 性别
	private Cadre cadre;		// 干部
	
	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}

	@Length(min=1, max=64, message="班级长度必须介于 1 和 64 之间")
	public String getClaszId() {
		return claszId;
	}

	public void setClaszId(String claszId) {
		this.claszId = claszId;
	}
	
	@Length(min=1, max=64, message="学生编号长度必须介于 1 和 64 之间")
	public String getCId() {
		return cId;
	}

	public void setCId(String cId) {
		this.cId = cId;
	}
	
	@Length(min=1, max=255, message="学生姓名长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="年龄长度必须介于 0 和 11 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
//	@Length(min=0, max=64, message="干部长度必须介于 0 和 64 之间")
	public Cadre getCadre() {
		return cadre;
	}

	public void setCadre(Cadre cadre) {
		this.cadre = cadre;
	}
	
}