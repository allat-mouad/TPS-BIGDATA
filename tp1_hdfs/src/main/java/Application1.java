import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataOutputStream;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Application1 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        System.setProperty("HADOOP_USER_NAME","root");
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {
            FileSystem fs=FileSystem.get(cf);
            Path path=new Path("/BDCC/CPP/cours/CoursCPP3.txt");
            FSDataOutputStream fsdo= fs.create(path);
            BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fsdo));
            br.write("CPP ");
            br.newLine();
            br.write("Cour 3");
            br.close();
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
