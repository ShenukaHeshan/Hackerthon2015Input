package com.hackerthon.common;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class UtilTransform extends UtilProperties {

	private static final ArrayList<Map<String, String>> l = new ArrayList<Map<String, String>>();

	private static Map<String, String> m = null;

	public static void requestTransform() throws Exception {

		Source x = new StreamSource(new File(UtilProperties.p.getProperty(Constants.EMPLOYEE_XML_PATH)));
		Source s = new StreamSource(new File(UtilProperties.p.getProperty(Constants.EMPLOYEE_XSL_PATH)));
		Result o = new StreamResult(new File(UtilProperties.p.getProperty(Constants.EMPLOYEE_RESPONSE_XML_PATH)));
		TransformerFactory.newInstance().newTransformer(s).transform(x, o);
	}

	public static ArrayList<Map<String, String>> xmlPaths() throws Exception {

		Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(UtilProperties.p.getProperty(Constants.EMPLOYEE_RESPONSE_XML_PATH));
		XPath x = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) x.compile(p.getProperty(Constants.COUNT_EMPLOYEE)).evaluate(d, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			m = new HashMap<String, String>();			
			
			String xpathEmployeeID = MessageFormat.format(p.getProperty(Constants.XPATH_EMPLOYEE_ID), i);
			String xpathEmployeeName = MessageFormat.format(p.getProperty(Constants.XPATH_EMPLOYEE_NAME), i);
			String xpathEmployeeAddress = MessageFormat.format(p.getProperty(Constants.XPATH_EMPLOYEE_ADDRESS), i);
			String xpathFacultyName = MessageFormat.format(p.getProperty(Constants.XPATH_FACULTY_NAME), i);
			String xpathDepartment = MessageFormat.format(p.getProperty(Constants.XPATH_DEPARTMENT), i);
			String xpathDesignation = MessageFormat.format(p.getProperty(Constants.XPATH_DESIGNATION), i);

			m.put("XpathEmployeeIDKey", (String) x.compile(xpathEmployeeID).evaluate(d, XPathConstants.STRING));
			m.put("XpathEmployeeNameKey", (String) x.compile(xpathEmployeeName).evaluate(d, XPathConstants.STRING));
			m.put("XpathEmployeeAddressKey", (String) x.compile(xpathEmployeeAddress).evaluate(d, XPathConstants.STRING));
			m.put("XpathFacultyNameKey", (String) x.compile(xpathFacultyName).evaluate(d, XPathConstants.STRING));
			m.put("XpathDepartmentKey", (String) x.compile(xpathDepartment).evaluate(d, XPathConstants.STRING));
			m.put("XpathDesignationKey", (String) x.compile(xpathDesignation).evaluate(d, XPathConstants.STRING));
			l.add(m);
			
		}
		return l;
	}
}
