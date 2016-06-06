package io.github.kuyer.jbase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadFutureTask {
	
	public static void main(String[] args) throws Exception {
		FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "hello, future & callable!";
			}});
		new Thread(ft).start();
		String result = ft.get();
		System.out.println("result: "+result);
	}

}
