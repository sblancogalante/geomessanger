package uy.edu.um.laboratoriotic.services.management.general;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

public interface GeneralMgt {

	public void addType(TypeVO oTypeVO) throws RemoteException, NotBoundException;
	
	public void removeType(TypeVO oTypeVO) throws RemoteException, NotBoundException;
}
