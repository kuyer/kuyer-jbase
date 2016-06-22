package io.github.kuyer.jbase.io;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;

public class MultiDownloadThread extends Thread {
	
	private URL url;
	private String path;
	private long startIndex;
	private long endIndex;

	public MultiDownloadThread(URL url, String path, long startIndex,
			long endIndex) {
		this.url = url;
		this.path = path;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public void run() {
		InputStream is = null;
		RandomAccessFile rfile = null;
		try {
			is = url.openStream();
			rfile = new RandomAccessFile(path, "rw");
			
			is.skip(startIndex);
			rfile.seek(startIndex);
			
			int bufSize = 32;
			byte[] buf = new byte[bufSize];
			long contentLength = endIndex - startIndex;
			long times = contentLength/bufSize + 4;
			int hasRead = 0;
			for(int i=0; i<times; i++) {
				hasRead = is.read(buf);
				if(hasRead<0) {
					break;
				}
				rfile.write(buf, 0, hasRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rfile.close();
				is.close();
			} catch (Exception ex) {}
		}
	}
	
	

}
