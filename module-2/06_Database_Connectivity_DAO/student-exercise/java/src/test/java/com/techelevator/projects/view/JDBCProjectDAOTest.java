package com.techelevator.projects.view;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

import junit.framework.Assert;

public class JDBCProjectDAOTest {


private static final String TEST_FIRST_NAME = "TestFirstName";
private static final String TEST_LAST_NAME = "TestLastName";
private static SingleConnectionDataSource dataSource;
private JDBCProjectDAO dao;
private JDBCEmployeeDAO daoEmployee;

	@BeforeClass
	public static void setupDataSource() 
	{
	    dataSource = new SingleConnectionDataSource();
	    dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
	    dataSource.setUsername("postgres");
	    dataSource.setPassword("postgres1");
	    dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException 
	{
	    dataSource.destroy();
	}
	
	@Before
	public void setup() 
	{
		daoEmployee = new JDBCEmployeeDAO(dataSource);
	    dao = new JDBCProjectDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException 
	{
	    dataSource.getConnection().rollback();
	}
	
	@Test
	public void get_all_active_projects_test() 
	{
	    List<Project> myList = dao.getAllActiveProjects();
	    
	    assertNotNull(myList);
	    assertEquals(2, myList.size());
	}
	
	@Test
	public void remove_employee_from_project_test(){
		Long employeeID = daoEmployee.getAllEmployees().get(7).getId();
		Long projectID = dao.getAllActiveProjects().get(0).getId();
		int numBeforeAddingEmployee = daoEmployee.getEmployeesByProjectId(projectID).size();
		
		dao.addEmployeeToProject (projectID, employeeID);
		assertEquals(numBeforeAddingEmployee + 1, daoEmployee.getEmployeesByProjectId(projectID).size());
		dao.removeEmployeeFromProject(projectID, employeeID);
		assertEquals(numBeforeAddingEmployee, daoEmployee.getEmployeesByProjectId(projectID).size());
	}
	
	
	@Test
	public void add_employee_to_project_test() 
	{
		int count = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlInsertEmployee = "INSERT INTO Employee VALUES (DEFAULT, 1, ?, ?,'1987-06-24','M','2018-09-17')";

		jdbcTemplate.update(sqlInsertEmployee, TEST_FIRST_NAME, TEST_LAST_NAME);

		List<Employee> employee = daoEmployee.getEmployeesByProjectId(1L);
		assertNotNull(employee);

		dao.addEmployeeToProject(1L, daoEmployee.searchEmployeesByName(TEST_FIRST_NAME, TEST_LAST_NAME).get(0).getId());
		assertNotEquals(employee.size(), daoEmployee.getEmployeesByProjectId(1L).size());

		List<Employee> employee2 = daoEmployee.getEmployeesByProjectId(1L);
		for (Employee people : employee2) {
		    if (people.getFirstName().contains(TEST_FIRST_NAME)) {
		        count++;
		    }   
		}
		assertEquals(1, count); 
	}

}