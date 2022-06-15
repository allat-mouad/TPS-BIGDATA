import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

public class Application3 {
    public static void main(String[] args) {
        Configuration cf=new Configuration();
        System.setProperty("HADOOP_USER_NAME","root");

        cf.set("fs.defaultFS","hdfs://localhost:9000");
        cf.set("dfs.replication","1");
        try {
            FileSystem fs=FileSystem.get(cf);
            //Path path1=new Path("/BDCC/CPP/cours/CoursCPP3.txt");
            Path path2=new Path("/BDCC/CPP/TPS/TPCPP1.txt");
            Path path1=new Path("/BDCC/JAVA");
           fs.delete(path2);
           fs.delete(path1);
          //  fs.rename(new Path("/BDCC/JAVA/Cours/CoursCPP2.txt"),new Path("/BDCC/JAVA/Cours/CoursJAVA2.txt"));
            //fs.rename(new Path("/BDCC/JAVA/Cours/CoursCPP1.txt"),new Path("/BDCC/JAVA/Cours/CoursJAVA1.txt"));

           // FSDataInputStream fsi=fs.open(path1);
            //FSDataOutputStream fso=fs.create(path2);
           // IOUtils.copyBytes(fsi,fso,cf);


          //  Path path3=new Path("/image2.jpg");
            //fs.copyFromLocalFile(new Path("TPCPP1"),new Path("/BDCC/CPP/TPS/TPCPP1.txt"));
            //fs.copyFromLocalFile(new Path("TPCPP2"),new Path("/BDCC/CPP/TPS/TPCPP2.txt"));
            //fs.copyFromLocalFile(new Path("TPJAVA2.txt"),new Path("/BDCC/JAVA/TPS/TPJAVA2.txt"));
            //fs.copyFromLocalFile(new Path("TPJAVA1"),new Path("/BDCC/JAVA/TPS/TPJAVA1.txt"));
            //fs.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
