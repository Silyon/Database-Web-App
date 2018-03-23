package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.entity.Playlist;
import com.test.entity.User;
import com.test.service.UserService;

@Controller
@RequestMapping("/")
public class RESTController {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserService userService;
	
	//Add user
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addUser(@RequestBody String jstr) {
		
		JSONObject obj = new JSONObject(jstr);
		
		User temp = new User();
		temp.setEmail(obj.getString("email"));
		temp.setFirst_name(obj.getString("first_name"));
		temp.setLast_name(obj.getString("last_name"));
		temp.setPassword(obj.getString("password"));
		temp.setUsername(obj.getString("username"));
		temp.setToken(obj.getString("token"));
		
		boolean check = userService.addUser(temp);
		
		if(check == true) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	//Add playlist to a user ----> Usage assumes user is already in database
	@RequestMapping(value = "/addPlaylistToUser", method = RequestMethod.POST)
	public ResponseEntity<String> addPlaylistToUser(@RequestBody String jstr) {
		
		JSONObject obj = new JSONObject(jstr);
		
		User tempUser = new User ();
		tempUser.setEmail(obj.getString("email"));
		tempUser.setFirst_name(obj.getString("first_name"));
		tempUser.setLast_name(obj.getString("last_name"));
		tempUser.setPassword(obj.getString("password"));
		tempUser.setUsername(obj.getString("username"));
		tempUser.setToken(obj.getString("token"));
		
		Playlist tempPlaylist = new Playlist();
		tempPlaylist.setLink(obj.getString("link"));
		
		userService.addPlaylistToUser(tempUser, tempPlaylist);
		
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
	//Delete playlist from a user ----> Usage assumes user is already in database
	@RequestMapping(value = "/deletePlaylistFromUser", method = RequestMethod.POST)
	public ResponseEntity<String> deletePlaylistFromUser(@RequestBody String jstr){
			
		JSONObject obj = new JSONObject(jstr);
			
		User tempUser = new User ();
		tempUser.setEmail(obj.getString("email"));
		tempUser.setFirst_name(obj.getString("first_name"));
		tempUser.setLast_name(obj.getString("last_name"));
		tempUser.setPassword(obj.getString("password"));
		tempUser.setUsername(obj.getString("username"));
		tempUser.setToken(obj.getString("token"));
			
		Playlist tempPlaylist = new Playlist();
		tempPlaylist.setLink(obj.getString("link"));
			
		userService.deletePlaylistFromUser(tempUser, tempPlaylist);
			
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	//Get user data for a specific user (determined with email - unique in database)
	@RequestMapping(value = "/getUserData", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getUserData(@RequestBody String jstr) {
		
		JSONObject obj = new JSONObject(jstr);
		
		String email = obj.getString("email");
		
		User temp = userService.getUserData(email);
		
		if(temp != null) {
			System.out.println(temp + temp.getPlaylists().toString());
			
			//Convert User to JSON
			
			ObjectMapper om = new ObjectMapper();
			
			try {
				String userJSON = om.writeValueAsString(temp);
				return new ResponseEntity<String>(userJSON,HttpStatus.OK);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getAllUsers(){
		
		List<User> userList = userService.getAllUsers();
		
		if(userList != null) {
			
			//Convert list to JSON
			
			ObjectMapper om = new ObjectMapper();
			
			try {
				String listJSON = om.writeValueAsString(userList);
				return new ResponseEntity<String>(listJSON,HttpStatus.OK);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		
	}
	
}
