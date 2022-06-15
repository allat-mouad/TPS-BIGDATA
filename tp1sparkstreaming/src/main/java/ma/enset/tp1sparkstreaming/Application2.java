package ma.enset.tp1sparkstreaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

public class Application2 {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf=new SparkConf().setAppName("Spark streaming HDFS").setMaster("local[*]");
        JavaStreamingContext jsc=new JavaStreamingContext(conf, Durations.seconds(10));
        JavaDStream<String> lines= jsc.textFileStream("hdfs://localhost:9000/words/");
        JavaDStream<String> words= lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());
        JavaPairDStream<String,Integer> wordsPair=words.mapToPair(s -> new Tuple2<String,Integer>(s,1));
        JavaPairDStream<String,Integer> wordCount=wordsPair.reduceByKey((n1, n2) ->n1+n2);
        wordCount.print();
        jsc.start();
        jsc.awaitTermination();
    }
}
