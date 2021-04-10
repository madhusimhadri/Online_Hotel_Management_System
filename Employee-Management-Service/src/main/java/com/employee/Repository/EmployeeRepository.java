package com.employee.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.Model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	
	List<Optional<Employee>> findByName(String name);
	List<Optional<Employee>> findByRole(String role);
}
