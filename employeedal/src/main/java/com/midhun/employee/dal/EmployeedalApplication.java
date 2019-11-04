package com.midhun.employee.dal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;
import com.midhun.employee.dal.entities.Employee;
import com.midhun.employee.dal.service.EmployeeServiceImpl;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class EmployeedalApplication implements CommandLineRunner {

	@Autowired
	private EmployeeServiceImpl empl;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(EmployeedalApplication.class);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("**********************************************************");
		System.out.println("                   Application Started                    ");
		System.out.println("**********************************************************");
		try (Scanner input = new Scanner(System.in)) {
			boolean loop = true;
			Employee employee = new Employee();
			try {
				while (loop) {
					System.out.println();
					System.out.println("              Available Functions              ");
					System.out.println("***********************************************");
					System.out.println();
					System.out.println("1. Add a person");
					System.out.println("2. Edit Person");
					System.out.println("3. Delete Person");
					System.out.println("4. Count Number Of Person");
					System.out.println("5. List Persons");
					System.out.println("6. Add Person From XML");
					System.out.println("7. Exit");
					System.out.print("Enter the integer corresponding to your choice: ");
					int number = input.nextInt(); // Input from user
					// Display the available functions
					switch (number) {
					
					case 1:
						System.out.print("Enter Person's First Name: ");
						employee.setFirstName(input.next());
						System.out.print("Enter Person's Surname: ");
						employee.setSurname(input.next());
						System.out.print("Enter An Integer For Person's ID: ");
						employee.setId(input.nextLong());
						empl.addEmployee(employee);
						System.out.println(
								"Employee " + employee.getFirstName() + " Created with ID: " + employee.getId());
						break;
					case 2:
						System.out.print("Enter ID Of the Person You Want To Update: ");
						Long id = input.nextLong();
						employee.setId(id);
						System.out.print("Enter Person's First Name: ");
						employee.setFirstName(input.next());
						System.out.print("Enter Person's Surname: ");
						employee.setSurname(input.next());
						empl.editEmployee(employee);
						System.out.println("Employee Edited");
						break;
					case 3:
						System.out.print("Enter ID Of the Person You Want To Delete: ");
						empl.deleteEmployee(input.nextLong());
						System.out.println("Employee Deleted");
						break;
					case 4:
						System.out.println("The Number Of Persons Is: " + empl.countEmployee());
						break;
					case 5:
						List<Employee> empList = empl.listEmployee();
						System.out.println("The Employee List Is As Follows :");
						System.out.println();
						System.out.println("-------------------------------------------------------------------");
						System.out.printf("%10s %30s %20s", "ID", "FIRST NAME", "SURNAME");
						System.out.println();
						System.out.println("-------------------------------------------------------------------");
						System.out.println();
						for (Employee e : empList) {
							System.out.printf("%10s %30s %20s", e.getId(), e.getFirstName(), e.getSurname());
							System.out.println();
						}
						break;
					case 6:
						System.out.println("Read xml");
						empl.addEmpFromXML();
						break;
					case 7:
						System.out.println("Exiting Application");
						System.exit(0);
						break;
					default:
						System.out.println("Enter an integer between 1 and 6");

					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Error Occured: Please verify your Input type");
			} catch (EmptyResultDataAccessException e) {
				System.out.println("Error Occured: No data found");
			} catch (Exception e) {
				System.out.println("Error Occured: "+e.getMessage());
			}
		}
		;

	}
}
