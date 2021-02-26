package com.mavenir.java;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
	public static List<Employee> getEmployees() {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee(1001, "Tom", 34, 7000.2));
		list.add(new Employee(1002, "Jake",33, 6010.2));
		list.add(new Employee(1003, "Peter", 22, 5020.3));
		list.add(new Employee(1004, "Marry", 11, 8004.5));
		list.add(new Employee(1005, "Jerry", 24, 5000.2));
		list.add(new Employee(1006, "雷军", 38, 5004.3));
		list.add(new Employee(1007, "Nike", 44, 6100.7));
		return list;
	}
}
