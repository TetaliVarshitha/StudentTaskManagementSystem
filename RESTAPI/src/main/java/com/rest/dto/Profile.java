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
property = "profileId")
@Entity
public class Profile {
	@Id
	@Column (name = "profileId")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	
	private int profileId;
	private int facultyId;
	private String imageUrl;
	
	
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Profile(int profileId, int facultyId, String imageUrl) {
		super();
		this.profileId = profileId;
		this.facultyId = facultyId;
		this.imageUrl = imageUrl;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}