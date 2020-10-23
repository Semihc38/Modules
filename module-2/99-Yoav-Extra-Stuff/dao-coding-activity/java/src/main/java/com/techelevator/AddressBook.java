package com.techelevator;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.dao.ContactDao;
import com.techelevator.dao.JdbcContactDao;
import com.techelevator.domain.Contact;

public class AddressBook {

	public static void main(String[] args) {
		
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/addressbook");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		// Create and use ContactDao
		ContactDao dao =new JdbcContactDao(dataSource);
		
		Contact contact=dao.getContactById(6);
		//System.out.println(contact);
//		
//		Contact newContact =new Contact();
//		
//		newContact.setFirstName("Will");
//		newContact.setLastName("rowwood");
//		newContact.setEmail("barrow234@gmail.com");
//		newContact.setPhone("312-980-2344");
//		newContact.setBirthYear(1990);
//		
//		dao.create(newContact);
		
//		List<Contact>contactList=dao.getAllContacts();
//		
//		for(Contact theContact: contactList) {
//			System.out.println(theContact);
//			
//		}
//		contact.setFirstName("Change");
//		dao.update(contact);
	
		
			dao.delete(contact);
		
	
	}

}
