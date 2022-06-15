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
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.concurrent.TimeoutException;

public class Application5 {
    public static void main(String[] args) throws InterruptedException, StreamingQueryException, TimeoutException {
        RootLogger rootLogger = (RootLogger) Logger.getRootLogger();
        rootLogger.setLevel(Level.ERROR);
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
        Logger.getLogger("org.spark-project").setLevel(Level.WARN);
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        StructType schema=new StructType(new StructField[]{
                new StructField("id", DataTypes.LongType,false, Metadata.empty()),
                new StructField("name",DataTypes.StringType,false,Metadata.empty()),
                new StructField("age",DataTypes.LongType,false,Metadata.empty()),
                new StructField("phone",DataTypes.StringType,false,Metadata.empty()),
                new StructField("salary",DataTypes.DoubleType,false,Metadata.empty()),
                new StructField("departement",DataTypes.StringType,false,Metadata.empty()),
        });
        Dataset<Row> df=ss.readStream().option("header",true)
                .schema(schema).csv("input");
        StreamingQuery query=df.writeStream().outputMode("append").format("console").start();
        query.awaitTermination();

    }
}
