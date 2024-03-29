package com.facebookapp.controller.privateApis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.facebookapp.model.AddEmployee;
import com.facebookapp.service.privateApis.AddEmployeeService;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/dashboard")
public class AddEmployeeController {
	
	@Autowired
	private AddEmployeeService addEmployeeServicer;
	
		@PostMapping("/add_employee")
		public String addEmployee(@RequestBody  AddEmployee addEmployee) {
			addEmployeeServicer.addEmployee(addEmployee);
			return "Employee Added in to database ";
	}		
		
		@GetMapping("/manageemployees")
		public List<AddEmployee> getEmployee() {
			return addEmployeeServicer.addEmployee();
		}

		@GetMapping("/manageemployees/{id}")
		public Optional<AddEmployee> getSingleEmployee(@PathVariable("id")  Integer employeeId) {
			return addEmployeeServicer.getSingleEmployee(employeeId);
		}
		
		@PutMapping("/edit_employee/{id}")
		public AddEmployee update(@PathVariable("id") Integer id, 
				@RequestBody AddEmployee request ) {
			return addEmployeeServicer.updateAddEmployee(id,request);
		}

//		@DeleteMapping("/delete_employee/{id}")
//		public void deleteAddEmployee(@PathVariable("id") Integer id) {
//			 addEmployeeServicer.deleteAddEmployee(id);
//		}
		
		@DeleteMapping("/delete_employee/{id}")
		public ResponseEntity<String> deleteAddEmployee(@PathVariable("id") Integer id) {
		    try {
		        addEmployeeServicer.deleteAddEmployee(id);
		        return ResponseEntity.ok("Employee deleted successfully");
		    } catch (IllegalArgumentException ex) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee not found");
		    } catch (Exception ex) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee");
		    }
		}

		
		

}
