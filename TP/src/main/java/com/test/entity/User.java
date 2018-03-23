package com.test.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name="user_table")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="token")
	private String token;
	
	@Column(name="password")
	private String password;
	
	@Column(name="username")
	private String username;
	
	//Orphan removal -> remove playlists when not linked to the user (spring)
	@OneToMany(orphanRemoval=true, fetch = FetchType.EAGER , mappedBy="user", cascade = {CascadeType.ALL})
	@JsonManagedReference//Tell JSON that this is the 'parent' of the user class (of the one to many relation)
	private List<Playlist> playlists;
	
	public User() {
		
	}
	
	//No username, password and playlist
	public User(String first_name, String last_name, String email, String token) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.token = token;
	}
	
	//No username and password
	public User(String first_name, String last_name, String email, String token, List<Playlist> playlists) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.token = token;
		this.playlists = playlists;
	}
	
	//No playlist
	public User(String first_name, String last_name, String email, String token, String password, String username) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.token = token;
		this.password = password;
		this.username = username;
	}
	
	//No token, no playlist
	public User(String first_name, String last_name, String email, String password, String username) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void add(Playlist playlist) {
		
		if(this.playlists == null) {
			this.playlists = new ArrayList<>();
		}	
		
		this.playlists.add(playlist);
		
		playlist.setUser(this);
		
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", token=" + token + "]";
	}	
	
}
