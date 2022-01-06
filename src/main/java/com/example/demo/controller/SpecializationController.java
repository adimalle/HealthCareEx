package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Specialization;
import com.example.demo.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	/**
	 * 1.display all specializations
	 */
	@GetMapping("/all")
	public String viewall(Model model) {
		
		List<Specialization> list= service.getAllSpecializations();
		model.addAttribute("list",list);
		return "Specialization";
		
	}
	
}
