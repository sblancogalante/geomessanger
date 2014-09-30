package uy.edu.um.laboratoriotic.persistence;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.Employee;
import uy.edu.um.laboratoriotic.persistance.EmployeeDAOFactory;

public class EmployeeDAOTest {

	@Test
	public void test() {
		
		Employee oEmployee = new Employee("Santiago", "Blanco", 50620810);		
		
		EmployeeDAOFactory.getEmployeeDAOMgt().createTable();
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee);
	}

}
