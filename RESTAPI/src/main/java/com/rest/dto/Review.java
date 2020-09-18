package com.rest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity
public class Review {
	@Id
	@Column (name = "reviewId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int reviewId;
	private String review;
	private int studentId;
	private int taskId;
	//@ManyToOne
	//@JoinColumn(name="studentId")
	//private  Student student;
	
	//@ManyToOne
	//@JoinColumn(name="taskId")
	//private Tasks task;

	public Review() {
		super();
	}

	



	public Review(int reviewId, String review, int studentId, int taskId) {
		super();
		this.reviewId = reviewId;
		this.review = review;
		this.studentId = studentId;
		this.taskId = taskId;
	}

	public int getReviewId() {
		return reviewId;
	}



	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}





	public int getStudentId() {
		return studentId;
	}





	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}





	public int getTaskId() {
		return taskId;
	}





	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	

}