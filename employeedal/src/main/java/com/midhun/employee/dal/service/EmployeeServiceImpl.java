package com.midhun.employee.dal.service;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.midhun.employee.dal.entities.Employee;
import com.midhun.employee.dal.repos.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Employee addEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public Employee editEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		repo.deleteById(id);
	}

	@Override
	public int countEmployee() {

		return (int) repo.count();
	}

	@Override
	public List<Employee> listEmployee() {

		return repo.findAll();
	}

	@Override
	public void addEmpFromXML() {

		try {

			File xmlFile = new File("Employee.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("person");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Employee employee = new Employee();
					employee.setId(Long.parseLong(eElement.getAttribute("id")));
					employee.setFirstName(eElement.getElementsByTagName("firstname").item(0).getTextContent());
					employee.setSurname(eElement.getElementsByTagName("surname").item(0).getTextContent());
					repo.save(employee);
					System.out.println("The person : " + employee.getFirstName() + " is added with ID : "
							+ eElement.getAttribute("id") + " to the database");
				}
			}
		} catch (Exception e) {
			System.out.println("Error Occured :"+e.getMessage());
		}
	}

	public EmployeeRepository getRepo() {
		return repo;
	}

	public void setRepo(EmployeeRepository repo) {
		this.repo = repo;
	}

}
