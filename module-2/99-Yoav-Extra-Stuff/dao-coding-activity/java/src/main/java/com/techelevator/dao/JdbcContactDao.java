package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.domain.Contact;

public class JdbcContactDao implements ContactDao {

	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcContactDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public Contact create(Contact contact) {
		String query= "insert into contact (id,first_name, last_name, phone, email, birthyear) values"
				+ " (?,?, ?,?,?,?)";
		Integer nextId =getNextContactId();
		
		jdbcTemplate.update(query, nextId,contact.getFirstName(),
				contact.getLastName(),contact.getPhone(),contact.getEmail(),contact.getBirthYear());
		contact.setId(nextId);
		
		
		return contact;
	}
	private Integer getNextContactId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_contact_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}

	@Override
	public Contact getContactById(Integer id) {
		String query ="SELECT id, first_name, last_name, phone, email, birthyear from contact where id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, id);
		
		if(rowSet.next()) {
			Contact result= mapRowToContact(rowSet);
			return result;
		}
		return null;
	}

	@Override
	public List<Contact> getAllContacts() {
		String query ="SELECT id, first_name, last_name, phone, email, birthyear from contact ";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
		List<Contact>result= new ArrayList<>();
		
		while(rowSet.next()) {
			Contact contact = mapRowToContact(rowSet);
			result.add(contact);
			
		}
		return result;
	}

	@Override
	public Contact update(Contact contact) {
		String query="update contact set first_name=?,last_name=?,phone=?,email=?,birthyear=? where id=?";
		jdbcTemplate.update(query, contact.getFirstName(),contact.getLastName(),
				contact.getPhone(),contact.getEmail(),contact.getBirthYear(),contact.getId());
		return null;
	}
	
	@Override
	public void delete(Contact contact) {
		
			String query="delete from contact where id=?";
			jdbcTemplate.update(query,9);
			System.out.println("Delete");
		
		
	}
	private Contact mapRowToContact(SqlRowSet row) {
		Contact contact = new Contact();
		Integer id= row.getInt("id");
		contact.setId(id);
		contact.setFirstName(row.getString("first_name"));
		contact.setLastName(row.getString("last_name"));
		contact.setPhone(row.getString("phone"));
		contact.setEmail(row.getString("email"));
		contact.setBirthYear(row.getInt("birthyear"));
		return contact;
	}

}
