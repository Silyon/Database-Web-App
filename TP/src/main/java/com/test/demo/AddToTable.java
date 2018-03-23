package com.test.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.entity.Playlist;
import com.test.entity.User;

public class AddToTable {
	
	@Autowired
	static private SessionFactory sessionFactory;
	
	public static void main(String [] args) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		User u1 = new User("John","Bobbynson","email@around.dot","awd213adfqaed21cq1");
		
		Playlist p1 = new Playlist("awdawdaw");
		Playlist p2 = new Playlist("adawdawdawd");
		Playlist p3 = new Playlist("adsvvsvawd21");
		
		session.save(u1);
		
		User temp = session.get(User.class, 1);
		
		temp.add(p1);
		temp.add(p2);
		
	    User temp2 = session.get(User.class, 1);
		
	    System.out.println(temp2);
	    
	    session.close();
		
		
	}
	
}
