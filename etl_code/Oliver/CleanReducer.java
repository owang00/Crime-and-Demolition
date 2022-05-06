import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class CleanReducer extends Reducer<Text, IntWritable, Text, NullWritable> {
  
  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {   
    
    NullWritable nw = NullWritable.get();
    context.write(key, nw);
  }
}