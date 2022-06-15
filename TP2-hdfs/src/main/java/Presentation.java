import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataOutputStream;
public class Presentation {
    public static void main(String[] args) throws Exception {
        Configuration cf=new Configuration() {
        };
        System.setProperty("HADOOP_USER_NAME","root");
        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {
            FileSystem fs=FileSystem.get(cf);
            Path path=new Path("/BDCC/CPP/CoursCPP1.txt");
            FSDataOutputStream fsdo= fs.create(path);
            BufferedWriter br=new BufferedWriter(new OutputStreamWriter(fsdo));
            br.write("hello world ");

            br.close();
            fs.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
