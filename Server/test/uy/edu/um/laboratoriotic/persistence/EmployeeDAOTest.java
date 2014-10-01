package uy.edu.um.laboratoriotic.persistence;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.Employee;
import uy.edu.um.laboratoriotic.persistance.EmployeeDAOFactory;

public class EmployeeDAOTest {

	@Test
	public void test() {
		
		Employee oEmployee = new Employee("Santiago", "Blanco", 50620810, "Uruguay", "Desarrollo", false);		
		
		Employee oEmployee1 = new Employee("Luis", "Gurmendez", 48726458, "Chile", "Testing", true);		
		
		Employee oEmployee2 = new Employee("Paul", "Castaibert", 42735684, "Uruguay", "Testing", true);		
		
		EmployeeDAOFactory.getEmployeeDAOMgt().createTable();
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee);
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee1);
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee2);
		
		EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
	}

}
