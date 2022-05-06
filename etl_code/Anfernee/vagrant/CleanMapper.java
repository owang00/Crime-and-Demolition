import java.io.IOException;
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CleanMapper
		extends Mapper<LongWritable, Text, Text, Text> {

	private static final int BOROUGH = 0;
	private static final int ZIPCODE = 10;
	private static final int BLDGCLS = 30;

	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] fields = line.split(",");
		
		// we're only interested in borough, zipcode and building class
		String newCols = fields[BOROUGH] + "," + fields[ZIPCODE] + "," + fields[BLDGCLS];
		

		// we want only the vacant buildings and the records that have a zip code
		if (!fields[BLDGCLS].isEmpty() && !fields[ZIPCODE].isEmpty() && fields[BLDGCLS].charAt(0) == 'V'){
			context.write(new Text("records"), new Text(newCols));
		}	
	} 
}
