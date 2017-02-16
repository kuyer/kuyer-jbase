package io.github.kuyer.jbase.lang.proxyx;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

public class CglibProxy {
	
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T t) {
		Enhancer enhancer = new Enhancer();
		enhancer.setClassLoader(t.getClass().getClassLoader());
		enhancer.setSuperclass(t.getClass());
		enhancer.setCallback(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				before();
				Object obj = null;
				try {
					obj = method.invoke(t, args);
				} catch (Exception e) {
					exception(e);
				}
				after();
				return obj;
			}});
		return (T) enhancer.create();
	}
	
	private static void before() {
		System.out.println("jdk proxy before.");
	}
	
	private static void exception(Exception e) {
		System.out.println("jdk proxy exception. "+e.getMessage());
	}
	
	private static void after() {
		System.out.println("jdk proxy after.");
	}

	public static void main(String[] args) {
		IProxy proxy1 = new ProxyImpl();
		IProxy proxy2 = createProxy(proxy1);
		System.out.println(proxy2.say("cglibproxy1"));
		
		ProxyImpl proxy3 = new ProxyImpl();
		ProxyImpl proxy4 = createProxy(proxy3);
		System.out.println(proxy4.say("cglibproxy2"));
	}

}
