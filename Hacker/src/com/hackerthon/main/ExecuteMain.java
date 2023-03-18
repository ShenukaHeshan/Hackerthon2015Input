package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.getEmpService;

// This is the class definition for ExecuteMain
public class ExecuteMain {

	/**
	 * This is the main method that will be executed when the program starts
	 * @param args command-line arguments, if any
	 */
	public static void main(String[] args) {
		// Create an object of the getEmpService class and assign it to the employeeService variable
		getEmpService employeeService = new getEmpService();
		try {
			// Call the requestTransform method from the UtilTransform class to transform some data
			UtilTransform.requestTransform();
			// Call the getEmployeesFromXML method from the employeeService object to retrieve employee data from an XML file
			employeeService.getEmployeesFromXML();
			// Call the createEmployeeTable method from the employeeService object to create a database table to store employee data
			employeeService.createEmployeeTable();
			// Call the addEmployee method from the employeeService object to add an employee to the database table
			employeeService.addEmployee();
			// Call the displayEmployees method from the employeeService object to display all employees stored in the database table
			employeeService.displayEmployees();
		} catch (Exception e) {
			// If an exception is thrown during any of the method calls above, catch it and do nothing with it.
			// This is generally not a good practice; it's better to handle the exception in some way, such as by logging an error message or displaying an error to the user.
			// However, this code does not provide any information on what to do in case of an exception.
		}
	}
}
}
