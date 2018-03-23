package com.test.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dao.UserDAO;
import com.test.entity.Playlist;
import com.test.entity.User;

@Repository
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Transactional
	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	@Transactional
	@Override
	public User getUserData(String email) {
		return userDAO.getUserData(email);
	}
	
	@Transactional
	@Override
	public boolean addUser(User user) {
		return userDAO.addUser(user);
	}
	
	@Transactional
	@Override
	public void addPlaylistToUser(User user, Playlist playlist) {
		userDAO.addPlaylistToUser(user, playlist);
	}
	
	@Transactional
	@Override
	public void deletePlaylistFromUser(User user, Playlist playlist) {
		userDAO.deletePlaylistFromUser(user, playlist);
	}
	
	
}
