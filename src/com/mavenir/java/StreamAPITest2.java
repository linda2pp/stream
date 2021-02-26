package com.mavenir.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/*
 * 测试Stream终止操作
 * 
 */
public class StreamAPITest2 {
	//1. 匹配与查找
	@Test
	public void test1() {
		var employees = EmployeeData.getEmployees();
		//allMatch(Predicate p) -- 检查是否匹配所有元素
		//练习：是否所有的员工的年龄都大于18
		boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
		System.out.println(allMatch);
		//anyMatch(Predicate p) -- 检查是否至少匹配一个元素
		//练习：是否存在员工的工资大于10000
		boolean anyMatch1 = employees.stream().anyMatch(e -> e.getSalary() > 10000.0);
		System.out.println(anyMatch1);
		//nonMatch(Predicate p) --检查是否没有匹配的元素
		//练习：是否存在员工姓雷
		boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
		System.out.println(noneMatch);
		//findFirst --返回第一个元素
		Optional<Employee> employee = employees.stream().findFirst();
		System.out.println(employee);
		//findAny --返回当前流中任意的元素
		Optional<Employee> employee1 = employees.stream().findAny();
		System.out.println(employee1);
		Optional<Employee> employee2 = employees.parallelStream().findAny();
		System.out.println(employee2);
	}
	@Test
	public void test2() {
		List<Employee> employees = EmployeeData.getEmployees();
		//count --返回流中元素的个数
		long count = employees.stream().filter(e -> e.getSalary() > 6000).count();
		System.out.println(count);
		//max(Comparator c) -- 返回流中最大值
		//练习：返回最高的工资：
		Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
		Optional<Double> maxSalary = salaryStream.max(Double::compare);
		System.out.println(maxSalary);
		//min(Comparator c) -- 返回流中最小值
		//练习：返回最底的工资的员工：
		Optional<Employee> minSalary = employees.stream().min((e1,e2)->Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(minSalary);
		//forEach(Consume c) --内部迭代
		employees.stream().forEach(System.out::println); 
		employees.forEach(System.out::println);//集合中的一个遍历方法
	}
	//2 - 归约
	@Test
	public void test3() {
		//reduce(T identity, BinaryOperator) -可以将流中元素反复结合起来，得到一个值。返回T
		//练习：计算1-10的自然数的和
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer sum = list.stream().reduce(0, Integer::sum);
		System.out.println(sum);
		//reduce(BinaryOperator) -可以将流中元素反复结合起来，得到一个值。返回Optional<T>
		//练习：计算公司所有员工工资的总和
		List<Employee> employees = EmployeeData.getEmployees();
		//Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
		Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
		Optional<Double> sumMoney = salaryStream.reduce(Double::sum);
		//Optional<Double> sumMoney = salaryStream.reduce((d1,d2)-> d1+d2);
		System.out.println(sumMoney);
	}
	//3. 收集
	@Test
	public void test4() {
		//collect(Collector c) --将流装换为其他形式。接收一个Collector接口的实现，用于给Stream
		//练习1：查找工资大于6000的员工，结果返回给一个List或Set
		var employees = EmployeeData.getEmployees();
		List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() >6000).collect(Collectors.toList());
		employeeList.forEach(System.out::println);
		System.out.println("====");
		Set<Employee> emplyeeSet = employees.stream().filter(e -> e.getSalary() >6000).collect(Collectors.toSet());
		emplyeeSet.forEach(System.out::println);
		System.out.println("====");
		ArrayList<Employee> employeeCollect = employees.stream().filter(e -> e.getSalary() >6000).collect(Collectors.toCollection(ArrayList::new));
		employeeCollect.forEach(System.out::println);
	}
}
