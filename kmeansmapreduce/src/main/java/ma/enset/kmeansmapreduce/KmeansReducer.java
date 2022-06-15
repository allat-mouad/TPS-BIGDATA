package ma.enset.kmeansmapreduce;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KmeansReducer extends Reducer<Text,Text,Text,Text> {
    private static final DecimalFormat df = new DecimalFormat("0.00");
     static int   test  = 0;


    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        double sommex=0;
        double sommey=0;
        double px=0;
        double py=0;
        int nb_points=0;
        Iterator<Text> it=values.iterator();
        while (it.hasNext()){
            String p[]=it.next().toString().split(",");
             Point p1=new Point(Double.parseDouble(p[0]),Double.parseDouble(p[1]));
            sommex+=p1.getX();
            sommey+=p1.getY();
            nb_points++;
        }
        Point mean=new Point(sommex/nb_points,sommey/nb_points);
        KmeansDriver.centers.add(mean);


        context.write(key,new Text(mean.toString()));
    }
}
