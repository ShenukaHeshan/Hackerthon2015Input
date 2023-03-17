package com.hackerthon.service;

import org.xml.sax.SAXException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;
import com.hackerthon.common.UtilTransform;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import com.hackerthon.common.UtilQuery;
import java.io.IOException;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;

import com.hackerthon.common.Constants;
import com.hackerthon.common.UtilProperties;

public class getEmpService extends UtilProperties {

	private final ArrayList<Employee> el = new ArrayList<Employee>();

	private static Connection c;

	private static Statement s;

	private PreparedStatement ps;

	public getEmpService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	public void getEmployeesFromXML() {

		try {
			int s = UtilTransform.xmlPaths().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = UtilTransform.xmlPaths().get(i);
				Employee employee = new Employee();
				employee.setEmployeeId(l.get(Constants.XPATH_EMPLOYEE_ID_KEY));
				employee.setFullName(l.get(Constants.XPATH_EMPLOYEE_NAME_KEY));
				employee.setAddress(l.get(Constants.XPATH_EMPLOYEE_ADDRESS_KEY));
				employee.setFacultyName(l.get(Constants.XPATH_FACULTY_NAME_KEY));
				employee.setDepartment(l.get(Constants.XPATH_DEPARTMENT_KEY));
				employee.setDesignation(l.get(Constants.XPATH_DESIGNATION_KEY));
				el.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void createEmployeeTable() {
		try {
			s = c.createStatement();
			s.executeUpdate(UtilQuery.Q("q2"));
			s.executeUpdate(UtilQuery.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void addEmployee() {
		try {
			ps = c.prepareStatement(UtilQuery.Q("q3"));
			c.setAutoCommit(false);
			for(int i = 0; i < el.size(); i++){
				Employee e = el.get(i);
				ps.setString(1, e.getEmployeeId());
				ps.setString(2, e.getFullName());
				ps.setString(3, e.getAddress());
				ps.setString(4, e.getFacultyName());
				ps.setString(5, e.getDepartment());
				ps.setString(6, e.getDesignation());
				ps.addBatch();
			}
			ps.executeBatch();
			c.commit();
		} catch (Exception e) {
		}
	}

	public void getEmployeeById(String eid) {

		Employee e = new Employee();
		try {
			ps = c.prepareStatement(UtilQuery.Q("q4"));
			ps.setString(1, eid);
			ResultSet R = ps.executeQuery();
			while (R.next()) {
				e.setEmployeeId(R.getString(1));
				e.setFullName(R.getString(2));
				e.setAddress(R.getString(3));
				e.setFacultyName(R.getString(4));
				e.setDepartment(R.getString(5));
				e.setDesignation(R.getString(6));
			}
			ArrayList<Employee> l = new ArrayList<Employee>();
			l.add(e);
			employeeOutput(l);
		} catch (Exception ex) {
		}
	}

	public void deleteEmployee(String eid) {

		try {
			ps = c.prepareStatement(UtilQuery.Q("q6"));
			ps.setString(1, eid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayEmployees() {

		ArrayList<Employee> l = new ArrayList<Employee>();
		try {
			ps = c.prepareStatement(UtilQuery.Q("q5"));
			ResultSet r = ps.executeQuery();
			while (r.next()) {
				Employee e = new Employee();
				e.setEmployeeId(r.getString(1));
				e.setFullName(r.getString(2));
				e.setAddress(r.getString(3));
				e.setFacultyName(r.getString(4));
				e.setDepartment(r.getString(5));
				e.setDesignation(r.getString(6));
				l.add(e);
			}
		} catch (Exception e) {
		}
		employeeOutput(l);
	}
	
	public void employeeOutput(ArrayList<Employee> l){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < l.size(); i++){
			Employee e = l.get(i);
			System.out.println(e.getEmployeeId() + "\t" + e.getFullName() + "\t\t"
					+ e.getAddress() + "\t" + e.getFacultyName() + "\t" + e.getDepartment() + "\t"
					+ e.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
