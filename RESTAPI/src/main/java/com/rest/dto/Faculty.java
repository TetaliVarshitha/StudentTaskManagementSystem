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
property = "facultyId")
@Entity
public class Faculty {
	@Id
	@Column (name = "facultyId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int facultyId;
	private String facultyName;
	private String gender;
	private String subject;
	private String emailId;
	private String mobileNumber;
	private String address;
	private String password;
		
	//@OneToMany(mappedBy="faculty",fetch = FetchType.LAZY)
	//@Fetch(value = FetchMode.SUBSELECT)
	
	//private List<Tasks> taskList =new ArrayList<Tasks>();
	
	public Faculty() {
		super();
	}

	public Faculty(int facultyId, String facultyName, String gender, String subject, String emailId,
			String mobileNumber, String address, String password) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.gender = gender;
		this.subject = subject;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.password = password;
		
		//this.taskList = taskList;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
/*	public List<Tasks> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Tasks> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", gender=" + gender + ", subject="
				+ subject + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", password=" + password + ", taskList=" + taskList + "]";
	}
    */
	
	
}