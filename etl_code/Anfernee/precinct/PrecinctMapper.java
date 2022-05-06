import java.io.IOException;
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class PrecinctMapper
		extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException {

		String line = value.toString();

		// dataset has columns precinct and zipcode
		// precinct looks like #th Precinct. We want to extact the number
		String[] fields = line.split(",");
		String newLine = "";
		// Lets get the precinct number from the precinct field
		
		int stopIndex = -1;
		for (int i=0; i<fields[0].length(); i++){
			if (!Character.isDigit(fields[0].charAt(i))){
				stopIndex = i;
				break;	
			}
		}	

		// if our new string is valid, then we add the record
		if (stopIndex != -1){
			
			// We'll get the number, then recreate the record
			String precinct = fields[0].substring(0, stopIndex);
			String zipcode = fields[1].split("-")[0];
			
			if (precinct.length() > 0 && zipcode.length() > 0){
				newLine = precinct + "," + zipcode;
				context.write(new Text("records"), new Text(newLine));
			}	
		}
	} 
}
