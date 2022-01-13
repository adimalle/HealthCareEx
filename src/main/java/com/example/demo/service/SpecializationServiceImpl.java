package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Specialization;
import com.example.demo.exception.SpecializationNotFoundException;
import com.example.demo.repo.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements ISpecializationService{

	@Autowired
	private SpecializationRepository repo;
	@Override
	public Long saveSpecialization(Specialization spec) {
	
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {


		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.delete(getOneSpecialization(id));;
		
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
//		Optional<Specialization> opt= repo.findById(id);
//		if(opt.isPresent()) {
//			return opt.get();
//		} else
//		throw new SpecializationNotFoundException(id+"Not Fouond");
	   return repo.findById(id).orElseThrow(()->new SpecializationNotFoundException(id+"not found"));
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
		
	}

	@Override
	public boolean isSpecCodeExit(String specCode) {
//		Integer count=repo.getSpecCodeCount(specCode);
//		if(count>0)
//			return true;
//		else
//			return false;
		return repo.getSpecCodeCount(specCode)>0;
	}

}
