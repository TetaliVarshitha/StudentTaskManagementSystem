package com.ts.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.rest.dto.Doubts;
import com.rest.dto.Faculty;
import com.rest.dto.Student;
import com.rest.dto.Todo;
import com.ts.db.HibernateTemplate;

public class TodoDAO {

	private SessionFactory factory = null;
private static SessionFactory sessionFactory;
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	

	public int register(Todo todo) {
		// java.util.Date utilDate = new java.sql.Date(tasks.getJoinDate().getTime()); 
		return HibernateTemplate.addObject(todo);
	}
	
	public List<Todo> getTodos(int studentId) {
		String query= "from Todo where studentId=:studentId";
		Query  query1 = sessionFactory.openSession().createQuery(query);
		 query1.setParameter("studentId",studentId);
		 List <Todo> obj = query1.list();
		
		for(Object doubts: obj){
			Todo doubt = (Todo)doubts;
			System.out.println(doubt.getTodoId());
		}
		return obj;
	}
	
public Todo getTodo(int todoId) {
		
		System.out.println( (Todo)HibernateTemplate.getObject(Todo.class,todoId));
		return (Todo)HibernateTemplate.getObject(Todo.class,todoId);
	}

public int delete( int todoId) {		
	int result= HibernateTemplate.deleteObject(Todo.class,todoId);
	return result;
}

	
}