package com.hotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;


import com.hotel.model.Employee;

public class UserDetailsServiceImpl implements UserDetailsService {

	public String employeeApi = "employee-management-service";

	public String GET_EMPLOYEE_BY_ID = "http://" + employeeApi + "/getEmployeeById/{id}";

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = getEmployeeById(username);
		if(employee == null) {
			throw new UsernameNotFoundException(username+" does not exist");
		}
		return new UserDetailsImpl(employee);
	}
	//get employee By id
	public Employee getEmployeeById(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_EMPLOYEE_BY_ID;

		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Employee> employee = restTemplate.exchange(url,HttpMethod.GET, httpEntity,Employee.class,id);
		return employee.getBody();
	}
}
