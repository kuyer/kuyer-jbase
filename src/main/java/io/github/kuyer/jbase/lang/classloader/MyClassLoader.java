package io.github.kuyer.jbase.lang.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
	
	public Class<?> defineMyClass(String name, byte[] b, int off, int len) {
		return super.defineClass(name, b, off, len);
	}
	
	public static void main(String[] args) throws Exception {
		String path = "D:\\Work\\project\\kuyer-jbase\\target\\classes\\io\\github\\kuyer\\jbase\\Test.class";
		System.out.println(path);
		File file = new File(path);
		InputStream is = new FileInputStream(file.getCanonicalFile());
		
		byte[] bytes = new byte[1024];
		int count = is.read(bytes);
		MyClassLoader loader = new MyClassLoader();
		Class<?> clazz = loader.defineMyClass(null, bytes, 0, count);
		System.out.println(clazz.getCanonicalName());
		
		Object obj = clazz.newInstance();
		Object result = clazz.getMethod("sayHello", String.class).invoke(obj, "rory");
		System.out.println(result);
		
		is.close();
	}

}
