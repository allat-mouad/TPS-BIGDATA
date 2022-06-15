import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

public class OccurencesMapper3 extends MapReduceBase
        implements Mapper<LongWritable, Text,Text, DoubleWritable> {

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, DoubleWritable> output, Reporter reporter) throws IOException {
        String depar[]=value.toString().split("\",\"");
        String ch =depar[13];
        ch=ch.split(",")[0]+"."+ch.split(",")[1];
        Double d=Double.parseDouble(ch);

        output.collect(new Text(depar[1].substring(5,7)),new DoubleWritable(d));

    }
}
