package com.test.dao;

import java.util.List;

import com.test.entity.Playlist;
import com.test.entity.User;

public interface UserDAO {
	
	public List<User> getAllUsers();
	
	public User getUserData(String email);
	
	public boolean addUser (User user);
	
	public void addPlaylistToUser(User user, Playlist playlist);
	
	public void deletePlaylistFromUser(User user, Playlist playlist);
	
}
