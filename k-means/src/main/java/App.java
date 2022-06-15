import java.util.ArrayList;
import java.util.List;

public class App {
    private final static int NB_POINTS=100;
    private final static int NB_CLUSTER=3;
    public static void main(String[] args) {
        List<Double> points=new ArrayList<Double>();
        List<Cluster> clusters=new ArrayList<Cluster>();
        for(int i=0;i<NB_POINTS;i++){
            points.add(Math.random()*100);
        }
        for(int i=0;i<NB_CLUSTER;i++){
            clusters.add(new Cluster(Math.random()*100));
        }
        double d1,d2,d3;
        double c1,c2,c3;
        double somme;
        Boolean termine=false;
        while(!termine)
        {
            for (double p:points) {
                d1=Math.abs(p-clusters.get(0).getCenter());
                d2=Math.abs(p-clusters.get(1).getCenter());
                d3=Math.abs(p-clusters.get(2).getCenter());
                if(d1<=d2 && d1<=d3){
                    clusters.get(0).getPoints().add(p);
                }
                else if(d2<=d3){
                    clusters.get(1).getPoints().add(p);
                }
                else{
                    clusters.get(2).getPoints().add(p);
                }
            }

            for(int i=0;i<NB_CLUSTER;i++){
                somme=0;
                for(int j=0;j<clusters.get(i).getPoints().size();j++){
                    somme+=clusters.get(i).getPoints().get(j);
                }
                clusters.get(i).setCenter(somme/clusters.get(i).getPoints().size());
            }


        }


    }

}

