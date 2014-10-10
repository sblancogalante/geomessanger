package uy.edu.um.laboratoriotic.business.management.general;

import uy.edu.um.laboratoriotic.business.entities.general.Type;

/**
 * This interface is the one in charge of establishing communication between the modules
 * @author sblanco1
 *
 */
public interface GeneralMgt {

	public void addType(Type oType);
	
	public void removeType(String oType);
	
}
