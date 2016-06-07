package io.github.kuyer.jbase.lang;

public class FinalizeGC {
	
	private String name;
	public static FinalizeGC finalizeGC;
	
	public FinalizeGC(String name) {
		this.name = name;
	}
	
	public void isAlive() {
		System.out.println("yes, I am still alive.");
	}
	
	/** finalize() 只在第一次GC执行 **/
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed start.");
		System.out.println(this);
		System.out.println("finalize method executed finish.");
		FinalizeGC.finalizeGC = this;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static void main(String[] args) throws Exception {
		finalizeGC = new FinalizeGC("rory");
		System.out.println(finalizeGC);
		finalizeGC = null;
		System.out.println(finalizeGC);
		
		System.gc();
		Thread.sleep(500);
		if(finalizeGC != null) {
			finalizeGC.isAlive();
		} else {
			System.out.println("fc is dead.");
		}
		
		finalizeGC = null;
		System.gc();
		
		Thread.sleep(500);
		if(finalizeGC != null) {
			finalizeGC.isAlive();
		} else {
			System.out.println("fc is dead.");
		}
	}

}
