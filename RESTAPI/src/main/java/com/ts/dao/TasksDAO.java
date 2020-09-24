package com.ts.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.rest.dto.Student;
import com.rest.dto.Tasks;
import com.ts.db.HibernateTemplate;

public class TasksDAO {

	private SessionFactory factory = null;
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	

	public int register(Tasks tasks) {
		// java.util.Date utilDate = new java.sql.Date(tasks.getJoinDate().getTime()); 
		return HibernateTemplate.addObject(tasks);
	}

	public List<Tasks> getAllTasks() {
		List<Tasks> tasks=(List)HibernateTemplate.getObjectListByQuery("From Tasks");
		System.out.println("Inside All Tasks ..."+tasks);
		return tasks;	
	}
	
	public List<Tasks> getAllTasks1() {
		List<Tasks> tasks=(List)HibernateTemplate.getObjectListByQuery("From Tasks");
		System.out.println("Inside All Tasks ..."+tasks);
		return tasks;	
	}

	public Tasks getTask(int taskId) {
		return (Tasks)HibernateTemplate.getObject(Tasks.class,taskId);
	}

	public  List<Tasks> getTasks(int facultyId) {
		String query= "from Tasks where facultyId=:facultyId";
		Query  query1 =  sessionFactory.openSession().createQuery(query);
		 query1.setParameter("facultyId",facultyId);
		 System.out.println(facultyId);
		 List <Tasks> obj = query1.list();
		System.out.println("get faculty is called...");
		System.out.println("Testing get faculty :" + obj); 
		for(Object tasks: obj){
			Tasks tasks1 = (Tasks)tasks;
			System.out.println(tasks1.getDescription());
		}
		return obj;
	}
	public int delete( int taskId) {		
		int result= HibernateTemplate.deleteObject(Tasks.class,taskId);
		return result;
	}

	
}