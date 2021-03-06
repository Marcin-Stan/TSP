package Program1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BFS {

    List<State> stateList = new ArrayList<>();
    List<State> finalPath = new ArrayList<>();
    List<City> cities;
    int startCity;

    public void printSearchTree (ArrayList<City> cityArrayList, int startCity ) {
        this.startCity = startCity;
        cities = cityArrayList;

        List<City> list = new ArrayList<>();
        list.add(cities.get(startCity));//

        State state = new State(list,0,0);
        stateList.add(state);



        potomstwo(stateList.get(0));
        while (!stateList.isEmpty())
            potomstwo(stateList.get(0));


        // wyswietlanie wszystkich stanow
        HashMap<List<City>,Double> hashMap = new HashMap<>();
        for(State state21 :finalPath){
            for(City city : state21.getCityList())
            {
                System.out.print(city.getCityName());
            }
            System.out.print(" "+ state21.getCost());
            System.out.println("");

            hashMap.put(state21.getCityList(),state21.getCost());
        }
        // wyznaczanie najkrotszej sciezki
        double min = Collections.min(hashMap.values());
        hashMap.forEach((key,value) ->{
           if(value.equals(min)) {
               System.out.println("Najkrótsza trasa");
               for(City city:key){
                   System.out.print(city.getCityName());
               }
               System.out.print(" "+min);
           }
        });

    }


    private void potomstwo(State state){
        stateList.remove(state);
        for(int i=0;i<cities.size();i++){
            List<City> cityList = new ArrayList<>();
            for(City city:state.getCityList()){
                city.setVisited(true);
                cityList.add(city);
            }
            if(!cities.get(i).isVisited()){
                cityList.add(cities.get(i));
                //dodajemy na koniec listy, tak jak w kolejce
                stateList.add(stateList.size(),new State(cityList,countLengthSegment(cityList),0));
            }

        }
     //testowanie dzialania krok po kroku
        for(State state1:stateList){
            for(City city:state1.getCityList()){
                System.out.print(city.getCityName());
            }
            System.out.println("");
        }
        System.out.println("");





        if(!stateList.isEmpty()) {
            //Jesli stan na koncu kolejki zawiera wszystkie miasta to dodajemy miasto poczatkowe i wyliczamy dla niego koszt
            if (stateList.get(stateList.size()-1).getCityList().size() == cities.size()) {//
                List<City> cityList = stateList.get(stateList.size()-1).getCityList();
                cityList.add(cityList.size(), cities.get(startCity));

                stateList.set(stateList.size()-1, new State(cityList, countLengthSegment(cityList), 0));
                finalPath.add(stateList.get(stateList.size()-1));
                stateList.remove(stateList.size()-1);
            }
        }

        for(City city:cities)
            city.setVisited(false);
            // rekurencja
            //if(!stateList.isEmpty())
             //       potomstwo(stateList.get(0));


            }

    private double countLengthSegment(List<City> cityList){
        double length =0;
        for(int i=0;i<cityList.size()-1;i++){
            length+= Math.sqrt(
                    Math.pow(cityList.get(i).getCoordinateX() - cityList.get(i+1).getCoordinateX(),2) +
                            Math.pow(cityList.get(i).getCoordinateY() - cityList.get(i+1).getCoordinateY(),2)
            );
        }
        return length;
    }


    // test wydajnosci bez wyswietlania
    public void printPerformanceTest(ArrayList<City> cityArrayList){
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        long startTime = System.nanoTime();
        cities = cityArrayList;
        List<City> list = new ArrayList<>();
        list.add(cities.get(0));// tu zmienic w razie zmiany miasta

        State state = new State(list,0,0);
        stateList.add(state);
        potomstwo(stateList.get(0));

        while (!stateList.isEmpty()){
            potomstwo(stateList.get(0));
        }

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Czas:"+elapsedTime);

        // Get the Java runtime

        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: "
                + bytesToMegabytes(memory));

    }

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

}
