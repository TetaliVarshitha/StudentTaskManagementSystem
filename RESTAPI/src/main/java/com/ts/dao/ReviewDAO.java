package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SQLQuery;

import com.rest.dto.Review;
import com.rest.dto.Tasks;
import com.ts.db.HibernateTemplate;

public class ReviewDAO {

	private SessionFactory factory = null;
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public int register(Review review) {
		//java.util.Date utilDate = new java.sql.Date(employee.getJoinDate().getTime()); 
		return HibernateTemplate.addObject(review);
	}

	public List<Review> getAllReviews() {
		List<Review> reviews=(List)HibernateTemplate.getObjectListByQuery("From Review");
		System.out.println("Inside All Review ..."+reviews);
		return reviews;	
	}

	public Review getReview(int id) {
		return (Review)HibernateTemplate.getObject(Review.class,id);
	}


	public List<Review> getReviewsByStudentId(int studentId) {
		String query= "from Review where studentId=:studentId";
		Query  query1 = sessionFactory.openSession().createQuery(query);
		 query1.setParameter("studentId",studentId);
		 List <Review> obj = query1.list();
		
		for(Object review: obj){
			Review reviews = (Review)review;
			System.out.println(reviews.getReviewId());
		}
		return obj;
	}

	public void getReviewsByTaskId(int taskId) {
		String query= "from Review where taskId=:taskId";
		Query  query1 = sessionFactory.openSession().createQuery(query);
		 query1.setParameter("tastId",taskId);
		 List <Review> obj = query1.list();
		
		for(Object review: obj){
			Review reviews = (Review)review;
			System.out.println(reviews.getReviewId());
		}
	}
	
	
}