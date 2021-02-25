package Program1;

import java.util.ArrayList;
import java.util.Random;

public class PerformanceTest {

    public static void getTest(int numberofCities){
            Random random = new Random();
            ArrayList<City> cities = new ArrayList<>();
            for(int i=0;i<numberofCities;i++){
                double x = random.nextDouble()*(300-1)+1;
                double y = random.nextDouble()*(300-1)+1;
                Character ch = (char) ('A'+i);
                cities.add(new City(x,y,ch.toString()));
            }
            printResults(cities);

        }


    private static void printResults( ArrayList<City> cities){
        DFS dfs = new DFS();
        BFS bfs = new BFS();
        KNN knn = new KNN();
        AStar aStar = new AStar();

        System.out.println("Test wydajnosci dla "+cities.size()+" miast");

        System.out.println("DFS");
        dfs.printPerformanceTest(cities);
        System.out.println("");

        System.out.println("BFS");
        bfs.printPerformanceTest(cities);
        System.out.println("");

        System.out.println("KNN");
        knn.printPerformanceTest(cities);
        System.out.println("");

        System.out.println("ASTAR");
        aStar.printPerformanceTest(cities);
        System.out.println("");

    }
}
