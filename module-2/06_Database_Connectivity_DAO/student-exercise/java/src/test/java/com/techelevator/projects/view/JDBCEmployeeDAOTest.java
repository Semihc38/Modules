package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.xml.transform.Templates;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

public class JDBCEmployeeDAOTest {

	private static SingleConnectionDataSource datasource;
	private EmployeeDAO dao;
	private JdbcTemplate jdbcTemplate;
	private Long testDepartmentId;
//	private Long testProjectId;
	
	@BeforeClass
	public static void setupDataSource() {
		datasource = new SingleConnectionDataSource();
		datasource.setUrl("jdbc:postgresql://localhost:5432/projects");
		datasource.setUsername("postgres");
		datasource.setPassword("postgres1");
		
		datasource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void destroyDataSource() {
		datasource.destroy();
	}
	
	@After
	public void rollbackTransaction() throws SQLException {
		datasource.getConnection().rollback();
	}
	
	@Before
	public void setupTest() {
		dao = new JDBCEmployeeDAO(datasource);
		jdbcTemplate = new JdbcTemplate(datasource);
		
		String insertDepartmentSql = "INSERT INTO department (department_id, name) VALUES (DEFAULT, 'departmentName') RETURNING department_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertDepartmentSql);
		rows.next();
		testDepartmentId = rows.getLong("department_id");
		
//		String insertProjectSql = "INSERT INTO project (project_id, name, from_date, to_date) VALUES (DEFAULT, projectName, null, null) RETURNING project_id";
//		SqlRowSet projectRows = jdbcTemplate.queryForRowSet(insertProjectSql);
//		projectRows.next();
//		testProjectId = projectRows.getLong("project_id");
	}
	
	@Test
	public void get_list_of_all_employees() {
		truncateEmployeeTable();
		createEmployee(createTestEmployee());
		
		List<Employee> employees = dao.getAllEmployees();
		Assert.assertEquals(1, employees.size());
		
	}
	
	@Test
	public void search_employees_by_name() {
		truncateEmployeeTable();
		Employee testEmployee = createTestEmployee();
		createEmployee(testEmployee);
		List<Employee> list = dao.searchEmployeesByName(testEmployee.getFirstName(), testEmployee.getLastName());
		
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void get_employees_by_department_id() {
		Employee testEmployee = createTestEmployee();
		createEmployee(testEmployee);
		List<Employee> list = dao.getEmployeesByDepartmentId(testDepartmentId);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void get_employees_without_projects() {
		List<Employee> originalList = dao.getEmployeesWithoutProjects();
		int originalSize = originalList.size();
		
		Employee testEmployee = createTestEmployee();
		createEmployee(testEmployee);
		List<Employee> newList = dao.getEmployeesWithoutProjects();
		int newListSize = newList.size();
		
		Assert.assertEquals(originalSize + 1, newListSize);
	
	}
	
	@Test 
	public void get_employees_with_project_id() {
		Long id = (long) 1;
		List<Employee> startingList = dao.getEmployeesByProjectId(id);
		int originalSize = startingList.size();
		Employee testEmployee = createTestEmployee();
		createEmployee(testEmployee);
		
		String insertProjectEmployeeSql = "INSERT INTO project_employee (project_id, employee_id) VALUES (1,"+ testEmployee.getId();
	}
	@Test
	public void test_change_employee_department() {
   
        dao.changeEmployeeDepartment(1L, 4L);
 
        List<Employee> results = dao.getEmployeesByDepartmentId(4L);

        System.out.println(results);
        Boolean match =Boolean.FALSE;
        for(Employee aResult : results) {
            if(aResult.getId().equals(1L)) {
               match=Boolean.TRUE;
               break;
            }
        }
        assertTrue(match);
    }
	
	private void createEmployee(Employee newEmployee) {
		String createEmployeeSql = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, "
				+ "gender, hire_date) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING employee_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(createEmployeeSql, testDepartmentId, newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getBirthDay(), 
				newEmployee.getGender(), newEmployee.getHireDate());
		rows.next();
		Long id = rows.getLong("employee_id");
		newEmployee.setId(id);
		
	}
	
	private Employee createTestEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("testFirstName");
		employee.setLastName("testLastName");
		employee.setBirthDay(LocalDate.parse("1994-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		employee.setGender('M');
		employee.setHireDate(LocalDate.parse("2020-02-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		return employee;
	}
	
	private void truncateEmployeeTable() {
		String truncateSql = "TRUNCATE employee CASCADE";
		jdbcTemplate.update(truncateSql);
	}
	
	
}