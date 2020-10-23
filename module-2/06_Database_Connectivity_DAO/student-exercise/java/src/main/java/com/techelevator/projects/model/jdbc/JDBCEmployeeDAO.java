package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		String sqlGetAllEmployees = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date FROM employee";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployees);
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}
		return allEmployees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> allEmployees = new ArrayList<>();
		String sqlGetAllEmployeesByName = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date FROM employee "+
				"WHERE first_name LIKE ? AND last_name LIKE ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployeesByName, "%" + firstNameSearch + "%", "%" + lastNameSearch + "%");
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}
		return allEmployees;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		List<Employee> allEmployees = new ArrayList<>();
		String sqlGetAllEmployeesFromDepartment = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date FROM employee "+
				"WHERE department_id=?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployeesFromDepartment, id);
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}
		return allEmployees;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		String query ="SELECT * FROM employee e "
				+"LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id"
				+"WHERE project_id IS NULL";
		SqlRowSet rowSet =jdbcTemplate.queryForRowSet(query);
		List<Employee>employeeWithoutProjects=new ArrayList<>();
		while(rowSet.next()) {
			Employee employee= mapRowToEmployee(rowSet);
			employeeWithoutProjects.add(employee);
		}
		return employeeWithoutProjects;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		List<Employee> allEmployees = new ArrayList<>();
		String sqlGetEmployeesByProject = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date FROM employee e "+
				"JOIN project_employee pe ON e.employee_id = pe.employee_id "+
				"WHERE pe.project_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeesByProject, projectId);
		while (results.next()) {
			Employee employeeResult = mapRowToEmployee(results);
			allEmployees.add(employeeResult);
		}
		return allEmployees;
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		String sqlUpdateEmployeeDepartment = "UPDATE employee SET department_id=? WHERE employee_id=?";
		jdbcTemplate.update(sqlUpdateEmployeeDepartment, departmentId, employeeId);
	}
	private Employee mapRowToEmployee(SqlRowSet results) {
		Employee employee = new Employee();
		employee.setId(results.getLong("employee_id"));
		employee.setDepartmentId(results.getLong("department_id"));
		employee.setFirstName(results.getString("first_name"));
		employee.setLastName(results.getString("last_name"));
		employee.setBirthDay(results.getDate("birth_date").toLocalDate());
		employee.setGender(results.getString("gender").charAt(0));
		employee.setHireDate(results.getDate("hire_date").toLocalDate());
		return employee;
		
	}

}
