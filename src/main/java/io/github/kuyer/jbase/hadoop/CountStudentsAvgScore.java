package io.github.kuyer.jbase.hadoop;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 计算所有学生的平均分，运行方式： <br/>
 * 1、打包程序为：student-score.jar <br/>
 * 2、上传到Hadoop环境中 <br/>
 * 3、yarn jar student-score.jar io.github.kuyer.jbase.hadoop.CountStudentsAvgScore /tmp/students/score/input/students-score.txt /tmp/students/score/output/students-avg-score
 * @author Rory.Zhang
 */
public class CountStudentsAvgScore {
	
	public static class CountStudentsAvgScoreMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			// 每行的气象数据
			String line = value.toString();
			if(StringUtils.isNotBlank(line)) {
				int data = -1;
				try {
					data = Integer.parseInt(line.split(",")[1]);
				} catch (Exception e) {}
				if(data>=0) {
					FileSplit fs = (FileSplit) context.getInputSplit();
					String name = fs.getPath().getName().split(",")[0];
					context.write(new Text(name), new IntWritable(data));
				}
			}
		}
		
	}
	
	public static class CountStudentsAvgScoreReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			IntWritable result = new IntWritable();
			int sum = 0;
			int count = 0;
			for(IntWritable value : values) {
				sum += value.get();
				count ++;
			}
			result.set(sum/count);
			context.write(key, result);
		}
		
	}
	
	public static int countStudentsAvgScore(String input, String output) throws Exception {
		Configuration conf = new Configuration();
		
		Path outPath = new Path(output);
		FileSystem hdfs = outPath.getFileSystem(conf);
		if(hdfs.isDirectory(outPath)) {
			hdfs.delete(outPath, true);
		}
		
		// 添加一个任务
		Job job = Job.getInstance(conf, "weather");
		// 设置主类
		job.setJarByClass(CountStudentsAvgScore.class);
		// 输入路径
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		
		// Mapper
		job.setMapperClass(CountStudentsAvgScoreMapper.class);
		// Reducer
		job.setReducerClass(CountStudentsAvgScoreReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		boolean flag = job.waitForCompletion(true);
		if(flag) {
			return 0;
		}
		return 1;
	}
	
	public static void main(String[] args) throws Exception {
		String input = null;
		String output = null;
		try {
			input = args[0].trim();
			output = args[1].trim();
		} catch (Exception e) {}
		if(null == input) {
			input = "hdfs://hadoop.kuyer.github.io:9000/tmp/students/score/input/students-score.txt";
		}
		if(null == output) {
			output = "hdfs://hadoop.kuyer.github.io:9000/tmp/students/score/output/students-avg-score";
		}
		int status = countStudentsAvgScore(input, output);
		System.exit(status);
	}

}
