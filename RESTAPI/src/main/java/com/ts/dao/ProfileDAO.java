package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rest.dto.Faculty;
import com.rest.dto.Profile;
import com.rest.dto.Review;
import com.rest.dto.Submit;
import com.rest.dto.Tasks;
import com.ts.db.HibernateTemplate;

public class ProfileDAO {
	private SessionFactory factory = null;

	private static SessionFactory sessionFactory;
		
		static {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	
	public int register(Profile profile) {		
		return HibernateTemplate.addObject(profile);
	}
	
	public int update(Profile profile) {		
		int result = HibernateTemplate.updateObject(profile);
		return  result;
	}
	
	public Profile getAllProfiles(int facultyId) {
		String query= "from Profile where facultyId=:facultyId";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("facultyId",facultyId);
		 System.out.println(facultyId);
		 Object queryResult = query1.uniqueResult();
		  Profile obj = (Profile)queryResult;
		return obj;
		
	}
}