package com.hotel.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.Model.Employee;
import com.hotel.Model.Room;
import com.hotel.Model.RoomDetails;
import com.hotel.Model.Show;

@Service
public class ManagerService {

	@Autowired
	RestTemplate restTemplate;

	public String roomApi = "room-management-service";

	// ************************* API Calls To Room Management Service ********************* //
	
	public String ADD_ROOM = "http://" + roomApi + "/addRoom";
	public String ADD_MULTIPLE_ROOMS = "http://" + roomApi + "/addMultiple/{type}/{count}";
	public String GET_ROOM_BY_ID = "http://" + roomApi + "/getRoom/{id}";
	public String GET_ROOM_BY_TYPE = "http://" + roomApi + "/getAllRoomsByType/{type}";
	public String GET_ROOM_BY_STATUS = "http://" + roomApi + "/getAllRoomsByStatus/{status}";
	public String GET_ALL_AVAILABLE_ROOMS = "http://" + roomApi + "/getAllAvailableRooms";
	public String GET_ALL_BOOKED_ROOMS = "http://" + roomApi + "/getAllBookedRooms";
	public String UPDATE_ROOM = "http://" + roomApi + "/updateRoom";
	public String DELETE_ROOM = "http://" + roomApi + "/deleteRoom/{id}";
	public String ADD_NEWROOM_TYPE = "http://" + roomApi + "/addRoomType";
	public String GET_ALL_ROOMTYPES = "http://" + roomApi + "/getAllTypes";
	public String DELETE_ROOMTYPE = "http://" + roomApi + "/deleteRoomType/{type}";
	public String GET_REPORT = "http://" + roomApi + "/getReports";

	// ************************* API Calls To Room Management Service Ends Here ********************* //

	public String employeeApi = "employee-management-service";

	// ************************* API Calls to Employee Management Service ********************* //
	
	public String ADD_EMPLOYEE = "http://" + employeeApi + "/addEmployee";
	public String GET_ALL_EMPLOYEES = "http://" + employeeApi + "/getAllEmployees";
	public String GET_EMPLOYEE_BY_ID = "http://" + employeeApi + "/getEmployeeById/{id}";
	public String GET_EMPLOYEE_BY_NAME = "http://" + employeeApi + "/getEmployeeByName/{name}";
	public String GET_EMPLOYEE_BY_ROLE = "http://" + employeeApi + "/getEmployeeByRole/{role}";
	public String UPDATE_EMPLOYEE = "http://" + employeeApi + "/updateEmployee";
	public String DELETE_EMPLOYEE = "http://" + employeeApi + "/deleteEmployee/{id}";
	
	// ************************* API Calls to Employee Management Service Ends Here ********************* //
	
	// Adding a New Room
	public Show addRoom(Room room)
	{
		return restTemplate.postForObject(ADD_ROOM, room, Show.class);
	}
	
