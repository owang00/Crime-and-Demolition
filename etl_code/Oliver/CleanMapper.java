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
      String[] conversion = words[26].split("/");
      String[] conversion2 = words[27].split("/");

      if(conversion.length == 3 && conversion2.length == 3) {
        String finalString = conversion[2]+"-"+conversion[0]+"-"+conversion[1];
        String finalString2 = conversion2[2]+"-"+conversion2[0]+"-"+conversion2[1];
        current = words[0]+","+words[6]+","+words[11]+","+words[17]+","+finalString+","+finalString2;
        context.write(new Text(current), outputValue);
      }
      
    }
  }
}