package com.test.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.entity.Playlist;
import com.test.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<User> getAllUsers() {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			List<User> userList = session.createQuery("from User").list();
			
			if(userList != null) {
				return userList;
			}
		}catch(NoResultException e) {
			
		}
		
		return null;
	}

	@Override
	public User getUserData(String email) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			User temp = (User)session.createQuery("from User where email = '"+email+"'").getSingleResult();
			
			if(temp != null) {
				System.out.println("Getting User Data");
				return temp;
			}
			
		}catch(NoResultException e) {
			
		}
		
		System.out.println("User not  in database");
		
		return null;
	}

	@Override
	public boolean addUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		String email = user.getEmail();
		
		//Check if email already in database
		
		try {
			User temp = (User)session.createQuery("from User where email = '"+email+"'").getSingleResult();
			
			if(temp != null) {
				System.out.println("User already in database");
				return false;
			}
			
		}catch(NoResultException e) {
			
		}
		
		//Save user if email not in database
		
		session.save(user);
		
		return true;
	}

	@Override
	public void addPlaylistToUser(String email, Playlist playlist) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User temp = (User)session.createQuery("from User where email = '"+email+"'").getSingleResult();
		
		temp.add(playlist);
		
		session.save(temp);
		

	}

	@Override
	public void deletePlaylistFromUser(String email, Playlist playlist) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User temp = (User)session.createQuery("from User where email = '"+email+"'").getSingleResult();
		
		for(int i = 0; i<temp.getPlaylists().size(); i++) {
			if(temp.getPlaylists().get(i).getLink().equals(playlist.getLink())) {
				temp.getPlaylists().remove(i);
				session.save(temp);
				return;
			}
		}

	}

}
