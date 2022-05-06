import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.text.*;

public class CleanCrimeMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
  @Override
  public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {
    String line = value.toString();
    String current = "";
    IntWritable outputValue = new IntWritable();
    String[] words = line.split(",");
    
    // MM-dd-yyyy to yyyy-MM-dd
    String[] conversion = words[1].split("/");
    if(conversion.length == 3) {
      String finalString = conversion[2]+"-"+conversion[0]+"-"+conversion[1];
      current = finalString+","+words[8]+","+words[9];
      context.write(new Text(current), outputValue);
    }    
  }
}