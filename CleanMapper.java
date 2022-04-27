import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.File;
import java.util.Scanner;

public class CleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
  @Override
  public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {
    String line = value.toString();
    String current = "";
    IntWritable outputValue = new IntWritable();
    String[] words = line.split(",");
    if(words[6].equals("DM")) {
      current = words[0]+","+words[6]+","+words[11]+","+words[17]+","+words[26]+","+words[27];
      context.write(new Text(current), outputValue);
    }
  }
}