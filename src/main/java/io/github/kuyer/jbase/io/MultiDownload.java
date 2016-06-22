package io.github.kuyer.jbase.io;

import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

public class MultiDownload {

	public static void main(String[] args) throws Exception {
		String path = "d:\\spark-1.6.1-bin-hadoop2.6.tgz";
		//String link = "http://mirrors.tuna.tsinghua.edu.cn/apache/spark/spark-1.6.1/spark-1.6.1-bin-hadoop2.6.tgz";
		String link = "http://nginx.org/download/nginx-1.11.1.zip";
		int threadNum = 20;
		
		URL url = new URL(link);
		long fileLength = getFileLength(url);
		System.out.println("file length: "+fileLength);
		
		RandomAccessFile rfile = new RandomAccessFile(path, "rw");
		rfile.write(0);
		rfile.close();
		
		long perSize = fileLength/threadNum;
		long leftSize = fileLength%threadNum;
		
		for(int i=0; i<threadNum; i++) {
			long startIndex = i * perSize;
			long endIndex = (i+1) * perSize;
			if(i == threadNum-1) {
				endIndex += leftSize;
			}
			new MultiDownloadThread(url, path, startIndex, endIndex).start();
		}
	}
	
	public static long getFileLength(URL url) throws Exception {
		URLConnection conn = url.openConnection();
		return conn.getContentLengthLong();
	}

}
