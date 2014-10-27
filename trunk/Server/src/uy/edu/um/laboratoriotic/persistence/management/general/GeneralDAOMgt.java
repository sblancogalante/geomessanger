package uy.edu.um.laboratoriotic.persistence.management.general;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.general.Type;

/**
 * This is the persistence interface
 * 
 * @author sblanco1
 *
 */
public interface GeneralDAOMgt {

	/**
	 * This method add a new type to the data base
	 * 
	 * @param oType
	 */
	public void addType(Type oType);

	/**
	 * This method removes a type from the database
	 * 
	 * @param oType
	 */
	public void removeType(Type oType);

	/**
	 * This method searches a country in the database
	 * 
	 * @param oType
	 * @return
	 */
	public Type searchType(Type oType);

	/**
	 * This method obtains a list with the types asked
	 * 
	 * @param oType
	 * @return
	 */
	public ArrayList<Type> getTypes(String oType);

}
