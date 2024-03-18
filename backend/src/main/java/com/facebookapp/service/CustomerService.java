package com.facebookapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.facebookapp.dto.CustomerRegisterDto;
import com.facebookapp.model.Customer;
import com.facebookapp.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService{

	@Autowired
	CustomerRepository customerRepository;
	
	 @Bean
	   public BCryptPasswordEncoder bCryptPasswordEncoder() {
	       return new BCryptPasswordEncoder();
	    }
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public String registerCustomer(CustomerRegisterDto customerRegisterDto) {
		
		Customer customer = new Customer();
		customer.setFirstname(customerRegisterDto.getFirstname());
		customer.setLastname(customerRegisterDto.getLastname());
		customer.setEmail(customerRegisterDto.getEmail());
		customer.setPassword(bCryptPasswordEncoder.encode(customerRegisterDto.getPassword()));
		customer.setConfirmpassword(customerRegisterDto.getConfirmpassword());

		customerRepository.save(customer);

		return "User Created Successfully. Please Login Now.";
	}

//	public String loginCustomer(CustomerLoginDto customerLoginDto) {
//		
//		Customer customer = customerRepository.findByEmailAndPassword(customerLoginDto.getEmail(), customerLoginDto.getPassword());
//					
//		if( customer != null) {
//			return "user Login Is Success. Welcome to home";
//		}else {
//			return "Invalid Credentials. Please Try Again";
//		}
//}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = this.customerRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found with email :"+username));
		return user;
	}
	
	//forget password
//
//	public void updateResetPassword(String token, String email) throws CustomerNotFoundException {
//		Customer customer = customerRepository.findByEmail(email);
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
	
	
}
