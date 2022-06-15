import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application4 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();

        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {
            FileSystem fs=FileSystem.get(cf);
            Path path1=new Path("/BDCC/CPP/cours/CoursCPP2.txt");
            FSDataInputStream fsi=fs.open(path1);
            BufferedReader br=new BufferedReader(new InputStreamReader(fsi));
            String ligne=null;
            while((ligne=br.readLine())!=null){
                System.out.println(ligne);
            }
            br.close();
            fs.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
