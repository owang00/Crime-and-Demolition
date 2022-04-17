import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.File;
import java.util.Scanner;

public class CountDemoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
  @Override
  public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {
    String line = value.toString();
    IntWritable outputValue = new IntWritable(1);
    String[] words = line.split(",");
    if(words[6].equals("DM"))
      context.write(new Text("Total Lines: "), outputValue);
  }
}