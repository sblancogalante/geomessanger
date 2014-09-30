package uy.edu.um.laboratoriotic.business;

/**
 * this class is the implementation of EmployeeMgt
 * @author sblanco1
 *
 */
public class EmployeeMgr implements EmployeeMgt{

	private static EmployeeMgr instance = null;
	
	private EmployeeMgr(){
		
	}
	
	public static EmployeeMgr getInstance(){
		if(instance == null){
			instance = new EmployeeMgr();
		}
		
		return instance;
	}
	
	@Override
	public EmployeeVO addEmployee(EmployeeVO oEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEmployee() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeeVO modifyEmployee(EmployeeVO oEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchEmployee(String oLastName, String oName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchEmployee(String oUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployees(EmployeeFilterVO oEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

}
