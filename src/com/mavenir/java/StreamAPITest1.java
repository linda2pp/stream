package com.mavenir.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/*
 * 测试Stream的中间操作
 */

public class StreamAPITest1 {
	//1 . 筛选与切片
	@Test
	public void test1() {
		List<Employee> list = EmployeeData.getEmployees();
		//filter(predicate p)  -- 接收Lambda，从流中排除某些元素
		Stream<Employee> stream = list.stream();
		//练习：查询员工中薪资大于6000的员工信息
		stream.filter(e -> e.getSalary() > 6000.0).forEach(System.out::println);
		System.out.println();
		//limit(n) --截断流，使其元素不超过给定数量
		list.stream().limit(3).forEach(System.out::println);
		System.out.println();
		//skip(n) --跳过元素，返回一个扔掉前n个元素的流。若流中元素补足n个，则返回一个空流。与limit(n)互不
		list.stream().skip(3).forEach(System.out::println);
		//distinct() --筛选，通过流所生成元素的hashCode和equals去除重复元素
		System.out.println();
		list.add(new Employee(1010, "dong", 40, 8000));
		list.add(new Employee(1010, "dong", 40, 8000));
		list.add(new Employee(1010, "dong", 40, 8000));
		list.add(new Employee(1010, "dong", 40, 8000));
		list.stream().distinct().forEach(System.out::println);
		
	}
	
	//2. 映射
	@Test
	public void test2() {
		//map(Functin f) -- 接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
		List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
		list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
		System.out.println();
		//练习：获取员工姓名长度大于3的员工的姓名
		List<Employee> employees = EmployeeData.getEmployees();
		Stream<String> namesStream = employees.stream().map(Employee::getName);
		namesStream.filter(name -> name.length() > 3).forEach(System.out::println);
		
		System.out.println();
		//练习2：
		Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
		streamStream.forEach(s ->{
			s.forEach(System.out::println);
		});
		System.out.println();
		//flatMap(Function f):接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
		Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
		characterStream.forEach(System.out::println);
	}
	//将字符串总的多个字符构成的集合转换成对应的Stream的实例
	public static Stream<Character> fromStringToStream(String str) {
		ArrayList<Character> list = new ArrayList<>();
		for (Character c :str.toCharArray()) {
			list.add(c);
		}
		return list.stream();
	}
	
	//3. 排序
	@Test
	public void test4() {
		//sorted() 自然排序(从小到大排序)
		List<Integer> list = Arrays.asList(12, 32, 43, 87, 34, -87, -98, 0);
		list.stream().sorted().forEach(System.out::println);
		System.out.println("====");
		//抛异常，原因Employee没有实现Comparable接口
//		List<Employee> employees = EmployeeData.getEmployees();
//		employees.stream().sorted().forEach(System.out::println);
		
		//sorted(Comparator cmp) --定制排序
		List<Employee> employees = EmployeeData.getEmployees();
		//employees.stream().sorted((e1,e2)-> Integer.compare(e1.getAge(), e2.getAge())).forEach(System.out::println);
		employees.stream().sorted((e1,e2) -> {
			int ageValue = Integer.compare(e1.getAge(), e2.getAge());
			if (ageValue != 0) {
				return ageValue;
			} else {
				return Double.compare(e1.getSalary(), e2.getSalary());
			}
		}).forEach(System.out::println);
	}
}
