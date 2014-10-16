package uy.edu.um.laboratoriotic.business.management.general;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This interface is the one in charge of establishing communication between the modules
 * @author sblanco1
 *
 */
public interface GeneralMgt {

	/**
	 * This method makes sure that the data base adds a type
	 * @param oEmployee
	 */
	public void addType(Type oType);
	
	/**
	 * This method makes sure that the data base removes a type
	 * @param oEmployeeID
	 */
	public void removeType(String oType);
		
	/**
	 * This method makes sure that the data base modifies the information
	 * of the type
	 * @param oEmployee
	 * @return
	 */
	public Type modifyType(Type oType);
	
	/**
	 * This method makes sure that the data base searches a type
	 * @param oemployeeID
	 * @return
	 */
	public Type searchType(String oValue);	
	
	/**
	 * This is an auxiliary method
	 * @param oTypeVO
	 * @return
	 */
	public Type getType(TypeVO oTypeVO);
	
}
