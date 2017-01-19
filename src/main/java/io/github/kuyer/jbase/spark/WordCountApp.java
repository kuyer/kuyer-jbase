package io.github.kuyer.jbase.spark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class WordCountApp {
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.setMaster("local[4]");
		conf.setAppName("WordCountApp");
		
		JavaSparkContext context = new JavaSparkContext(conf);
		JavaRDD<String> lines = context.textFile("D:/words.txt");
		lines.flatMap(new FlatMapFunction<String, String>() {
			
			private static final long serialVersionUID = -29120035843639853L;
			
			@Override
			public Iterator<String> call(String str) throws Exception {
				return Arrays.asList(str.split(" ")).iterator();
			}});
		JavaPairRDD<String, Integer> words = lines.mapToPair(new PairFunction<String, String, Integer>() {

			private static final long serialVersionUID = 4097967453370045050L;

			@Override
			public Tuple2<String, Integer> call(String word) throws Exception {
				return new Tuple2<>(word, 1);
			}}).reduceByKey(new Function2<Integer, Integer, Integer>() {

				private static final long serialVersionUID = -2734489902796430770L;

				@Override
				public Integer call(Integer x, Integer y) throws Exception {
					return x+y;
				}});
		System.out.println("result:");
		List<Tuple2<String, Integer>> output = words.collect();
		for(Tuple2<String, Integer> tuple : output) {
			System.out.println(tuple._1()+""+tuple._2());
		}
		words.saveAsTextFile("D:/output");
		context.stop();
		context.close();
	}

}
