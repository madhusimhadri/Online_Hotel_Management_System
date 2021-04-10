package com.employee.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.employee.Model.Employee;
import com.employee.Model.Show;
import com.employee.Repository.EmployeeRepository;
import com.employee.Repository.SequenceRepository;
import com.employee.Service.Util.Password;

@Service
public class EmployeeService {
	
	//Sequence Id in Mongo Database
	@Value("${sequence.key}")
	private String counterKey;
	
	//Prefix for employeeId
	@Value("${employee.prefix}")
	private String employeePrefix;
	
	@Autowired
	Password ePassword;
	
	@Autowired
	SequenceRepository sequenceRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	
	
	// Adding a new Employee
	public Show addEmployee(Employee employee)
	{	
		// Count of existing employees
		long seqId = sequenceRepository.getNextSequenceId(counterKey);
		employee.setId(employeePrefix+seqId);
		
		String encryptPassword = ePassword.getEncryptPassword(employee.getPassword());
		employee.setPassword(encryptPassword);
		employeeRepository.save(employee);
		
		return new Show(employee.getId(), "Success!, Employee Added");
	}
	
	// Get All Employees
	public List<Employee> getAllEmployees()
	{
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}
	
	// Getting Employee By Id
	public Employee getEmployeeById(String id)
	{
		try
		{
			Optional<Employee> employee = employeeRepository.findById(id);
			return employee.get();
		}
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	// Get Employee By Name
	// There can be more than one employee with same name. So we get a list 
	public List<Employee> getEmployeeByName(String name)
	{
		List<Optional<Employee>> employee = employeeRepository.findByName(name);
		List<Employee> employees = employee.stream().map(e->e.get()).collect(Collectors.toList());
		return employees;
	}
	
	//Get Employee By Role
	public List<Employee> getEmployeeByRole(String role)
	{
		role = role.toUpperCase();
		List<Optional<Employee>> employee = employeeRepository.findByRole(role);
		List<Employee> employees = employee.stream().map(e->e.get()).collect(Collectors.toList());
		return employees;
	}
	
	// Update Employee
	public Show updateEmployee(Employee employee)
	{
		String encryptPassword = ePassword.getEncryptPassword(employee.getPassword());
		employee.setPassword(encryptPassword);
		employeeRepository.save(employee);
		
		return new Show(employee.getId(),"Success!, Employee Details Updated");
	}
	
	// Delete Employee
	public Show deleteEmployee(String id)
	{
		employeeRepository.deleteById(id);
		return new Show(id,"Success!, Employee Deleted");
	}
	
	
}
