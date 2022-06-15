import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OccurencesReduce3 extends MapReduceBase
        implements Reducer<Text, DoubleWritable,Text, Text> {

    @Override
    public void reduce(Text key, Iterator<DoubleWritable> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        List<Double> list=new ArrayList<>();
        int i=0;

        while (values.hasNext()){
            list.add(values.next().get());
            i++;
        }

        Collections.sort(list);
        output.collect(key,new Text("max="+list.get(i-1)+" min ="+list.get(0)));

    }
}
