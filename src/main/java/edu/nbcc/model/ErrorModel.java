/**
 * 
 */
package edu.nbcc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jason
 *
 */
public class ErrorModel {
	private List<String> errors = new ArrayList<String>();

	/**
	 * Default Constructor
	 */
	public ErrorModel() {
		
	}
	
	/**
	 * constructor that takes an error string and adds it to the errors list.
	 * @param error the initial error to add to the list.
	 */
	public ErrorModel(String error) {
		this.errors.add(error);
	}
	
	/**
	 * Get the Errors list from the class
	 * @return
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * Set the errors list
	 * @param errors the list of errors to set as the property
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
