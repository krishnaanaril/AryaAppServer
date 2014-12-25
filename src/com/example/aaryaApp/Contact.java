package com.example.aaryaApp;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String chatId;
	private String userName;
	private String userPassword;
	private String regId;
	
	public Contact() {}
	
	public Contact(String chatId, String regId, String userName, String userPassword) {
		this.chatId = chatId;
		this.regId = regId;
		this.userName=userName;
		this.userPassword=userPassword;
	}
	
	public static Contact find(String chatId, EntityManager em) {
		Query q = em.createQuery("select c from Contact c where c.chatId = :chatId");
		q.setParameter("chatId", chatId);
		List<Contact> result = q.getResultList();
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public Long getId() {
		return id;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
