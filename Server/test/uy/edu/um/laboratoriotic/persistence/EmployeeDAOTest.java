package uy.edu.um.laboratoriotic.persistence;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;

public class EmployeeDAOTest {

	@Test
	public void test() {
		
		Employee oEmployee = new Employee("Santiago", 50620810, "Uruguay", "Desarrollo", false);		
		
		Employee oEmployee1 = new Employee("Luis", 48963214, "Chile", "Testing", true);		
		
		Employee oEmployee2 = new Employee("Paul", 42735684, "Uruguay", "Testing", true);		
		
		EmployeeDAOFactory.getEmployeeDAOMgt().createTable();
		
		System.out.println("//////////////////////Test de addEmployee()//////////////////////");
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee);
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee1);
		
		EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee2);
		
		System.out.println("//////////////////////Test de getEmployees()/////////////////////");
		
		EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
		
		System.out.println("//////////////////////Test de searchEmployee()/////////////////////");
		
		EmployeeDAOFactory.getEmployeeDAOMgt().searchEmployee(oEmployee.getEmployeeID());
		
		System.out.println("//////////////////////Test de removeEmployee()/////////////////////");
		
		EmployeeDAOFactory.getEmployeeDAOMgt().removeEmployee(oEmployee.getEmployeeID());
	}

}
