package com.dsaproblems.DSAProblems.functionalinterfaces;

import java.util.function.Function;
import java.util.function.Predicate;

public class EmployeeUtils {

	public String getFullName(Employee employee) {
		Function<Employee, String> fullName = (e) -> {
			return e.getFirstName() + " " + e.getLastName();
		};
		return fullName.apply(employee);
	}

	public String getManagersLastName(Employee employee) {
		Function<Employee, String> managersLastName = (e) -> {
			return e.getManager().getLastName();
		};
		return managersLastName.apply(employee);
	}

	public boolean hasBeenEmployedLongerThanFiveYears(Employee employee) {
		Predicate<Employee> employedLongerThanFiveYears = (e) -> {
			return e.getYearsOfService() > 5;
		};
		return employedLongerThanFiveYears.test(employee);
	}

	public boolean hasMoreThanThreeDirectReports(Employee employee) {
		Predicate<Employee> moreThanThreeDirectReports = (e) -> {
			return e.getNumberOfDirectReports() > 3;
		};
		return moreThanThreeDirectReports.test(employee);
	}

	public boolean hasMoreThanThreeYearsOfServiceAndLessThanTwoDirectReports(Employee employee) {
		Predicate<Employee> moreThanThreeYearsOfServiceAndLessThanTwoDirectReports = (e) -> {
			return e.getYearsOfService() > 3 && e.getNumberOfDirectReports() < 2;
		};
		return moreThanThreeYearsOfServiceAndLessThanTwoDirectReports.test(employee);
	}
}
