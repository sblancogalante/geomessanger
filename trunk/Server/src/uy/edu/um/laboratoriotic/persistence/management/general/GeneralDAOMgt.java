package uy.edu.um.laboratoriotic.persistence.management.general;

import uy.edu.um.laboratoriotic.business.entities.general.Type;

/**
 * This is the persistence interface
 * @author sblanco1
 *
 */
public interface GeneralDAOMgt {

	/**
	 * This method add a new type to the data base
	 * @param oType
	 */
	public void addType(Type oType);
	
	/**
	 * This method removes a type from the database
	 * @param oType
	 */
	public void removeType(String oType);
	
	/**
	 * This method removes a value from the data base
	 * @param oType
	 */
	public void removeValue(String oValue);

	/**
	 * This method searches a type in the database
	 * @param oType
	 * @return
	 */
	public Type searchType(String oType);
	
}