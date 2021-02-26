package com.mavenir.java;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/*
 * 1. Stream 关注的是数据的运算，与CPU打交道
 *      集合关注的是数据的存储，与内存打交道
 *      
 *  2. 
 *    - stream自己不会存储元素
 *	  - stream不会改变源对象。相反，他们会返回一个持有结果的新stream
 *    - stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *    
 *  3. Stream执行流程
 *    - Stream的实例化
 *    - 一些列的中间操作（过滤，映射...）
 *    - 终止操作
 *    
 *  4. 说明
 *     - 一个中间操作链，对数据源的数据进行处理
 *     - 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 */
public class StreamAPITest {
	//创建Stream方式一：通过集合
	@Test
	public void test1() {
		List<Employee> employees = EmployeeData.getEmployees();
		// default Stream<E> stream(): 返回一个顺序流
		Stream<Employee> stream = employees.stream();
		// default Stram<E> parallelStream(): 返回一个并行流
		Stream<Employee> parallelStram = employees.parallelStream();
	}
	//创建Stream方式二：通过数组
	@Test
	public void test2() {
		int[] arr = new int[] {1,2,3,4,5};
		//调用Arrays类的
		IntStream stream = Arrays.stream(arr);
		
		Employee e1 = new Employee(1001, "Tom", 22, 3442.3);
		Employee e2 = new Employee(1001, "Peter", 24, 3432.3);
		Employee[] arr1 = new Employee[] {e1, e2};
		Stream<Employee> stream1 = Arrays.stream(arr1);
		
	}
	//创建Stream方式三：通过Stream的of()
	@Test
	public void test3() {
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
	}
	//创建Stream方式四：创建无限流
	@Test
	public void test4() {
		//迭代
		//public static<T> Stream<T> interate(final T seed, final UnaryOperator<T> f)
		Stream.iterate(0, t-> t+2).limit(10).forEach(System.out::println);
		//生成
		//public static<T> Stream<T> generate(Supplier<? extends T> s)
		Stream.generate(Math::random).limit(10).forEach(System.out::println);
	}	
}
