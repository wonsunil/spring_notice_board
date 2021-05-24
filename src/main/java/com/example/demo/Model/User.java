package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="user_info")
public class User {
	@Id
	@Column(length = 255, nullable = false)
	private String id;
	
	@Column(length = 255, nullable = false)
	private String password;
	
	@Column(length = 255, nullable = false)
	private String name;
	
	@Column(length = 255)
	private String profileImage;
	
	@PrePersist
	private void prePersist() {
		if(profileImage == null) profileImage = "/images/profile/basic_profile_img.png";
	};
	
	public String getId() { return this.id; };
	public void setId(String id) { this.id = id; };
	
	public String getPassword() { return this.password; };
	public void setPassword(String password) { this.password = password; };
	
	public String getName() { return this.name; };
	public void setName(String name) { this.name = name; };
	
	public String getProfileImage() { return this.profileImage; };
	public void setProfileImage(String url) { this.profileImage = url; };
	
	@Override
	public String toString() {
		return "User(id : "+this.id+", name : "+this.name+")";
	};
}