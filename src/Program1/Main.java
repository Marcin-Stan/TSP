package Program1;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {


        City city1 = new City(1.0,2.5,"A");
        City city2 = new City(30.0,50,"B");
        City city3 = new City(60.0,100,"C");
        City city4 = new City(85.0,25,"D");
        City city5 = new City(90.0,380,"E");
        City city6 = new City(20.0,600,"F");
        City city7 = new City(30.0,700,"G");


        /*
        for(int i=5;i<=10;i++){
            PerformanceTest.getTest(i);
        }

         */

        ArrayList<City> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city5);
        cities.add(city6);
        cities.add(city7);


        //DFS DFS = new DFS();
        //DFS.printSearchTree(cities,0);// Drugi parametr to numer miasta,z ktorego chcemy wystartowac, zaczynamy indeksowanie od 0(Brak walidacji)

        BFS bfs = new BFS();
        bfs.printSearchTree(cities,0);

        //KNN knn = new KNN();
        //knn.printPath(cities,0);

        //AStar aStar = new AStar();
        //aStar.printPath(cities,0);

    }
}
