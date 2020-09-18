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
public class Doubts {
	@Id
	@Column (name = "doubtId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int doubtId;
	private String question;
	private String answer;
	private int studentId;
	private int taskId;
	private String date;
	

	/*@ManyToOne
	@JoinColumn(name="studentId")
	private Student student;

	@ManyToOne
	@JoinColumn(name="taskId")
	private Tasks tasks;
	*/
	public Doubts() {
		super();
	}

	
	public Doubts(int doubtId, String question, String answer, int studentId,int taskId, String date) {
		super();
		this.doubtId = doubtId;
		this.question = question;
		this.answer = answer;
		this.studentId = studentId;
		this.taskId = taskId;
		this.date = date;
	}
	

	public int getDoubtId() {
		return doubtId;
	}


	public void setDoubtId(int doubtId) {
		this.doubtId = doubtId;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

/*
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Tasks getTask() {
		return tasks;
	}

	public void setTask(Tasks tasks) {
		this.tasks = tasks;
	}*/
	
	

}