	// Adding Multiple Rooms
	public Show addMultipleRooms(int count, String type)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		Show result = restTemplate.postForObject(ADD_MULTIPLE_ROOMS, httpEntity, Show.class, count, type);
		return result;
	}
	
	// Updating Room
	public Show updateRoomDetails(Room room)
	{
		if(room.getId().equals(null))
		{
			return new Show("Invalid Id","Enter a proper Id");
		}
		
		RequestEntity<Room> requestEntity = RequestEntity.put(UPDATE_ROOM).accept(MediaType.APPLICATION_JSON).body(room);
		ResponseEntity<Show> updatedResult = restTemplate.exchange(requestEntity, Show.class);
		return updatedResult.getBody();
	}
	
	// Delete Room
	public Show deleteRoomById(String id)
	{
		ResponseEntity<Show> result = delete(DELETE_ROOM,id);
		return result.getBody();
	}
	
	// Add Room Type
	public Show addRoomType(RoomDetails roomDetails)
	{
		RequestEntity<RoomDetails> requestEntity = RequestEntity.post(ADD_NEWROOM_TYPE) .accept(MediaType.APPLICATION_JSON).body(roomDetails);
		ResponseEntity<Show> result = restTemplate.exchange(requestEntity,Show.class);
		return result.getBody();
	}
	
	// Delete Room Type
	public Show deleteRoomType(String type)
	{
		ResponseEntity<Show> result = delete(DELETE_ROOMTYPE,type);
		return result.getBody();
	}
	
	// ******************************* Room Methods End Here ***************************************//
	
	
	//Add New Employee
	public Show addEmployee(Employee employee)
	{
		if(employee.getRole().equalsIgnoreCase("OWNER"))
		{
			return new Show("Invalid Action!","You are not authorized to perform this action");
		}
		
		Show result = restTemplate.postForObject(ADD_EMPLOYEE, employee, Show.class);
		return result;
	}
	
	// Update Employee
	public Show updateEmployee(Employee employee)
	{
		if(employee.getId().equals(null))
		{
			return new Show("Invalid Id!"," Enter a proper Id");
		}
		
		if(employee.getRole().equalsIgnoreCase("OWNER"))
		{
			return new Show("Invalid Request!"," You are not authorized to perform this action");
		}
		
		RequestEntity<Employee> requestEntity = RequestEntity.put(UPDATE_EMPLOYEE).accept(MediaType.APPLICATION_JSON).body(employee);
		ResponseEntity<Show> result = restTemplate.exchange(requestEntity, Show.class);
		return result.getBody();
	}
	
	// Delete Employee - Manager is not Allowed to delete employees with role as Owner
	public Show deleteEmployeeById(String id)
	{
		Employee employee = restTemplate.getForObject(GET_EMPLOYEE_BY_ID+id, Employee.class);
		if(employee.getRole().equalsIgnoreCase("OWNER"))
		{
			return new Show("Invalid Request!","You are not authorized to perform this action");
		}
		
		ResponseEntity<Show> result = delete(DELETE_EMPLOYEE, id);
		return result.getBody();
	}
	
	// Get All Employees
	public List<Employee> getAllEmployees()
	{
		ResponseEntity<Object[]> employees = restTemplate.getForEntity(GET_ALL_EMPLOYEES, Object[].class);
		ObjectMapper mapper = new ObjectMapper();
		
		List<Employee> employeeList = Arrays.stream(employees.getBody()).map(e->mapper.convertValue(e,Employee.class)).collect(Collectors.toList());
		return employeeList;	
	}
	
	// Get Employee By Id
	public Employee getEmployeeById(String id)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_EMPLOYEE_BY_ID;

		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Employee> employee = restTemplate.exchange(url,HttpMethod.GET, httpEntity,Employee.class,id);
		return employee.getBody();
	}
	
	// Get Employee By Name
	public List<Employee> getEmployeeByName(String name)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_EMPLOYEE_BY_NAME;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Employee[]> employees = restTemplate.exchange(url,HttpMethod.GET, httpEntity,Employee[].class,name);
		Employee[] allEmployees = employees.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employeeList = Arrays.stream(allEmployees).map(e->mapper.convertValue(e, Employee.class)).collect(Collectors.toList());
		return employeeList;
	}
	
	// Get Employee By Role
	public List<Employee> getEmployeeByRole(String role){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_EMPLOYEE_BY_ROLE;

		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Employee[]> employees = restTemplate.exchange(url,HttpMethod.GET, httpEntity,Employee[].class,role);
		Employee[] allEmployees = employees.getBody();
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> employeeList = Arrays.stream(allEmployees).map(e->mapper.convertValue(e, Employee.class)).collect(Collectors.toList());
		return employeeList;
	}
		
	// ******************************* Employee Methods End Here ***************************************//
	
	
	// Common Code to implement delete methods
	public ResponseEntity<Show> delete(String url,String value)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Show> result = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Show.class, value);
		return result;
	}
}
