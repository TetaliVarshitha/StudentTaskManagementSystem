package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Faculty;
import com.rest.dto.Review;
import com.rest.dto.Submit;
import com.rest.dto.Tasks;
import com.ts.db.HibernateTemplate;

public class SubmitDAO {
	private SessionFactory factory = null;

	private static SessionFactory sessionFactory;
		
		static {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	
	public int register(Submit submit) {		
		return HibernateTemplate.addObject(submit);
	}
	
	public List<Submit> getAllSubmissions(String subject) {
		String query= "from Submit where subject=:subject";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("subject",subject);
		 System.out.println(subject);
		 List <Submit> obj = query1.list();
				for(Object submit: obj){
			Submit tasks1 = (Submit)submit;
			//System.out.println(tasks1.getDescription());
		}
		return obj;
		/*List<Submit> submit=(List)HibernateTemplate.getObjectListByQuery("From Submit");
		
		for(Object fac: submit){
			Submit fac1 = (Submit)fac;
			System.out.println(fac1);
		}
		return submit;	*/
	}
	
	public Submit getSubmission(int taskId, int studentId) {
		
		String query= "from Submit where taskId=:taskId and studentId=:studentId";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("taskId",taskId);
		 query1.setParameter("studentId",studentId);
		 System.out.println(taskId);
		 //Faculty obj = query1.list();
		 Object queryResult = query1.uniqueResult();
		 Submit submit = (Submit)queryResult;
		 return submit;

	}
	

}