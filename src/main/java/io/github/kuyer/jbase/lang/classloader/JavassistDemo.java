package io.github.kuyer.jbase.lang.classloader;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistDemo {

	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("io.github.kuyer.jbase.MyBaseTest");
		CtMethod cm = CtNewMethod.make("public void hello() {}", cc);
		cm.insertBefore("System.out.println(\"hello, base ...\");");
		cc.addMethod(cm);
		cc.writeFile("d:\\temp");
	}

}
