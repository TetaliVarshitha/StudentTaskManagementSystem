package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Faculty;
import com.rest.dto.Student;
import com.ts.db.HibernateTemplate;

public class StudentDAO {
	private SessionFactory factory = null;
	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}


	
	public int register(Student student) {		
		return HibernateTemplate.addObject(student);
	}

	public Student getStudent(int studentId) {
		return (Student)HibernateTemplate.getObject(Student.class,studentId);
	}

	public List<Student> getAllStudents() {
		List<Student> students=(List)HibernateTemplate.getObjectListByQuery("From Student");
		return students;	
	}
    
   public Student getStudentByEmailId(String emailId) {
		
		String query= "from Student where emailId=:emailId";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("emailId",emailId);
		 System.out.println(emailId);
		 //Faculty obj = query1.list();
		 Object queryResult = query1.uniqueResult();
		 Student faculty = (Student)queryResult;
		 return faculty;

	}
	
	public int delete( int studentId) {		
		int result= HibernateTemplate.deleteObject(Student.class,studentId);
		return result;
	}
	public int update( Student student) {		
		int result = HibernateTemplate.updateObject(student);
		return  result;
	}
	public Student login(String loginId,String password) {		
		Student student =(Student) HibernateTemplate.getStudentByUserPass(loginId,password);
		System.out.println(student);
		return student;
	}
}