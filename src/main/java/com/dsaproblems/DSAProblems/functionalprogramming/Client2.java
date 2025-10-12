package com.dsaproblems.DSAProblems.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Client2 {

	protected static class Employee {
		public final String name;
		public final Integer age;
		public final String jobTitle;
		public final Float salary;

		public Employee(String name, Integer age, String jobTitle, Float salary) {
			super();
			this.name = name;
			this.age = age;
			this.jobTitle = jobTitle;
			this.salary = salary;
		}
	}

	public static void main(String[] args) {
		Employee[] employeesArr = { new Employee("John", 34, "developer", 80000f),
				new Employee("Hannah", 24, "developer", 95000f), new Employee("Bart", 50, "sales executive", 100000f),
				new Employee("Sophie", 49, "construction worker", 40000f), new Employee("Darren", 38, "writer", 50000f),
				new Employee("Nancy", 29, "developer", 75000f), };

		List<Employee> employees = new ArrayList<>(Arrays.asList(employeesArr));

		Float totalDeveloperSalaries = employees.stream().filter(emp -> emp.jobTitle == "developer")
				.map(emp -> emp.salary).reduce(0f, (acc, x) -> acc + x);
		System.out.println(totalDeveloperSalaries);

		Long numberOfDevelopers = employees.stream().filter(emp -> emp.jobTitle == "developer")
				.collect(Collectors.counting());
		System.out.println(numberOfDevelopers);

		Map<String, Float> averageSalariesMap = employees.stream()
				.collect(Collectors.groupingBy((employee) -> employee.jobTitle)).entrySet().stream()
				.collect(Collectors.toMap((entry) -> entry.getKey(), (entry) -> entry.getValue().stream()
						.map((employee) -> employee.salary).reduce(0f, (acc, x) -> acc + x) / entry.getValue().size()));

		System.out.println(averageSalariesMap);
	}
}
