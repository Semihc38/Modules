package com.techelevator.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Department;
import com.techelevator.model.DepartmentDAO;



/**
 * Controller to get the department data via API call
 */
	@RestController //tell the server there are controllers in this file
	public class DepartmentController {
	
		private DepartmentDAO deptDAO;
	// We want to controller to return all the departments in the Department table
	//Is there DAO method to return all the Departments? Yes -getAllDepartments() in DepartmentDAO
	//What path do we want the API caller to use to getAllDepartments? /departments
	
		//Create a constructor for the controller call that takes a DAO object as a parameter
		//spring dependcy injection will create an instance of the DAO and pass it to the const
		
	public DepartmentController(DepartmentDAO aDeptDAO) {
		this.deptDAO=aDeptDAO;//assign the DAO object passed to our DAO reference
	}
	
	//identify the method to handle GET request for that path/departments
	//Is there DAO method to return specific Departments? Yes -getAllDepartments() in DepartmentDAO
	@RequestMapping(path="/departments",method=RequestMethod.GET)
	public List<Department>getAllDepartments(){
		logAPICall("GET -/departments");//log the API call
		return deptDAO.getAllDepartments();
		
		
	}
	// We want a controller to return a specific department in the Department Table
	//What path do we want the API caller to use to specific department? /departments/{}
	@RequestMapping(path="/departments/{id}",method=RequestMethod.GET)
	public Department getDeptById(@PathVariable Long id) {
		logAPICall("GET - /departments/"+id);
		return deptDAO.getDepartmentById(id);
	}
	
	// We want a controller to return a specific department in the Department Table searchDepartmentsByName(String nameSearch);
		//What path do we want the API caller to use to specific department/search?name=whatever 
	//is there a default department name we want to use? Yes- empty String 
	@RequestMapping(path="/departments/search", method=RequestMethod.GET)
	public List<Department> getDepartmentByName(@RequestParam (value="name", defaultValue= "")String nameSearch){
		
		logAPICall("GET - /departments/search?name="+nameSearch);
		return deptDAO.searchDepartmentsByName(nameSearch);
	}
	
		//we want to add a department
	//do  we have dao?- yes createDepartment(Department newDepartment)
	//what path? departments with a Post request
	   @RequestMapping(path="/departments",method=RequestMethod.POST)
	   //The data for the new Department will be in the request body for the POST
	    public Department createADept(@RequestBody Department aDepartment)  {
		   logAPICall("POST - /departments"+aDepartment);
	    return	deptDAO.createDepartment(aDepartment);
	    	
	    
	    }
	
public void logAPICall(String message) {
	 LocalDateTime now = LocalDateTime.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
     String timeNow = now.format(formatter);
     System.out.println(timeNow + "-" + message);
 }
}


