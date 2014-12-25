package com.example.aaryaApp;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

import com.example.aaryaApp.Contact;

@Entity
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String userEmail;
	private boolean isVerified;
	
	public Subscription(){}
	
	public Subscription(String userName, String userEmail, boolean isVerified) {
		this.userEmail=userEmail;
		this.userName=userName;
		this.isVerified=isVerified;
	}
	
	public static Subscription find(String userEmail, EntityManager em) {
		Query q = em.createQuery("select c from Subscription c where c.userEmail = :userEmail");
		q.setParameter("userEmail", userEmail);
		List<Subscription> result = q.getResultList();
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public Long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String useremail) {
		this.userEmail = useremail;
	}
	
	public boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
}
