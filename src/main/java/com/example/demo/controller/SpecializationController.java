package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Specialization;
import com.example.demo.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	/**
	 * 1.show register page
	 */
	@GetMapping("/register")
	public String displayregister() {
		return"SpecializationRegister";
	}
	/**
	 * 2.submit form save data
	 */@PostMapping("/save")
	public String save(@ModelAttribute Specialization specialization,Model model) {
		Long id=service.saveSpecialization(specialization);
		String message="Record("+id+") is created";
		model.addAttribute("message",message);
		return"SpecializationRegister";
	}
	
	/**
	 * 3.display all specializations
	 */
	@GetMapping("/all")
	public String viewall(Model model) {
		
		List<Specialization> list= service.getAllSpecializations();
		model.addAttribute("list",list);
		return "Specialization";
		}
	
	
}
