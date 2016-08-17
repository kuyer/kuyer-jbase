package io.github.kuyer.jbase.sort.geo;

/**
 * GEO算法
 * 余弦定理以及弧度计算方法：acos(cos($radLat1) * cos($radLat2) * cos($radLng1 - $radLng2) + sin($radLat1) * sin($radLat2)) * $R
 * Google距离计算方法：2*asin(sqrt(pow(sin(($radLat1-$radLat2)/2),2)+cos($radLat1)*cos($radLat2)*pow(sin(($radLng1-$radLng2)/2),2)))*$R
 * $radLat1、$radLng1，$radLat2，$radLng2 为弧度 $R 为地球半径
 * 参考网址：http://www.qixing318.com/article/geohash-scheme-based-on-function-of-lbs-application.html
 * 数据库操作：select id, 6378.137*2*ASIN(SQRT(POW(SIN((c_lat*PI()/180-#{lat}*PI()/180)/2),2)+COS(c_lat*PI()/180)*COS(#{lat}*PI()/180)* POW(SIN( (c_lon*PI()/180-#{lon}*PI()/180)/2),2))) as distance from mytable where distance<600 order by distance asc
 * @author rory.zhang
 */
public class GeoUtil {
	
	/**
	 * 获取两点之间的距离
	 * @param ulat
	 * @param ulon
	 * @param slat
	 * @param slon
	 * @return
	 */
	public static double getDistence(double ulat, double ulon, double slat, double slon) {
		return 6378.137 * 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin((slat * Math.PI / 180 - ulat * Math.PI / 180)/2), 2) 
				+ Math.cos(slat * Math.PI / 180) 
				* Math.cos(ulat * Math.PI / 180)
				* Math.pow(Math.sin((slon * Math.PI / 180 - ulon * Math.PI / 180)/2), 2)
			));
	}
	
	public static void main(String[] args) {
		double ulat = 31.194067d;
		double ulon = 121.323166d;
		double slat = 31.243466d;
		double slon = 121.491121d;
		System.out.println(getDistence(ulat, ulon, slat, slon));
	}

}
