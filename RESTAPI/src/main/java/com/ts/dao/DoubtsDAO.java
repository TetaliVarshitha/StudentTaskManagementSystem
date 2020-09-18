package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SQLQuery;

import com.rest.dto.Doubts;
import com.rest.dto.Faculty;
import com.rest.dto.Review;
import com.ts.db.HibernateTemplate;

public class DoubtsDAO {

	private SessionFactory factory = null;
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	

	public int register(Doubts doubts) {
		//java.util.Date utilDate = new java.sql.Date(employee.getJoinDate().getTime()); 
		return HibernateTemplate.addObject(doubts);
	}

	public List<Doubts> getAllDoubts() {
		List<Doubts> doubts=(List)HibernateTemplate.getObjectListByQuery("From Doubts");
		System.out.println("Inside All Doubts ..."+doubts);
		return doubts;	
	}

	public Doubts getDoubt(int id) {
		return (Doubts)HibernateTemplate.getObject(Doubts.class,id);
	}


	public List<Doubts> getDoubtsByStudentId(int studentId) {
		String query= "from Doubts where studentId=:studentId";
		Query  query1 = sessionFactory.openSession().createQuery(query);
		 query1.setParameter("studentId",studentId);
		 List <Doubts> obj = query1.list();
		
		for(Object doubts: obj){
			Doubts doubt = (Doubts)doubts;
			System.out.println(doubt.getDoubtId());
		}
		return obj;
	}

	public List<Doubts> getDoubtsByTaskId(int taskId) {
		String query= "from Doubts where taskId=:taskId";
		Query  query1 = sessionFactory.openSession().createQuery(query);
		 query1.setParameter("taskId",taskId);
		 List <Doubts> obj = query1.list();
		
		for(Object doubts: obj){
			Doubts doubt = (Doubts)doubts;
			System.out.println(doubt.getDoubtId());
		}
		return obj;
	}
	
	public List<Doubts> getReviewedDoubts(String date) {
		String query= "from Doubts where date=:date";
		Query  query1 = sessionFactory.openSession().createQuery(query);
	    query1.setParameter("date",date);
		 List <Doubts> obj = query1.list();
		
		for(Object doubts: obj){
			Doubts doubt = (Doubts)doubts;
			System.out.println(doubt.getDoubtId());
		}
		return obj;
	}
	
	public List<Doubts> getPendingDoubts() {
		String query= "from Doubts where answer IS NULL";
		Query  query1 = sessionFactory.openSession().createQuery(query);
		// query1.setParameter("taskId",taskId);
		 List <Doubts> obj = query1.list();
		
		for(Object doubts: obj){
			Doubts doubt = (Doubts)doubts;
			System.out.println(doubt.getDoubtId());
		}
		return obj;
	}
	public int update(Doubts doubt) {		
		int result = HibernateTemplate.updateObject(doubt);
		return  result;
	}
	
}