package uy.edu.um.laboratoriotic.services;

import java.util.ArrayList;


public interface EmployeeMgt {

	public void addEmployee(EmployeeVO oEmployee);
	
	public ArrayList<EmployeeVO> getEmployees();
	
}
