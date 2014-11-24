package uy.edu.um.laboratoriotic.services.manager.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.exceptions.employee.MissingArguments;
import uy.edu.um.laboratoriotic.exceptions.employee.PasswordTooShort;
import uy.edu.um.laboratoriotic.exceptions.employee.UserNameAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.WrongLogin;
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
			NotBoundException, EmployeeAlreadyExists, PasswordTooShort,
			UserNameAlreadyExists, MissingArguments {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		try {
			oEmployeeRemoteMgt.addEmployee(oEmployeeVO);
		} catch (EmployeeAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new EmployeeAlreadyExists();
		} catch (PasswordTooShort e) {
			// TODO Auto-generated catch block
			throw new PasswordTooShort();
		} catch (UserNameAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new UserNameAlreadyExists();
		} catch (MissingArguments e) {
			// TODO Auto-generated catch block
			throw new MissingArguments();
		}

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
			NotBoundException, EmployeeDoesNotExist {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		try {
			oEmployeeRemoteMgt.removeEmployee(oEmployeeVO);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		}

	}

	@Override
	public EmployeeVO searchEmployee(String oUserName) throws RemoteException,
			NotBoundException, EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		try {
			oEmployeeVOToReturn = oEmployeeRemoteMgt.searchEmployee(oUserName);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		} catch (EmployeeAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new EmployeeAlreadyExists();
		}

		return oEmployeeVOToReturn;
	}

	@Override
	public EmployeeVO modifyEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException, NotBoundException, EmployeeDoesNotExist {
		// TODO Auto-generated method stub

		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		try {
			oEmployeeVOToReturn = oEmployeeRemoteMgt
					.modifyEmployee(oEmployeeVO);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		}

		return oEmployeeVOToReturn;
	}

	@Override
	public boolean checkLogin(EmployeeFilterVO oEmployeeVO)
			throws RemoteException, NotBoundException, WrongLogin {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		boolean toReturn;
		try {
			toReturn = oEmployeeRemoteMgt.checkLogin(oEmployeeVO);
		} catch (WrongLogin e) {
			// TODO Auto-generated catch block
			throw new WrongLogin();
		}

		return toReturn;
	}

	@Override
	public EmployeeVO getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws RemoteException, NotBoundException, EmployeeDoesNotExist {

		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeRemoteMgt oEmployeeRemoteMgt = newLookUp("EmployeeRemoteMgr");
		try {
			oEmployeeVOToReturn = oEmployeeRemoteMgt
					.getLoginEmployee(oEmployeeFilterVO);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		}

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