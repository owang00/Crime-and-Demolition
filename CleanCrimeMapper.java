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
    
    String start_dt = (String)words[1];
    DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); 
    Date date = (Date)formatter.parse(start_dt);
    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
    String finalString = newFormat.format(date);

    current = finalString+","+words[8]+","+words[9];
    context.write(new Text(current), outputValue);
    
  }
}