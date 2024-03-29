package com.facebookapp.controller.privateApis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.facebookapp.model.EmployeeSkills;
import com.facebookapp.service.privateApis.SkillsService;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/dashboard")
public class SkillsController {
	
	@Autowired
	private SkillsService skillService;
	
	 @PostMapping("/add_skills")
	public String addSkills(@RequestBody EmployeeSkills employeeSkills) {
		 skillService.addSkills(employeeSkills);
			return "Skills Added in to database ";
		}
	 
	 @GetMapping("/get/skills")
	 public List<EmployeeSkills> getSkills() {
		 return skillService.getEmployeeSkills();
	 }
}
