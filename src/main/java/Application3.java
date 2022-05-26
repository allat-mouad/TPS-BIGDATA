
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.execution.columnar.MAP;

import java.util.HashMap;
import java.util.Map;

import static org.apache.spark.sql.functions.max;
public class Application3 {
    public static void main(String[] args) {
        SparkSession ss=SparkSession.builder().
                appName("TP Spark SQL").
                master("local[*]").getOrCreate();
        Map<String,String> options=new HashMap<>();
        options.put("driver","com.mysql.cj.jdbc.Driver");
        options.put("url","jdbc:mysql://localhost:3306/db_filled");
        options.put("user","root");
        options.put("password","");

             Dataset<Row> dfCat=ss.read().
                format("jdbc")
                .options(options)
                .option("dbtable","categories")
                .option("query","select category_name from categories WHERE category_id in (SELECT product_category_id FROM products WHERE product_id in(SELECT order_item_product_id from order_items ORDER by order_item_quantity DESC)) ")
                .load();
        dfCat.show();

    /*    Dataset<Row> dfProduits=ss.read().
                format("jdbc")
                .options(options)
                .option("dbtable","PRODUITS")
                .option("query","select P.NOM,P.PRIX from PRODUITS P,CATEORIE C where P.ID_CATEGORIE=C.ID_CAT and C.NOM='CAT1'")
                .load();*/
      //  df.createOrReplaceTempView("employes");
       // ss.sql("select * from employes where age between 30 AND 35 ").show();

 //       dfProduits.show();
     //   Dataset<Row> dfCategory=ss.read().format("jdbc").options(options).option("dbtable","CATEORIE").load();
      //  dfCategory.show();
    }
}
