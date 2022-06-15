import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.function.Function;

public class Application4 {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("temperature").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rdd1=sc.textFile("1763.csv");
        JavaPairRDD<String,Long> rdd2=rdd1.mapToPair(s->new Tuple2<>(s.split(",")[2],
                Long.parseLong(s.split(",")[3])));

        JavaPairRDD<String,Long> rdd3=rdd2.filter(a -> a._1().equals("TMIN"));


        JavaPairRDD<String,Long> rdd4=rdd3.reduceByKey((v1, v2)->v1+v2);
        Long nbr=rdd3.count();
        rdd4.foreach(nameTuple-> System.out.println("moyenne:"+nameTuple._2()/nbr));



    }
}
