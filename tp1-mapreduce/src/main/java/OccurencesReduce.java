
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class OccurencesReduce extends MapReduceBase
        implements Reducer<Text, LongWritable,Text, LongWritable> {

    @Override
    public void reduce(Text key, Iterator<LongWritable> values, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {
        int somme=0;
        while (values.hasNext()){
            System.out.println(key.toString()+"  fffffffffff"+values.next().get());
            somme+=values.next().get();
        }
        output.collect(key,new LongWritable(somme));
    }
}
