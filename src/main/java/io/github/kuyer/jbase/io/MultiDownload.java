package io.github.kuyer.jbase.io;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MultiDownload {
	
	public void download(String url, String path, int threads) throws Exception {
		URL hurl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) hurl.openConnection();
//		connection.setRequestMethod("GET");
		connection.setReadTimeout(5000);
		
		Executor executor = Executors.newFixedThreadPool(threads);
		
		int count = connection.getContentLength();//获取文件大小
		int block = count/threads;
		for(int i=0; i<threads; i++) {
			long start = i*block;
			long end = (i+1)*block - 1;
			if(i == threads-1) {
				end = count;
			}
			executor.execute(new MultiDownloadTask(url, path, start, end));
		}
	}
	
	class MultiDownloadTask implements Runnable {
		
		private String url;
		private String path;
		private long start;
		private long end;

		public MultiDownloadTask(String url, String path, long start, long end) {
			this.url = url;
			this.path = path;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			try {
				System.out.println("start down start: "+start+"; end: "+end);
				URL hurl = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) hurl.openConnection();
//				connection.setRequestMethod("get");
				connection.setReadTimeout(5000);
				connection.setRequestProperty("Range", "bytes="+start+"-"+end);
				RandomAccessFile raf = new RandomAccessFile(new File(path), "rw");
				raf.seek(start);
				InputStream is = connection.getInputStream();
				byte[] bytes = new byte[4*1024];
				int len;
				while((len=is.read(bytes)) != -1) {
					raf.write(bytes, 0, len);
				}
				if(null != raf) {
					raf.close();
				}
				if(null != is) {
					is.close();
				}
				System.out.println("end down start: "+start+"; end: "+end);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		//String path = "d:\\spark-1.6.1-bin-hadoop2.6.tgz";
		//String link = "http://mirrors.tuna.tsinghua.edu.cn/apache/spark/spark-1.6.1/spark-1.6.1-bin-hadoop2.6.tgz";
		//String link = "http://nginx.org/download/nginx-1.11.1.zip";
		new MultiDownload().download("http://nginx.org/download/nginx-1.11.1.zip", 
				"d:\\nginx-1.11.1.zip", 3);
	}
	
}
