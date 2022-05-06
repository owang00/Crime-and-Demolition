import java.io.IOException;
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class PrecinctReducer
		extends Reducer<Text, Text, Text, NullWritable> {


	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
		
		NullWritable nw = NullWritable.get();

		for (Text value : values) {
			context.write(new Text(value), nw);
		}
	}
}
