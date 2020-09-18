package com.rest.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@XmlRootElement

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "taskId")
@Entity
public class Tasks {
	@Id
	@Column (name = "taskId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int taskId;
	private String description;
	private String uploadDate;
	private String submitDate;
	private String subject;
	private Integer facultyId;
	
	
	//@ManyToOne
	//@JoinColumn(name="facultyId")
	//private Faculty faculty;

	/*@OneToMany(mappedBy="tasks",fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.REMOVE, CascadeType.SAVE_UPDATE })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Doubts> doubtList =new ArrayList<Doubts>();
	*/
	//@OneToMany(mappedBy="task",fetch = FetchType.LAZY)
	//@Fetch(value = FetchMode.SUBSELECT)
	//private List<Review> reviewList =new ArrayList<Review>();
	
	public Tasks() {
		super();
	}
	
	public Tasks(int taskId, String description, String uploadDate, String submitDate, String subject, int facultyId) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.uploadDate = uploadDate;
		this.submitDate = submitDate;
		this.subject = subject;
		this.facultyId = facultyId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String string) {
		this.submitDate = string;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	
	

	/*public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}*/

}