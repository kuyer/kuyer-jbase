package io.github.kuyer.jbase.spark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class SparkWordCount {
	
	private static final Pattern SPACE = Pattern.compile(" ");

	public static void main(String[] args) {
		/**
		if(args.length < 1) {
			System.err.println("Usage: SparkWordCount <file>");
			System.exit(1);
		} **/
		String filePath = "hdfs://192.168.101:9000/user/root/input";
		SparkSession spark = SparkSession.builder().appName("SparkWordCount").getOrCreate();
		JavaRDD<String> lines = spark.read().textFile(filePath).javaRDD();
		JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
			private static final long serialVersionUID = 6877322804336208973L;
			@Override
			public Iterator<String> call(String t) throws Exception {
				return Arrays.asList(SPACE.split(t)).iterator();
			}});
		JavaPairRDD<String, Integer> ones = words.mapToPair(new PairFunction<String, String, Integer>() {
			private static final long serialVersionUID = -499501130079215045L;
			@Override
			public Tuple2<String, Integer> call(String t) throws Exception {
				return new Tuple2<>(t, 1);
			}});
		JavaPairRDD<String, Integer> counts = ones.reduceByKey(new Function2<Integer, Integer, Integer> () {
			private static final long serialVersionUID = -2714199847700337000L;
			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1+v2;
			}});
		List<Tuple2<String, Integer>> output = counts.collect();
		for(Tuple2<String, Integer> tuple : output) {
			System.out.println(tuple._1+": "+tuple._2);
		}
		spark.stop();
	}

}
