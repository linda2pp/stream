package com.mavenir.java1;

import java.util.Optional;

import org.junit.Test;

/*
 * Optional类：为了在程序中避免出现空指针异常而创建的
 * 常用的方法：
 * of(T t)
 * orElse(T t)
 */
public class OptionalTest {
	@Test
	public void test1() {
		Girl girl = new Girl();
		//of(T t)保证t非空
		Optional<Girl> opGirl = Optional.of(girl);
		System.out.println(opGirl);
	}
	@Test
	public void test2() {
//		Girl girl = new Girl();
		Girl girl = null;
		//ofNullable(T t): t可以为null
		Optional<Girl> opGirl = Optional.ofNullable(girl);
		Girl orElse = opGirl.orElse(new Girl("赵丽颖"));
		System.out.println(orElse);
	}
	public String getGirlName(Boy boy) {
		return boy.getGirl().getName();
	}
	//使用optional的getGirlName
	public String getGirlName1(Boy boy) {
		Optional<Boy> boyOptional = Optional.ofNullable(boy);
		//此时boy1一定非空
		Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽巴热")));
		Girl girl = boy1.getGirl();
		
		Optional<Girl> girlOptional = Optional.ofNullable(girl);
		//girl1一定非空
		Girl girl1 = girlOptional.orElse(new Girl("古力娜扎"));
		
		return girl1.getName();
	}
	@Test
	public void test3() {
		Boy boy = new Boy();
		String girlName = getGirlName(boy);
		System.out.println(girlName);
	}
	@Test
	public void test4() {
		Boy boy = new Boy();
		String girlName = getGirlName1(boy);
		System.out.println(girlName);
	}
	@Test
	public void test5() {
		Boy boy = null;
		boy = new Boy();
		boy = new Boy(new Girl("苍老师"));
		String girlName = getGirlName1(boy);
		System.out.println(girlName);
	}
}
