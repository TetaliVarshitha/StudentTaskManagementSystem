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
property = "studentId")
@Entity
public class Student {
	@Id
	@Column (name = "studentId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int studentId;
	private String studentName;
	private String dateOfBirth;
	private String gender;
	
	private String branch;
	private String emailId;
	private String mobileNumber;
	private String address;
	private String password;
	
	
//	@OneToMany(mappedBy="student",fetch = FetchType.LAZY)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<Doubts> doubtList =new ArrayList<Doubts>();
	
//	@OneToMany(mappedBy="student",fetch = FetchType.LAZY)
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<Review> reviewList =new ArrayList<Review>();
	
	
	public Student() {
		super();
	}


	public Student(int studentId, String studentName, String dateOfBirth, String gender, String branch, String emailId,
			String mobileNumber, String address, String password/* List<Doubts> doubtList*/) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.branch = branch;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.password = password;
		//this.doubtList = doubtList;
		//this.reviewList = reviewList;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getBranch() {
		return branch;
	}


	public void setBranch(String branch) {
		this.branch = branch;
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

/*
	public List<Doubts> getDoubtList() {
		return doubtList;
	}


	public void setDoubtList(List<Doubts> doubtList) {
		this.doubtList = doubtList;
	}*/


	//public List<Review> getReviewList() {
		//return reviewList;
	//}


	//public void setReviewList(List<Review> reviewList) {
		//this.reviewList = reviewList;
	//}
	
}