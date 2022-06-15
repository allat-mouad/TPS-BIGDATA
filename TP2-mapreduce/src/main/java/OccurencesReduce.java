import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.*;

public class OccurencesReduce extends MapReduceBase
        implements Reducer<Text, LongWritable,Text, Text> {

    @Override
    public void reduce(Text key, Iterator<LongWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        List<Long> list=new ArrayList<>();
        int i=0;

        while (values.hasNext()){
            list.add(values.next().get());
            i++;
        }
        Collections.sort(list);
        output.collect(key,new Text("max="+list.get(i-1)+" min ="+list.get(0)));
    }
}
