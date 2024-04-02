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


//this all are making this private apis 
//package com.facebookapp.controller.privateApis;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.facebookapp.model.AddEmployee;
//import com.facebookapp.service.privateApis.AddEmployeeService;
//
//@RestController
//@EnableAutoConfiguration
//@CrossOrigin
//@RequestMapping("/dashboard")
//public class AddEmployeeController {
//	
//	@Autowired
//	private AddEmployeeService addEmployeeServicer;
//	
////		@PostMapping("/add_employee")
////		public String addEmployee(@RequestBody  AddEmployee addEmployee) {
////			addEmployeeServicer.addEmployee(addEmployee);
////			return "Employee Added in to database ";
////	}		
//
//	
//	 @PostMapping("/add_employee")
//	    public ResponseEntity<String> addEmployee(@RequestBody AddEmployee addEmployee,
//	                                              @RequestHeader(value = "Authorization") String authToken) {
//	        if (isValidToken(authToken)) {
//	            addEmployeeServicer.addEmployee(addEmployee);
//	            return ResponseEntity.ok("Employee Added to the database");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
//	        }
//	    }
//	 
//	 private boolean isValidToken(String authToken) {
//		    List<String> validTokens = Arrays.asList("token", authToken);
//		    return authToken != null && validTokens.contains(authToken);
//		}	
//		
//////		@GetMapping("/manageemployees")
//////		public List<AddEmployee> getEmployee() {
//////			return addEmployeeServicer.addEmployee();
//////		}
////	 
//	 @GetMapping("/manageemployees")
//	    public List<AddEmployee> getEmployee(@RequestHeader("Authorization") String authToken) {
//	        if (isValidToken(authToken)) {
//	            return addEmployeeServicer.getEmployee();
//	        } else {
//	            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access");
//	        }
//	    }
//	     // Method to validate the authorization token (you can replace it with your validation logic)
//	    private boolean isValidToken1(String authToken) {
//	        List<String> validTokens = Arrays.asList("token", authToken);
//	        return authToken != null && !authToken.isEmpty();
//	    }
//	 		        
////	    @GetMapping("/manageemployees/{id}")
////		public Optional<AddEmployee> getSingleEmployee(@PathVariable("id")  Integer employeeId) {
////			return addEmployeeServicer.getSingleEmployee(employeeId);
////		}
//	    
//	    @GetMapping("/manageemployees/{id}")
//	    public ResponseEntity<?> getSingleEmployee(@PathVariable("id") Integer employeeId,
//	                                               @RequestHeader(value = "Authorization") String authToken) {
//	        if (isValidToken(authToken)) {
//	            Optional<AddEmployee> employee = addEmployeeServicer.getSingleEmployee(employeeId);
//	            if (employee.isPresent()) {
//	                return ResponseEntity.ok(employee.get());
//	            } else {
//	                return ResponseEntity.notFound().build();
//	            }
//	        } else {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
//	        }
//	    }	
//	    private boolean isSingleValidToken11(String authToken) {
//	        // Implement your token validation logic here
//	        // You can check if the token is valid, not expired, and matches a stored token, etc.
//	        return authToken != null && authToken.equals(authToken);
//	    }
//	    		
////		@PutMapping("/edit_employee/{id}")
////		public AddEmployee update(@PathVariable("id") Integer id, 
////				@RequestBody AddEmployee request ) {
////			return addEmployeeServicer.updateAddEmployee(id,request);
////		}
//	    
//	    private boolean isValidToken22(String authToken) {
//	        // Implement your token validation logic here
//	        // You can check if the token is valid, not expired, and matches a stored token, etc.
//	        return authToken != null && !authToken.isEmpty();
//	    }
//
//	    @PutMapping("/edit_employee/{id}")
//	    public ResponseEntity<AddEmployee> updateEmployee(
//	            @PathVariable("id") Integer id,
//	            @RequestBody AddEmployee request,
//	            @RequestHeader(value = "Authorization") String authToken) {
//	        
//	        if (!isValidToken(authToken)) {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	        }
//	        
//	        // Proceed with the update operation if the token is valid
//	        AddEmployee updatedEmployee = addEmployeeServicer.updateAddEmployee(id, request);
//	        return ResponseEntity.ok(updatedEmployee);
//	    }
//	    
//
////		@DeleteMapping("/delete_employee/{id}")
////		public void deleteAddEmployee(@PathVariable("id") Integer id) {
////			 addEmployeeServicer.deleteAddEmployee(id);
////		}
//		
//		@DeleteMapping("/delete_employee/{id}")
//		public ResponseEntity<String> deleteAddEmployee(@PathVariable("id") Integer id) {
//		    try {
//		        addEmployeeServicer.deleteAddEmployee(id);
//		        return ResponseEntity.ok("Employee deleted successfully");
//		    } catch (IllegalArgumentException ex) {
//		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee not found");
//		    } catch (Exception ex) {
//		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee");
//		    }
//		}
//
//		
//		
//
//}
