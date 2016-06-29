package io.github.kuyer.jbase.lang.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

public class CGLibDemo {

	public static void main(String[] args) throws Exception {
		CGLibBean bean = new CGLibBean();
		FastClass fclass = FastClass.create(CGLibBean.class);
		FastMethod fmethod = fclass.getMethod("show", new Class[] {String.class});
		String result = (String) fmethod.invoke(bean, new String[] {"rory"});
		System.out.println("result: "+result);
		
		System.out.println("######################################");
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CGLibBean.class);
		enhancer.setCallback(new CGLibCallback());
		CGLibBean proxy = (CGLibBean) enhancer.create();
		System.out.println("result: "+proxy.show("coolrl"));
	}

}
