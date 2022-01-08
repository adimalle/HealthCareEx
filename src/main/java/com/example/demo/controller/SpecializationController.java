package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Specialization;
import com.example.demo.service.ISpecializationService;

import aj.org.objectweb.asm.Attribute;

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
	public String viewall(Model model,@RequestParam(value = "message",required = false) String message) {
		
		List<Specialization> list= service.getAllSpecializations();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "Specialization";
		}
	/**
	 * 4.delete by id
	 */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id,RedirectAttributes attributes) {
		service.removeSpecialization(id);
		attributes.addAttribute("message","record("+id+") is remove here");
		return"redirect:all";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
