package com.facebookapp.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.facebookapp.model.Customer;
import com.facebookapp.repository.CustomerRepository;

@Service
public class ForgotPasswordService {

	@Autowired
	CustomerRepository customerRepository;
	
//	 @Bean
//	   public BCryptPasswordEncoder bCryptPasswordEncoder() {
//	       return new BCryptPasswordEncoder();
//	    }
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 public void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException {
		 Optional<Customer> users = customerRepository.findByEmail(email);
		 if (!users.isEmpty()) {
		        Customer user = users.get(); // Get the first user
		        user.setResetPasswordToken(token);
		        customerRepository.save(user);
		    } else {
		        throw new CustomerNotFoundException("Could not find customer with this email: " + email);
		    }
	    }

	
		
//	public void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException {
//		Customer customer = customerRepository.findFirstByEmail(email);
//		
//		if(customer != null) {
//			customer.setResetPasswordToken(token);
//			customerRepository.save(customer);
//		}else {
//			throw new CustomerNotFoundException("Could not find customer with this email" + email);
//		}
//		}
//	
//		public Customer get(String resetPasswordToken) {
//			return customerRepository.findByResetPasswordToken(resetPasswordToken);
//	}
//		
//		public void updatePassword(Customer customer, String newPassword) {
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String encodePassword = passwordEncoder.encode(newPassword);
//			
//			customer.setPassword(encodePassword);
//			customer.setResetPasswordToken(null);
//			
//			customerRepository.save(customer);
//		}	
//	
}
