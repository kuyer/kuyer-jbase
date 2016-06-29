package io.github.kuyer.jbase.lang.classloader;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmDemo {
	
	public static void main(String[] args) throws Exception {
		System.out.println();
		ClassWriter cw = new ClassWriter(0);
		cw.visit(Opcodes.V1_8, 
				Opcodes.ACC_PUBLIC, 
				"MyTest", 
				null, 
				"java/lang/Object", 
				null);
		
		MethodVisitor mv1 = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		mv1.visitCode();
		mv1.visitVarInsn(Opcodes.AALOAD, 0);
		mv1.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv1.visitInsn(Opcodes.RETURN);
		mv1.visitMaxs(1, 1);
		mv1.visitEnd();
		
		MethodVisitor mv2 = cw.visitMethod(Opcodes.ACC_PUBLIC, "sayHello", "()V", null, null);
		mv2.visitCode();
		mv2.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv2.visitLdcInsn("hello, mytest!");
		mv2.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/io/PrintSystem", "println", "(Ljava/lang/String;)V", false);
		mv2.visitInsn(Opcodes.RETURN);
		mv2.visitMaxs(2, 2);
		mv2.visitEnd();
		
		cw.visitEnd();
		
		byte[] datas = cw.toByteArray();
		File file = new File("D:\\MyTest.class");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(datas);
		fos.close();
	}

}
