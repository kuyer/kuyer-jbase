package io.github.kuyer.jbase.lang;

public class ObjectLoader {

	public static void main(String[] args) {
		System.out.println("--------- main start ---------");
		ObjectParent op = new ObjectParent();
		ObjectChild oc = new ObjectChild();
		op = new ObjectChild();
		System.out.println(op.hashCode());
		System.out.println(oc.hashCode());
		
		String str1 = "hello";
		String str2 = "hel"+new String("lo");
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
		System.out.println("--------- main end ---------");
		
		ObjectChild o = new ObjectChild("mark");
		ObjectParent p = o;
		System.out.println(o.name + " - " +p.name);
		System.out.println(o.getName() + " - " +p.getName());
	}

}
