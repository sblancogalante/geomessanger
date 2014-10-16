package uy.edu.um.laboratoriotic.services.management.general;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This is the interface on the client side
 * @author sblanco1
 *
 */
public interface GeneralMgt {

	/**
	 * This method is the one that communicates with the commons interface
	 * to add a type
	 * @param oTypeVO
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void addType(TypeVO oTypeVO) throws RemoteException, NotBoundException;
	
	/**
	 * This method is the one that communicates with the commons interface
	 * to remove a type
	 * @param oTypeVO
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void removeType(TypeVO oTypeVO) throws RemoteException, NotBoundException;
	
	/**
	 * This method is the one that communicates with the commons interface
	 * to get a type
	 * @param oTypeVO
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public TypeVO getType(TypeVO oTypeVO) throws RemoteException, NotBoundException;
}
