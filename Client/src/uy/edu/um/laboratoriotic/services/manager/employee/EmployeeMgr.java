package uy.edu.um.laboratoriotic.services.manager.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.ServiceFacade;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This is the implementation of EmployeeMgt
 * 
 * @author sblanco1
 * 
 */
public class EmployeeMgr implements EmployeeMgt {

	/*
	 * Attributes of the class
	 */
	private static EmployeeMgr instance = null;

	/*
	 * Constructors
	 */
	private EmployeeMgr() {

	}

	public static EmployeeMgr getInstance() {

		if (instance == null) {
			instance = new EmployeeMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		oEmployeeRemoteMgt.addEmployee(oEmployeeVO);

	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		ArrayList<EmployeeVO> oListToReturn = new ArrayList<EmployeeVO>();

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		oListToReturn = oEmployeeRemoteMgt.getEmployees();

		return oListToReturn;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		oEmployeeRemoteMgt.removeEmployee(oEmployeeVO);

	}

	@Override
	public EmployeeVO searchEmployee(String oUserName) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		oEmployeeVOToReturn = oEmployeeRemoteMgt.searchEmployee(oUserName);

		return oEmployeeVOToReturn;
	}

	@Override
	public EmployeeVO modifyEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub

		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		oEmployeeVOToReturn = oEmployeeRemoteMgt.modifyEmployee(oEmployeeVO);

		return oEmployeeVOToReturn;
	}

	@Override
	public boolean checkLogin(EmployeeFilterVO oEmployeeVO)
			throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		boolean toReturn = oEmployeeRemoteMgt.checkLogin(oEmployeeVO);

		return toReturn;
	}

	@Override
	public EmployeeVO getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws RemoteException, NotBoundException {

		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		oEmployeeVOToReturn = oEmployeeRemoteMgt
				.getLoginEmployee(oEmployeeFilterVO);

		return oEmployeeVOToReturn;
	}

	/*
	 * Helping methods
	 */
	private EmployeeRemoteMgt newLookUp(String sObjectService)
			throws RemoteException, NotBoundException {

		EmployeeRemoteMgt oEmployeeRemoteMgtToReturn;

		Registry oRegistry = LocateRegistry
				.getRegistry(ServiceFacade.getInstance().getHost(),
						ServiceFacade.getInstance().getPort());
		oEmployeeRemoteMgtToReturn = (EmployeeRemoteMgt) oRegistry
				.lookup(sObjectService);

		return oEmployeeRemoteMgtToReturn;
	}

}