package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Faculty;
import com.rest.dto.Review;
import com.rest.dto.Tasks;
import com.ts.db.HibernateTemplate;

public class FacultyDAO {
	private SessionFactory factory = null;
	private static SessionFactory sessionFactory;
		
		static {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}

	
	public int register( Faculty faculty) {		
		return HibernateTemplate.addObject(faculty);
	}

	public Faculty getFaculty(int facultyId) {
		
		System.out.println( (Faculty)HibernateTemplate.getObject(Faculty.class,facultyId));
		return (Faculty)HibernateTemplate.getObject(Faculty.class,facultyId);
	}


	public Faculty getFacultyByEmailId(String emailId) {
		
		String query= "from Faculty where emailId=:emailId";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("emailId",emailId);
		 System.out.println(emailId);
		 //Faculty obj = query1.list();
		 Object queryResult = query1.uniqueResult();
		 Faculty faculty = (Faculty)queryResult;
		 return faculty;

	}
	
	public List<Faculty> getAllFaculty() {
		List<Faculty> faculty=(List)HibernateTemplate.getObjectListByQuery("From Faculty");
		for(Object fac: faculty){
			Faculty fac1 = (Faculty)fac;
			System.out.println(fac1);
		}
		return faculty;	
	}
	

	public int delete( int facultyId) {		
		int result= HibernateTemplate.deleteObject(Faculty.class,facultyId);
		return result;
	}
	public int update( Faculty faculty) {		
		int result = HibernateTemplate.updateObject(faculty);
		return  result;
	}
	
	
	public Faculty login(String loginId,String password) {		
		Faculty fac =(Faculty) HibernateTemplate.getFacultyByUserPass(loginId,password);
		System.out.println(fac);
		return fac;
	}
}