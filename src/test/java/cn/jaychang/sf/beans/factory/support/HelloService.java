package cn.jaychang.sf.beans.factory.support;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public class HelloService {

	public String sayHello() {
		System.out.println("hello");
		return "hello";
	}
}