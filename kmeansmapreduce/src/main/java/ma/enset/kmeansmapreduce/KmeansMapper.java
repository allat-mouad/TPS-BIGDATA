package ma.enset.kmeansmapreduce;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class KmeansMapper extends Mapper<LongWritable, Text, Text,Text> {
    List<Point> centers=new ArrayList<>();

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        centers.clear();
        URI uri[]= context.getCacheFiles();
        FileSystem fs=FileSystem.get(context.getConfiguration());
        InputStreamReader is=new InputStreamReader(fs.open(new Path(uri[0])));
        BufferedReader br=new BufferedReader(is);
        String ligne=null;
        while ((ligne=br.readLine())!=null){
            String point[]=ligne.split(",");
         centers.add(new Point(Double.parseDouble(point[0]),Double.parseDouble(point[1])));

        }
    }
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String p[]=value.toString().split(",");
        double px=Double.parseDouble(p[0]);
        double py=Double.parseDouble(p[1]);
      double min=Double.MAX_VALUE,d;
      String nearest_center="";
        for (Point c:centers) {
            d=Math.abs(Math.sqrt((px-c.getX())*(px-c.getX())+(py-c.getY())*(py-c.getY())));
            if (d<min){
                min=d;
                nearest_center=c.toString();
            }
        }
        context.write(new Text(nearest_center),value);
    }

}
