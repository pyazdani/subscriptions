package com.project.belt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.belt.models.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository <Subscription, Long>{
	public List<Subscription> findAll();
	public Subscription findById(Long id);
}