package com.project.belt.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.belt.models.Subscription;
import com.project.belt.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	private SubscriptionRepository subscriptionRepository;
	
	public SubscriptionService(SubscriptionRepository subscriptionRepository){
		this.subscriptionRepository = subscriptionRepository;
	}
	
	public void create(Subscription subscription){
		subscriptionRepository.save(subscription);
	}
	
	public void update(Subscription subscription){
		subscriptionRepository.save(subscription);
	}
	
	public void delete(Long id) {
		subscriptionRepository.delete(id);
	}
	
	public Subscription findById(Long id){
		return subscriptionRepository.findById(id);
	}
	
	public List<Subscription> all(){
		return subscriptionRepository.findAll();
	}
	
}