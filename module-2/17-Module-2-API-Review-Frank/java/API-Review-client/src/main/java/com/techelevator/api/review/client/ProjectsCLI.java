package com.techelevator.api.review.client;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.api.review.client.model.AuthenticatedUser;
import com.techelevator.api.review.client.model.Department;
import com.techelevator.api.review.client.model.DepartmentDAO;
import com.techelevator.api.review.client.model.Employee;
import com.techelevator.api.review.client.model.EmployeeDAO;
import com.techelevator.api.review.client.model.Project;
import com.techelevator.api.review.client.model.ProjectDAO;
import com.techelevator.api.review.client.model.jdbc.JDBCDepartmentDAO;
import com.techelevator.api.review.client.model.jdbc.JDBCEmployeeDAO;
import com.techelevator.api.review.client.model.jdbc.JDBCProjectDAO;
import com.techelevator.api.review.client.services.AuthenticationService;
import com.techelevator.api.review.client.services.AuthenticationServiceException;
import com.techelevator.api.review.client.view.Menu;
import com.techelevator.api.review.client.services.ConsoleService;
import com.techelevator.api.review.client.model.UserCredentials;

public class ProjectsCLI {
	
	private static final String MAIN_MENU_OPTION_REGISTER = "Register";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login";
	private static final String MAIN_MENU_OPTION_EMPLOYEES = "Employees";
	private static final String MAIN_MENU_OPTION_DEPARTMENTS = "Departments";
	private static final String MAIN_MENU_OPTION_PROJECTS = "Projects";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_DEPARTMENTS, 
																	 MAIN_MENU_OPTION_EMPLOYEES, 
																	 MAIN_MENU_OPTION_PROJECTS, 
																	 MAIN_MENU_OPTION_LOGIN,
																	 MAIN_MENU_OPTION_REGISTER,
																	 MAIN_MENU_OPTION_EXIT };

	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

	private static final String DEPT_MENU_OPTION_ALL_DEPARTMENTS = "Show all departments";
	private static final String DEPT_MENU_OPTION_SEARCH_BY_NAME = "Department search by name";
	private static final String DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES = "Show department employees";
	private static final String DEPT_MENU_OPTION_ADD_DEPARTMENT = "Add a new department";
	private static final String DEPT_MENU_OPTION_UPDATE_NAME = "Update department name";
	private static final String[] DEPARTMENT_MENU_OPTIONS = new String[] { DEPT_MENU_OPTION_ALL_DEPARTMENTS,
																		   DEPT_MENU_OPTION_SEARCH_BY_NAME,
																		   DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES,
																		   DEPT_MENU_OPTION_ADD_DEPARTMENT,
																		   DEPT_MENU_OPTION_UPDATE_NAME,
																		   MENU_OPTION_RETURN_TO_MAIN};
	
	private static final String EMPL_MENU_OPTION_ALL_EMPLOYEES = "Show all employees";
	private static final String EMPL_MENU_OPTION_SEARCH_BY_NAME = "Employee search by name";
	private static final String EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS = "Show employees without projects";
	private static final String EMPL_MENU_OPTION_CHANGE_DEPARTMENT = "Change employee's department";
	private static final String[] EMPL_MENU_OPTIONS = new String[] { EMPL_MENU_OPTION_ALL_EMPLOYEES,
																	 EMPL_MENU_OPTION_SEARCH_BY_NAME,
																	 EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS,
																	 EMPL_MENU_OPTION_CHANGE_DEPARTMENT,
																	 MENU_OPTION_RETURN_TO_MAIN};
	
	private static final String PROJ_MENU_OPTION_ACTIVE_PROJECTS = "Show active projects";
	private static final String PROJ_MENU_OPTION_PROJECT_EMPLOYEES = "Show project employees";
	private static final String PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT = "Assign an employee to a project";
	private static final String PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT = "Remove employee from project";
	private static final String[] PROJ_MENU_OPTIONS = new String[] { PROJ_MENU_OPTION_ACTIVE_PROJECTS,
																	 PROJ_MENU_OPTION_PROJECT_EMPLOYEES,
																	 PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT,
																	 PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT,
																	 MENU_OPTION_RETURN_TO_MAIN };
	
	private Menu menu;
	private DepartmentDAO departmentDAO;
	private EmployeeDAO employeeDAO;
	private ProjectDAO projectDAO;
	
	private ConsoleService console;
	private AuthenticationService authenticationService;
	
	private AuthenticatedUser currentUser;
	
	private static final String API_SERVER = "http://localhost:8080";
	
	public static void main(String[] args) {
		ProjectsCLI application = new ProjectsCLI();
		application.run();
		
	}
	
	public ProjectsCLI() {
		this.menu = new Menu(System.in, System.out);
		this.console = new ConsoleService(System.in, System.out);
		this.authenticationService = new AuthenticationService(API_SERVER);
		// postgres is a Data Base Mangager which contains many databases which contains many tables
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		departmentDAO = new JDBCDepartmentDAO(dataSource);
		employeeDAO   = new JDBCEmployeeDAO(dataSource);
		projectDAO    = new JDBCProjectDAO(dataSource);
	}

	private void run() {
		displayApplicationBanner();	
		while(true) {
			printHeading("Main Menu");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			switch(choice) {
			   case MAIN_MENU_OPTION_REGISTER: {
				  handleRegisters();
				  break;
			   } 
			   case MAIN_MENU_OPTION_LOGIN: {
				  handleLogins();
				  break;
			   } 
			   case MAIN_MENU_OPTION_DEPARTMENTS: {
				  handleDepartments();
				  break;
			   } 
			   case MAIN_MENU_OPTION_EMPLOYEES: {
				   handleEmployees();
				   break;
			   } 
			   case MAIN_MENU_OPTION_PROJECTS: {
				   handleProjects();
				   break;
			   } 
			   case MAIN_MENU_OPTION_EXIT: {
				   System.out.println("Thanks for trying our app");
				   System.exit(0);
			   }
		    }
		}
	}

	private void handleLogins() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
			{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
				System.out.println("Login successful");
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
		 
	}
	
	private void handleRegisters() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
	       while (!isRegistered) //will keep looping until user is registered
	       {
	           UserCredentials credentials = collectUserCredentials();
	           try {
	           	authenticationService.register(credentials);
	           	isRegistered = true;
	           	System.out.println("Registration successful. You can now login.");
	            } catch(AuthenticationServiceException e) {
	            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
					System.out.println("Please attempt to register again.");
	            }
	           
	        }
		}
	private void handleDepartments() {
		printHeading("Departments");
		String choice = (String)menu.getChoiceFromOptions(DEPARTMENT_MENU_OPTIONS);
		if(choice.equals(DEPT_MENU_OPTION_ALL_DEPARTMENTS)) {
			handleListAllDepartments();
		} else if(choice.equals(DEPT_MENU_OPTION_SEARCH_BY_NAME)) {
			handleDepartmentSearch();
		} else if(choice.equals(DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES)) {
			handleDepartmentEmployeeList();
		} else if(choice.equals(DEPT_MENU_OPTION_ADD_DEPARTMENT)) {
			handleAddDepartment();
		} else if(choice.equals(DEPT_MENU_OPTION_UPDATE_NAME)) {
			handleUpdateDepartmentName();
		} 
	}

	private void handleAddDepartment() {
		printHeading("Add New Department");
		String newDepartmentName = getUserInput("Enter new Department name");
		Department newDepartment = new Department();
		newDepartment.setName(newDepartmentName);
		newDepartment = departmentDAO.createDepartment(newDepartment);
		System.out.println("\n*** "+newDepartment.getName()+" created ***");
	}
	
	private void handleUpdateDepartmentName() {
		printHeading("Update Department Name");
		List<Department> allDepartments = departmentDAO.getAllDepartments();
		if(allDepartments.size() > 0) {
			System.out.println("\n*** Choose a Department ***");
			Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
			String newDepartmentName = getUserInput("Enter new Department name");
			selectedDepartment.setName(newDepartmentName);
			departmentDAO.saveDepartment(selectedDepartment);
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleListAllDepartments() {
		// Changed on 10/30/2020 to call api to get the data instead of database fjf
		printHeading("All Departments");

		// To get data from an API we will use a RestTemplate
		RestTemplate apiCall = new RestTemplate();
		
		// Call the APi to array of generic response - getForEntity instead of getForObject
		ResponseEntity<Department[]> responseEntity = apiCall.getForEntity(API_SERVER+"http://localhost:8080/departments",Department[].class);
		
		// Take the response data and convert it to a List
		List<Department> allDepartments =  Arrays.asList(responseEntity.getBody());

		//List<Department> allDepartments = departmentDAO.getAllDepartments();

		listDepartments(allDepartments);
	}


	private void handleDepartmentSearch() {
		printHeading("Department Search");
		String departmentSearch = getUserInput("Enter department name to search for");
		List<Department> departments = departmentDAO.searchDepartmentsByName(departmentSearch);
		listDepartments(departments);
	}
	
	private void handleDepartmentEmployeeList() {
		printHeading("Department Employee List");
		List<Department> allDepartments = departmentDAO.getAllDepartments();
		if(allDepartments.size() > 0) {
			System.out.println("\n*** Choose a Department ***");
			Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
			List<Employee> departmentEmployees = employeeDAO.getEmployeesByDepartmentId(selectedDepartment.getId());
			listEmployees(departmentEmployees);
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void listDepartments(List<Department> departments) {
		System.out.println();
		if(departments.size() > 0) {
			for(Department dept : departments) {
				System.out.println(dept.getName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}
	
	private void handleEmployees() {
		printHeading("Employees");
		String choice = (String)menu.getChoiceFromOptions(EMPL_MENU_OPTIONS);
		if(choice.equals(EMPL_MENU_OPTION_ALL_EMPLOYEES)) {
			handleListAllEmployees();
		} else if(choice.equals(EMPL_MENU_OPTION_SEARCH_BY_NAME)) {
			handleEmployeeSearch();
		} else if(choice.equals(EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS)) {
			handleUnassignedEmployeeSearch();
		} else if(choice.equals(EMPL_MENU_OPTION_CHANGE_DEPARTMENT)) {
			handleChangeEmployeeDepartment();
		}
	}

	private void handleListAllEmployees() {
		printHeading("All Employees");
		// To get data from an API we will use a RestTemplate
		RestTemplate apiCall = new RestTemplate();
				
		// Call the APi to array of generic response - getForEntity instead of getForObject
		ResponseEntity<Employee[]> responseEntity = apiCall.getForEntity("http://localhost:8080/employees",Employee[].class);
				
		// Take the response data and convert it to a List
	    List<Employee> allEmployees =  Arrays.asList(responseEntity.getBody());

		listEmployees(allEmployees);
	}

	private void handleEmployeeSearch() {
		printHeading("Employee Search");
		String firstNameSearch = getUserInput("Enter first name to search for");
		String lastNameSearch = getUserInput("Enter last name to search for");
		List<Employee> employees = employeeDAO.searchEmployeesByName(firstNameSearch, lastNameSearch);
		listEmployees(employees);
	}

	private void handleUnassignedEmployeeSearch() {
		printHeading("Unassigned Employees");
		List<Employee> employees = employeeDAO.getEmployeesWithoutProjects();
		listEmployees(employees);
	}
	
	private void listEmployees(List<Employee> employees) {
		System.out.println();
		if(employees.size() > 0) {
			for(Employee emp : employees) {
				System.out.println(emp.getLastName() + ", " + emp.getFirstName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}
	
	private void handleChangeEmployeeDepartment() {
		printHeading("Change Employee Department");
		
		System.out.println("Choose an employee to transfer:");
		List<Employee> allEmployees = employeeDAO.getAllEmployees();
		Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(allEmployees.toArray());
		
		System.out.println("Choose the new department:");
		List<Department> allDepartments = departmentDAO.getAllDepartments();
		Department selectedDepartment = (Department)menu.getChoiceFromOptions(allDepartments.toArray());
		
		employeeDAO.changeEmployeeDepartment(selectedEmployee.getId(), selectedDepartment.getId());
	}

	private void handleProjects() {
		printHeading("Projects");
		String choice = (String)menu.getChoiceFromOptions(PROJ_MENU_OPTIONS);
		if(choice.equals(PROJ_MENU_OPTION_ACTIVE_PROJECTS)) {
			handleListActiveProjects();
		} else if(choice.equals(PROJ_MENU_OPTION_PROJECT_EMPLOYEES)) {
			handleProjectEmployeeList();
		} else if(choice.equals(PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT)) {
			handleEmployeeProjectAssignment();
		}  else if(choice.equals(PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT)) {
			handleEmployeeProjectRemoval();
		}
	}

	private void handleListActiveProjects() {
		printHeading("Active Projects");
		List<Project> projects = projectDAO.getAllActiveProjects();
		listProjects(projects);
	}

	private void handleEmployeeProjectRemoval() {
		printHeading("Remove Employee From Project");
		
		Project selectedProject = getProjectSelectionFromUser();
		
		System.out.println("Choose an employee to remove:");
		List<Employee> projectEmployees = employeeDAO.getEmployeesByProjectId(selectedProject.getId());
		if(projectEmployees.size() > 0) {
			Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(projectEmployees.toArray());
			projectDAO.removeEmployeeFromProject(selectedProject.getId(), selectedEmployee.getId());
			System.out.println("\n*** "+selectedEmployee+" removed from "+selectedProject+" ***");
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void handleEmployeeProjectAssignment() {
		printHeading("Assign Employee To Project");
		
		Project selectedProject = getProjectSelectionFromUser();
		
		System.out.println("Choose an employee to add:");
		List<Employee> allEmployees = employeeDAO.getAllEmployees();
		Employee selectedEmployee = (Employee)menu.getChoiceFromOptions(allEmployees.toArray());
		
		projectDAO.addEmployeeToProject(selectedProject.getId(), selectedEmployee.getId());
		System.out.println("\n*** "+selectedEmployee+" added to "+selectedProject+" ***");
	}
	
	private void handleProjectEmployeeList() {
		Project selectedProject = getProjectSelectionFromUser();
		List<Employee> projectEmployees = employeeDAO.getEmployeesByProjectId(selectedProject.getId());
		listEmployees(projectEmployees);
	}

	private Project getProjectSelectionFromUser() {
		System.out.println("Choose a project:");
		List<Project> allProjects = projectDAO.getAllActiveProjects();
		return (Project)menu.getChoiceFromOptions(allProjects.toArray());
	}
	
	private void listProjects(List<Project> projects) {
		System.out.println();
		if(projects.size() > 0) {
			for(Project proj : projects) {
				System.out.println(proj.getName());
			}
		} else {
			System.out.println("\n*** No results ***");
		}
	}

	private void printHeading(String headingText) {
		System.out.println("\n"+headingText);
		for(int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	@SuppressWarnings("resource")
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}

	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
	
	private void displayApplicationBanner() {
		System.out.println(" ______                 _                         _____           _           _     _____  ____");
		System.out.println("|  ____|               | |                       |  __ \\         (_)         | |   |  __ \\|  _ \\");
		System.out.println("| |__   _ __ ___  _ __ | | ___  _   _  ___  ___  | |__) | __ ___  _  ___  ___| |_  | |  | | |_) |");
		System.out.println("|  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\ |  ___/ '__/ _ \\| |/ _ \\/ __| __| | |  | |  _ <");
		System.out.println("| |____| | | | | | |_) | | (_) | |_| |  __/  __/ | |   | | | (_) | |  __/ (__| |_  | |__| | |_) |");
		System.out.println("|______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___| |_|   |_|  \\___/| |\\___|\\___|\\__| |_____/|____/");
		System.out.println("                 | |             __/ |                          _/ |");
		System.out.println("                 |_|            |___/                          |__/");
		System.out.println();
	}
}
