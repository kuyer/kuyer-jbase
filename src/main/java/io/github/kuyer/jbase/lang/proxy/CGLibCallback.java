package io.github.kuyer.jbase.lang.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibCallback implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("start cglib.");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("end cglib.");
		return result;
	}

}
