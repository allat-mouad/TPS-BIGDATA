package ma.enset.kmeansmapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KmeansDriver {
     static boolean test=true;


    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

        Configuration conf=new Configuration();
        int i=0;
       while (test)
        {
            Job job=Job.getInstance(conf,"Job Kmeans");
            job.setJarByClass(KmeansDriver.class);
            job.setMapperClass(KmeansMapper.class);
            job.setReducerClass(KmeansReducer.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);
            job.addCacheFile(new URI("hdfs://localhost:9000/input/centres.txt"));
            FileInputFormat.addInputPath(job,new Path(args[0]));
            String c= String.valueOf(i);
            FileOutputFormat.setOutputPath(job,new Path(args[1]+c));
            i++;

            job.waitForCompletion(true);
            if(centers.size()>2)
            if(centers.get(centers.size()-1).equals(centers.get(centers.size()-2))){
                test=false;
            }

        }

        }

}
