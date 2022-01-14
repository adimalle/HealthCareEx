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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Specialization;
import com.example.demo.exception.SpecializationNotFoundException;
import com.example.demo.service.ISpecializationService;
import com.example.demo.view.SpecializationExcelView;

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
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message","record("+id+") is remove here");
			
		} catch (SpecializationNotFoundException e) {
         e.printStackTrace();
         attributes.addAttribute("message",e.getMessage());
		}
		
		return"redirect:all";
	}
	
	
	/**
	 * 5.fetch data into edit page
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id,Model model,RedirectAttributes attributes)
	{
		String page=null;
		try {
			Specialization spec= service.getOneSpecialization(id);
			model.addAttribute("specialization",spec);
			page="SpecializationEdit";
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		
		return page;
	}
	/**
	 * 6.update form data redirect all
	 */
	@PostMapping("/update")
	public String updatedata(@ModelAttribute Specialization specialization,RedirectAttributes attributes) {
		
		service.saveSpecialization(specialization);
		attributes.addAttribute("message","record("+specialization.getId()+") is created");
		
		return"redirect:all";
	}
	
	/**
	 * 7.read code and check with service
	 * return message back to UI
	 */
	@GetMapping("/checkCode")
    @ResponseBody
	public String validateSpecCode(@RequestParam String code,
			                       @RequestParam Integer id) {
		
		String message="";
		if(id==0 && service.isSpecCodeExit(code)) {
			message=code+" already exist";
		}else if(id!=0 && service.isSpecCodeExitForEdit(code,id))
				{
			message=code+" already exist";
		}
		return message;
	}
    /**
     * 8.export data to excel file 
     */
	@GetMapping("/excel")
	public ModelAndView exporttoexcel() {
		ModelAndView m=new ModelAndView();
		m.setView(new SpecializationExcelView());
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
