package io.github.kuyer.jbase;

public class Test1 {
	
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private void test21() {
		int i = 2;
		test22(i);
		System.out.println(i);
	}
	
	private void test22(int index) {
		index = 3;
	}
	
	private void test23() {
		Test1 t1 = new Test1();
		t1.setId(1);
		test24(t1);
		System.out.println(t1.getId());
	}
	
	private void test24(Test1 test1) {
		test1.setId(2);
	}
	
	private void test25() {
		Integer i = new Integer(1);
		test26(i);
		System.out.println(i.intValue());
	}
	
	private void test26(Integer i) {
		i = 45;
	}

	@Override
	public String toString() {
		return "Test1 [id=" + id + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(4|5);
		new Test1().test25();
	}

}
