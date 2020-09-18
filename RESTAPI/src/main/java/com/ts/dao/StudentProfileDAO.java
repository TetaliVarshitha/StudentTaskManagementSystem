package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Faculty;
import com.rest.dto.Profile;
import com.rest.dto.Review;
import com.rest.dto.StudentProfile;
import com.rest.dto.Submit;
import com.rest.dto.Tasks;
import com.ts.db.HibernateTemplate;

public class StudentProfileDAO {
	private SessionFactory factory = null;

	private static SessionFactory sessionFactory;
		
		static {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	
	public int register(StudentProfile profile) {		
		return HibernateTemplate.addObject(profile);
	}
	
	public int update(StudentProfile profile) {		
		int result = HibernateTemplate.updateObject(profile);
		return  result;
	}
	
	public StudentProfile getAllProfiles(int studentId) {
		String query= "from StudentProfile where studentId=:studentId";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("studentId",studentId);
		 System.out.println(studentId);
		 Object queryResult = query1.uniqueResult();
		  StudentProfile obj = (StudentProfile)queryResult;
		return obj;
		
	}
}