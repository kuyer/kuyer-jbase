package io.github.kuyer.jbase.sort.geo;

import java.util.BitSet;
import java.util.HashMap;

/** 
 * geohash算法原理：将一个经纬度信息，转换成一个可以排序，可以比较的字符串编码
 * 首先将纬度范围(-90, 90)平分成两个区间(-90,0)、(0, 90)，如果目标纬度位于前一个区间，则编码为0，否则编码为1。
 * 由于39.92324属于(0, 90)，所以取编码为1。然后再将(0, 90)分成 (0, 45), (45, 90)两个区间，而39.92324位于(0, 45)，所以编码为0。
 * 以此类推，直到精度符合要求为止，得到纬度编码为1011 1000 1100 0111 1001。
 * 参考网址：http://www.cnblogs.com/dengxinglin/archive/2012/12/14/2817761.html
 * geohash特点：
 * 首先，geohash用一个字符串表示经度和纬度两个坐标。某些情况下无法在两列上同时应用索引 （例如MySQL 4之前的版本，Google App Engine的数据层等），利用geohash，只需在一列上应用索引即可。
 * 其次，geohash表示的并不是一个点，而是一个矩形区域。比如编码wx4g0ec19，它表示的是一个矩形区域。 使用者可以发布地址编码，既能表明自己位于北海公园附近，又不至于暴露自己的精确坐标，有助于隐私保护。
 * 第三，编码的前缀可以表示更大的区域。例如wx4g0ec1，它的前缀wx4g0e表示包含编码wx4g0ec1在内的更大范围。 这个特性可以用于附近地点搜索。首先根据用户当前坐标计算geohash（例如wx4g0ec1）然后取其前缀进行查询 （SELECT * FROM place WHERE geohash LIKE 'wx4g0e%'），即可查询附近的所有地点。
 * Geohash比直接用经纬度的高效很多。
 * @author rory.zhang
 */
public class GeoHash {

	private static int numbits = 6 * 5;
	
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();
	
	static {
		int i = 0;
		for (char c : digits) {
			lookup.put(c, i++);
		}
	}

	public double[] decode(String geohash) {
		StringBuilder buffer = new StringBuilder();
		for (char c : geohash.toCharArray()) {
			int i = lookup.get(c) + 32;
			buffer.append(Integer.toString(i, 2).substring(1));
		}
		BitSet lonset = new BitSet();
		BitSet latset = new BitSet();
		//even bits
		int j =0;
		for (int i=0; i< numbits*2;i+=2) {
			boolean isSet = false;
			if (i < buffer.length()) {
				isSet = buffer.charAt(i) == '1';
			}
			lonset.set(j++, isSet);
		}
		//odd bits
		j=0;
		for (int i=1; i< numbits*2;i+=2) {
			boolean isSet = false;
			if (i < buffer.length()) {
				isSet = buffer.charAt(i) == '1';
			}
			latset.set(j++, isSet);
		}
		double lon = decode(lonset, -180, 180);
		double lat = decode(latset, -90, 90);
		return new double[] {lat, lon};
	}

	private double decode(BitSet bs, double floor, double ceiling) {
		double mid = 0;
		for (int i=0; i<bs.length(); i++) {
			mid = (floor + ceiling) / 2;
			if (bs.get(i)) {
				floor = mid;
			} else {
				ceiling = mid;
			}
		}
		return mid;
	}

	public String encode(double lat, double lon) {
		BitSet latbits = getBits(lat, -90, 90);
		BitSet lonbits = getBits(lon, -180, 180);
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < numbits; i++) {
			buffer.append( (lonbits.get(i))?'1':'0');
			buffer.append( (latbits.get(i))?'1':'0');
		}
		return base32(Long.parseLong(buffer.toString(), 2));
	}

	private BitSet getBits(double lat, double floor, double ceiling) {
		BitSet buffer = new BitSet(numbits);
		for (int i = 0; i < numbits; i++) {
			double mid = (floor + ceiling) / 2;
			if (lat >= mid) {
				buffer.set(i);
				floor = mid;
			} else {
				ceiling = mid;
			}
		}
		return buffer;
	}

    private static String base32(long i) {
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (i < 0);
		if (!negative) {
			i = -i;
		}
		while (i <= -32) {
			buf[charPos--] = digits[(int) (-(i % 32))];
			i /= 32;
		}
		buf[charPos] = digits[(int) (-i)];
		if (negative) {
			buf[--charPos] = '-';
		}
		return new String(buf, charPos, (65 - charPos));
	}
    
    public static void main(String[] args)  throws Exception {
		System.out.println(new GeoHash().encode(45, 125));
	}

}
