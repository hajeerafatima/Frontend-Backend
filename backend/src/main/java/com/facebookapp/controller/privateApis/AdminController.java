package com.facebookapp.controller.privateApis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facebookapp.model.AddEmployee;
import com.facebookapp.service.privateApis.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/dashboard")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admin_count")
    public ResponseEntity<?> getAdminCount() {
        try {
            Integer count = adminService.getAdminCount();
            return ResponseEntity.ok().body(Map.of("Status", true, "Result", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("Status", false, "Error", "Query Error: " + e.getMessage()));
        }
    }
	
	   @GetMapping("/employee_count")
	    public ResponseEntity<?> getEmployeeCount() {
	        Integer employeeCount = adminService.getEmployeeCount();
	        return ResponseEntity.ok().body(employeeCount);
	    }
	
	   @GetMapping("/salary_count")
	    public ResponseEntity<?> getSalaryCount() {
	        BigDecimal totalSalary = adminService.getTotalSalary();
	        return ResponseEntity.ok().body(totalSalary);
	    }
	
	   @GetMapping("/admin_records")
	    public ResponseEntity<?> getAdminRecords() {
	        try {
	            List<AddEmployee> admins = adminService.findAll();
	            return ResponseEntity.ok().body(admins);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Error occurred: " + e.getMessage());
	        }
	           }
	   
	        @GetMapping("/logout")
	        public ResponseEntity<?> logout(HttpServletResponse response) {
	            Cookie cookie = new Cookie("token", null);
	            cookie.setMaxAge(0);
	            cookie.setHttpOnly(true);
	            cookie.setPath("/"); 
	            response.addCookie(cookie);

	            return ResponseEntity.ok().body("{\"Status\": true}");
	     
	    }
  }
