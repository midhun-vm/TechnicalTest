package com.midhun.employee.dal.service;

import java.util.List;

import com.midhun.employee.dal.entities.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public Employee editEmployee(Employee employee);

	public void deleteEmployee(Long id);

	public int countEmployee();

	public List<Employee> listEmployee();

	public void addEmpFromXML();

}
