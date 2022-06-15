package ma.enset.tp1sparkstreaming;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

public class Application4 {
    public static void main(String[] args) throws InterruptedException {
        RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.spark-project").setLevel(Level.WARN);
        SparkConf conf=new SparkConf().setAppName("Spark streaming HDFS").setMaster("local[*]");
        JavaStreamingContext jsc=new JavaStreamingContext(conf, Durations.seconds(10));
        JavaDStream<String> lines= jsc.textFileStream("hdfs://localhost:9000/temperatures/");
        JavaPairDStream<String,Long> wordsPair1=lines.mapToPair(s -> new Tuple2<>(s.split(",")[2],
                Long.parseLong(s.split(",")[3])));
        JavaPairDStream<String,Long> wordsPair=wordsPair1.filter(a -> a._1().equals("TMIN"));

        JavaPairDStream<String,Long> tempsum=wordsPair.reduceByKey((n1, n2) ->n1+n2);
        JavaDStream<Long> nbr=wordsPair1.count();
        nbr.print();

        JavaPairDStream<String,Long> tempmoy=tempsum.mapValues(s->s/100);

        tempmoy.print();
        jsc.start();
        jsc.awaitTermination();
    }
}
