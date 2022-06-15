import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class OccurencesMapper2 extends MapReduceBase
        implements Mapper<LongWritable, Text,Text, LongWritable> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {
        String depar[]=value.toString().split(",");
        output.collect(new Text(depar[2]),new LongWritable(1));

    }
}
