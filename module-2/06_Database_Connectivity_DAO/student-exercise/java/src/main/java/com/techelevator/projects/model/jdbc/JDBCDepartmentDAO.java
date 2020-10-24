package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department>allDepartment= new ArrayList<>();
		String query="SELECT department_id, name FROM department";
		SqlRowSet result=jdbcTemplate.queryForRowSet(query);
		while(result.next()) {
			Department deptResult=mapRowToDepartment(result);
			allDepartment.add(deptResult);
		}
		
		return allDepartment;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		List<Department> foundDepartments = new ArrayList<>();
		nameSearch = "%" + nameSearch + "%";
		String sqlGetFoundDepartments = "SELECT department_id, name FROM department WHERE name LIKE ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFoundDepartments, nameSearch);
		while (results.next()) {
			Department departmentResult = mapRowToDepartment(results);
			foundDepartments.add(departmentResult);
		}
		return foundDepartments;
		
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		
		String updateDepartmentSql = "UPDATE department SET department_id = ?, name = ? WHERE department_id = ?";
		
		jdbcTemplate.update(updateDepartmentSql, updatedDepartment.getId(), updatedDepartment.getName(), updatedDepartment.getId());
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		String sqlCreateDepartment = "INSERT INTO department (name) VALUES (?) RETURNING department_id";
		Long id = jdbcTemplate.queryForObject(sqlCreateDepartment, Long.class, newDepartment.getName());
		
		return getDepartmentById(id);
	}
	


	@Override
	public Department getDepartmentById(Long id) {
		String sqlGetDepartment = "SELECT department_id, name FROM department WHERE department_id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetDepartment, id);
		if (results.next()) {
			return mapRowToDepartment(results);
		} else {
			return null;
		}
	}
	private Department mapRowToDepartment(SqlRowSet result) {
		Department dept =new Department();
		dept.setId(result.getLong("department_id"));
		dept.setName(result.getString("name"));
		return dept;
	}

}
