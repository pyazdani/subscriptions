package com.project.belt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="subscriptions")
public class Subscription {
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=1)
	private String subscriptionName;
	
	// @Min(0)
	private double price;
	
	private boolean status;
	
	// @Size(min=1, max=31)
	private int due;
	
	
	public boolean isStatus() {
		return status;
	}
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreated(){
		this.createdAt= new Date();
	}
	
	@PreUpdate
	protected void onUpdated(){
		this.updatedAt= new Date();
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_subscriptions", 
		joinColumns = @JoinColumn(name = "subscription_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
		
	private List<User> users;	
	
	public Subscription(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getDue() {
		return due;
	}

	public void setDue(int due) {
		this.due = due;
	}
	

}