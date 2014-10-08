package uy.edu.um.laboratoriotic.business;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.EmployeeVO;

/**
 * This interface is the one in charge of establishing communication between the modules
 * @author sblanco1
 *
 */
public interface EmployeeMgt {

	/*
	 * Methods
	 */
	public void addEmployee(Employee oEmployee);
	
	public void removeEmployee(int oEmployeeID);
	
	public EmployeeVO modifyEmployee(EmployeeVO oEmployee);
	
	public Employee searchEmployee(int oemployeeID);
	
	public ArrayList<EmployeeVO> getEmployees();
	
	public Employee getEmployeeVO(EmployeeVO oEmployeeVO);

	public EmployeeVO getEmployee(Employee oEmployee);

	
	
	
}
