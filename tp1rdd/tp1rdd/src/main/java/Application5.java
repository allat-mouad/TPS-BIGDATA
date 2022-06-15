import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application5 {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("temperature").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.textFile("1763.csv");
       // JavaPairRDD<Long,Long> rdd2=rdd1.mapToPair(s->new Tuple2<>(Long.parseLong(s.split(",")[3]),Long.parseLong(s.split(",")[3])));
        JavaRDD<Long> rdd2=rdd1.map(s->Long.parseLong(s.split(",")[3]));
        List<Long> list1 =rdd2.collect();




        System.out.println(list1.stream().max(Long::compare).get());



    }
}
