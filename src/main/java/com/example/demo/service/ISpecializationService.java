package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Specialization;

public interface ISpecializationService {

	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializations();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	
	
}
