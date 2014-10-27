package uy.edu.um.laboratoriotic.services.management.general;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This interface is the one that communicates the client module with the server
 * module
 * 
 * @author sblanco1
 * 
 */
public interface GeneralRemoteMgt extends Remote {

	public void addType(TypeVO oTypeVO) throws RemoteException;

	public void removeType(TypeVO oTypeVO) throws RemoteException;

	public TypeVO getType(TypeVO oTypeVO) throws RemoteException;

	public ArrayList<TypeVO> getTypes(String oType) throws RemoteException;	

}
