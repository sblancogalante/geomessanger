package uy.edu.um.laboratoriotic.business;

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
	
	public void removeEmployee();
	
	public EmployeeVO modifyEmployee(EmployeeVO oEmployee);
	
	public Employee searchEmployee(String oLastName, String oName);
	
	public Employee searchEmployee(String oUserName);
	
	public Employee getEmployees();
	
	public Employee getEmployee(EmployeeVO oEmployeeVO);

	public EmployeeVO getEmployeeVO(Employee oEmployee);
	
	
}
