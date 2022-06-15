package ma.enset.tp1sparkstreaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

public class Application1 {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf=new SparkConf().setAppName("TP 1 Spark streaming").setMaster("local[*]");
        JavaStreamingContext jsp=new JavaStreamingContext(conf, Durations.seconds(60));
        JavaReceiverInputDStream<String> lines= jsp.socketTextStream("localhost",9090);
        JavaDStream<String> words= lines.flatMap(s -> Arrays.asList(s.split(" ")).iterator());
        JavaPairDStream<String,Integer> wordsPair=words.mapToPair(s -> new Tuple2<String,Integer>(s,1));
        JavaPairDStream<String,Integer> wordCount=wordsPair.reduceByKey((n1, n2) ->n1+n2);
        wordCount.print();
        jsp.start();
        jsp.awaitTermination();
    }
}
