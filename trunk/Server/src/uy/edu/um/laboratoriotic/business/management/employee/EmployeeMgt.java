package uy.edu.um.laboratoriotic.business.management.employee;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

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
