import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Application2 {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("word count").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.textFile("ventes.txt");
        JavaPairRDD<String,Integer> rdd3=rdd1.mapToPair(s->new Tuple2<>(s.split(" ")[1],Integer.
                parseInt(s.split(" ")[3])));
        JavaPairRDD<String,Integer> rdd4=rdd3.reduceByKey((v1, v2)->v1+v2);
        rdd4.foreach(nameTuple-> System.out.println(nameTuple._1()+" "+nameTuple._2()));


    }
}
