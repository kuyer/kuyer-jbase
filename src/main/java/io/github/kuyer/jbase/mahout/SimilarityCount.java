package io.github.kuyer.jbase.mahout;

import java.io.File;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;

/**
 * url: https://my.oschina.net/penngo/blog/807810
 */
public class SimilarityCount {
	
	private static SparkSession spark;
	private static String splitTag = "@==@";

	private static SparkSession initSpark() {
		if(null == spark) {
			spark = SparkSession.builder().appName("SimilarityCount")
					.master("local[3]").getOrCreate();
		}
		return spark;
	}
	
	private static Dataset<Row> readText(String dataPath) {
		// TODO Auto-generated method stub
		JavaRDD<TfidfData> dataRDD = spark.read().textFile(dataPath).javaRDD().map(new Function<String, TfidfData>() {
			private static final long serialVersionUID = 1069755638415839232L;
			private ISegment seg = null;
			private void initSegment() throws Exception {
				if(null == seg) {
					JcsegTaskConfig config = new JcsegTaskConfig();
					config.setLoadCJKPos(true);
					String path = new File("").getAbsolutePath()+"/data/data-2";
					System.out.println(new File("").getAbsolutePath());
					ADictionary dic = DictionaryFactory.createDefaultDictionary(config);
					dic.loadDirectory(path);
					seg = SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE, config, dic);
				}
			}
			@Override
			public TfidfData call(String line) throws Exception {
				initSegment();
				TfidfData data = new TfidfData();
				String[] lines = line.split(splitTag);
				if(lines.length<5) {
					System.out.println("error="+lines[0] + " " + lines[1]);
				}
				//TODO
				return data;
			}});
		Dataset<Row> dataset = spark.createDataFrame(dataRDD, TfidfData.class);
		return dataset;
	}

	private static void run() throws Exception {
		initSpark();
		String dataPath = new File("").getAbsolutePath()+"/data/data-1.txt";
		Dataset<Row> dataset = readText(dataPath);
		dataset.show();
		//TODO
	}
	
	public static void main(String[] args) throws Exception {
		//windows上运行
		System.setProperty("hadoop.home.dir", "d:/develop/hadoop-2.7.2");
		System.setProperty("HADOOP_USER_NAME", "root");
		run();
	}

}
