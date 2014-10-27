package uy.edu.um.laboratoriotic.business.management.general;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This interface is the one in charge of establishing communication between the
 * modules
 * 
 * @author sblanco1
 *
 */
public interface GeneralMgt {

	/**
	 * This method makes sure that the data base adds a TypeVO
	 * 
	 * @param oEmployee
	 */
	public void addType(TypeVO oTypeVOVO);

	/**
	 * This method makes sure that the data base removes a country
	 * 
	 * @param oEmployeeID
	 */
	public void removeType(TypeVO oTypeVO);	

	/**
	 * This method makes sure that the data base modifies the information of the
	 * TypeVO
	 * 
	 * @param oEmployee
	 * @return
	 */
	public Type modifyType(TypeVO oTypeVO);

	/**
	 * This method makes sure that the data base searches a TypeVO
	 * 
	 * @param oemployeeID
	 * @return
	 */
	public Type searchType(TypeVO oTypeVO);

	/**
	 * This method makes sure that the database returns a list with all the
	 * current types
	 * 
	 * @return
	 * @throws DataBaseConnection
	 */
	public ArrayList<TypeVO> getTypes(String oType);			

}
