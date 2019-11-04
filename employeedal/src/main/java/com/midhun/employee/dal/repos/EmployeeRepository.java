package com.midhun.employee.dal.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midhun.employee.dal.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
