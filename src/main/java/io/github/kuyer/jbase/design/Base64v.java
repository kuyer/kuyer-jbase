package io.github.kuyer.jbase.design;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Base64v {
	
	private static final String BASE64_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	public static String encode(String str) {
		StringBuilder bincodes = new StringBuilder();
		char[] cs = str.toCharArray();
		for(char c : cs) {
			int num = c;
			// 将字符转换为二进制代码
			String ascii = Integer.toBinaryString(num);
			// 二进制代码不足8位，在高位补0
			while(ascii.length()<8) {
				ascii = "0"+ascii;
			}
			System.out.println(c+":"+num+":"+ascii);
			bincodes.append(ascii);
		}
		// 二进制不能被6整除，则在低位补0
		while(bincodes.length()%6 != 0) {
			bincodes.append("0");
		}
		String bincode = bincodes.toString();
		System.out.println("ascii bincode: "+bincode);
		// 按6个一组拆分成字符串数组
		List<String> bclist = new ArrayList<>();
		while(bincode.length()/6 > 0) {
			String bc = bincode.substring(0, 6);
			bclist.add(bc);
			bincode = bincode.substring(6);
		}
		// 确定最终补位长度
		int overlen = 0;
		if(str.length()%3 != 0) {
			overlen = 3 - str.length()%3;
		}
		// 设定存放最终编码的容器
		char[] code = new char[bclist.size()+overlen];
		for(int i=0; i<bclist.size(); i++) {
			//将二进制转换成十进制
			int index = Integer.parseInt(bclist.get(i), 2);
			code[i] = BASE64_CODE.charAt(index);
		}
		switch(overlen) {
		case 2:code[code.length-2] = '=';
		case 1:code[code.length-1] = '=';
		default:
		}
		return String.valueOf(code);
	}
	
	public static String decode(String str) {
		int counter = 0;
		if(str.endsWith("==")) {
			counter = 2;
		} else if(str.endsWith("=")) {
			counter = 1;
		}
		System.out.println("counter: "+counter);
		str = str.replaceAll("=", "");
		System.out.println("replace: "+str);
		char[] cs = str.toCharArray();
		StringBuilder binsb = new StringBuilder();
		for(int i=0; i<cs.length; i++) {
			int index = BASE64_CODE.indexOf(cs[i]);
			String bincode = Integer.toBinaryString(index);
			while(bincode.length()<6) {
				bincode = "0"+bincode;
			}
			binsb.append(bincode);
		}
		String bincodes = binsb.toString();
		System.out.println("bincodes1: "+bincodes);
		// 如果二进制字符后有多补的0，则去除
		if(counter == 1) {
			bincodes = bincodes.substring(0, bincodes.length()-2);
		} else if(counter ==2) {
			bincodes = bincodes.substring(0, bincodes.length()-4);
		}
		System.out.println("bincodes2: "+bincodes);
		// 按8个一组拆分字符串数组
		List<String> bclist = new ArrayList<>();
		while(bincodes.length()/8 > 0) {
			String bincode = bincodes.substring(0, 8);
			bclist.add(bincode);
			bincodes = bincodes.substring(8);
		}
		// 将二进制转换成十进制，再转换成char类型
		char[] ascs = new char[bclist.size()];
		for(int i=0; i<ascs.length; i++) {
			String bincode = bclist.get(i);
			char asc = (char) Integer.parseInt(bincode, 2);
			ascs[i] = asc;
			System.out.println(asc+":"+bincode);
		}
		return new String(ascs);
	}
	
	public static void main(String[] args) {
		String str = "hellox김희선";
		String encode = encode(str);
		String decode = decode(encode);
		System.out.println("encode: "+encode);
		System.out.println("decode: "+decode);
		
		String encoder = Base64.getEncoder().encodeToString(str.getBytes());
		System.out.println("encoder: "+encoder);
		String decoder = new String(Base64.getDecoder().decode(encoder));
		System.out.println("decoder: "+decoder);
	}

}
