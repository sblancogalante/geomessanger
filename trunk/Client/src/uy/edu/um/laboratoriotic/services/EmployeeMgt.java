package uy.edu.um.laboratoriotic.services;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.Employee;
import uy.edu.um.laboratoriotic.business.EmployeeVO;

public interface EmployeeMgt {

	public void addEmployee(EmployeeVO oEmployee);
	
	public ArrayList<Employee> getEmployees();
	
}
