package com.facebookapp.service.privateApis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.facebookapp.model.AddEmployee;
import com.facebookapp.repository.privateApis.AddEmployeeRepo;

@Service
public class AddEmployeeService {
	
	@Autowired
	private AddEmployeeRepo  addEmployeeRepo; 

	//working code
//	public void create(AddEmployee addEmployee) {
//		empManagementSystemRepo.save(addEmployee);		
//	}
	
	
//Add Employee Records
	public AddEmployee addEmployee(AddEmployee addEmployee) {
	AddEmployee employee  = new AddEmployee(); 
	employee.setId(addEmployee.getId());
	employee.setName(addEmployee.getName());
	employee.setEmail(addEmployee.getEmail());
	employee.setDesignation(addEmployee.getDesignation());
	employee.setSalary(addEmployee.getSalary());
	employee.setAddress(addEmployee.getAddress());
	return addEmployeeRepo.save(addEmployee);
	}

	
	public List<AddEmployee> addEmployee() {
		return addEmployeeRepo.findAll();
	}

	public Optional<AddEmployee> getSingleEmployee(Integer id) {
		return addEmployeeRepo.findById(id);
//					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist in DB"));
	}
	
	
	public AddEmployee updateAddEmployee(Integer id, AddEmployee request) {
        // Retrieve the AddEmployee entity by ID
        AddEmployee addEmployee = fetchAddEmployee(id);

        // Update the properties of the retrieved entity with the values from the request
        if (addEmployee != null) {
            addEmployee.setName(request.getName());
            addEmployee.setEmail(request.getEmail());
            addEmployee.setSalary(request.getSalary());
            addEmployee.setAddress(request.getAddress());

            // Save the updated entity back to the database
            return addEmployeeRepo.save(addEmployee);
        } else {
            // Handle the case where the entity with the given ID is not found
            throw new IllegalArgumentException("AddEmployee with ID " + id + " not found");
        }
    }

    private AddEmployee fetchAddEmployee(Integer id) {
        // Retrieve the AddEmployee entity by ID
        Optional<AddEmployee> optionalAddEmployee = addEmployeeRepo.findById(id);
        return optionalAddEmployee.orElse(null);
    }

    //delete Employee Records
    public void deleteAddEmployee(Integer id) {
        AddEmployee addEmployee = fetchAddEmployee(id);
        if (addEmployee != null) {
            addEmployeeRepo.delete(addEmployee);
        } else {
            throw new IllegalArgumentException("Employee not found");
        }
    }

//    private AddEmployee fetchAddEmployee1(Integer id) {
//        Optional<AddEmployee> optionalAddEmployee = addEmployeeRepo.findById(id);
//        return optionalAddEmployee.orElse(null);
//    }
    
    
//	public void deleteAddEmployee(Integer id) {
//		AddEmployee addEmployee = fetchAddEmployee(id);
//		addEmployeeRepo.delete(addEmployee);
//		
//	}
	

//	public AddEmployee updateAddEmployee(Integer id, AddEmployee request) {
//		AddEmployee addEmployee = fetchAddEmployee(id);
//		addEmployee.setName(request.getName());
//		addEmployee.setEmail(request.getEmail());
//		addEmployee.setSalary(request.getSalary());
//		addEmployee.setAddress(request.getAddress());
//		return addEmployeeRepo.save(addEmployee);
//	}
//
//	private AddEmployee fetchAddEmployee(Integer id) {
//			return null;
//	}





//	public List<AddEmployee> addEmployee() {
//		return empManagementSystemRepo.findAll();
//	}





	


	

	
	
//
//	public boolean isUserAlreadyExist( String email, String password) {
//		return empManagementSystemRepo.findByEmailAndPassword(email, password);
//
//	}
}
