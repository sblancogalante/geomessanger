package uy.edu.um.laboratoriotic.services.factory.general;

public class GeneralFactory {

	/*
	 * Attributes of the class
	 */
	private static GeneralFactory instance = null;

	/*
	 * Constructors
	 */
	private GeneralFactory() {

	}

	public static GeneralFactory getInstance() {
		if (instance == null) {
			instance = new GeneralFactory();
		}

		return instance;
	}

}
