import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.*;

public class Application6 {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("temperature").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.textFile("1886.csv");
        JavaPairRDD<Long,String> rdd2=rdd1.mapToPair(s->new Tuple2<>(Long.parseLong(s.split(",")[3]),
                s.split(",")[2]));

        JavaPairRDD<Long,String> rdd3=rdd2.filter(a -> a._2().equals("TMIN"));

        System.out.println("Top 5 des stations meteo les plus froides. : " + rdd3.sortByKey(true).take(5));


       /* List<Tuple2<String,Long>> list1 =rdd2.collect();
        Map<String, Long> list2 = new HashMap<>();
        Map<String, Long> list = rdd2.collectAsMap();


        for (Tuple2<String,Long> m:
             list1) {
            list2.put(m._1,m._2);

        }
        //list.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        list.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(5).forEach(s->
                System.out.println(s.getKey()+" "+s.getValue()));*/








    }
}
