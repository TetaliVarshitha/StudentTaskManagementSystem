package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "submitId")
@Entity
public class Submit {
	@Id
	@Column (name = "submitId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int submitId;
	private int taskId;
	private int studentId;
	private String subject;
	private String file1;
	
	
	public Submit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Submit(int submitId, int taskId, int studentId, String subject, String file1) {
		super();
		this.submitId = submitId;
		this.taskId = taskId;
		this.studentId = studentId;
		this.subject = subject;
		this.file1 = file1;
	}


	public int getSubmitId() {
		return submitId;
	}


	public void setSubmitId(int submitId) {
		this.submitId = submitId;
	}


	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getFile1() {
		return file1;
	}


	public void setFile1(String file1) {
		this.file1 = file1;
	}
	
}
	
	