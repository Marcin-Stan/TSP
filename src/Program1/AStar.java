package Program1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AStar {

    List<State> stateList = new ArrayList<>();
    List<State> finalPath = new ArrayList<>();
    List<City> cities;
    int startCity;

    public void printPath(ArrayList<City> cityArrayList, int startCity){
        this.startCity = startCity;
        cities = cityArrayList;

        List<City> list = new ArrayList<>();
        list.add(cities.get(startCity));

        State state = new State(list,0,0);
        stateList.add(state);
        potomstwo(stateList.get(0));

        while (finalPath.isEmpty()){
            potomstwo(stateList.get(0));
        }

        // wyswietlanie
        for (State state1:finalPath){
            for(City city:state1.getCityList()){
                System.out.print(city.getCityName() );
            }
            System.out.println(" "+state1.getCost()+" "+state1.getHeuristic());
        }

    }

    private void potomstwo(State state){
        // usuniecie stanu, ktory potem bedzie rozwijany
        stateList.remove(state);
        ArrayList<Double> tempList = new ArrayList<>();
        for(int i=0;i<cities.size();i++){
            List<City> cityList = new ArrayList<>();
            for(City city:state.getCityList()){
                city.setVisited(true);
                cityList.add(city);
            }

            if(!cities.get(i).isVisited()){
                cityList.add(cities.get(i));
                tempList.add(countLenthbyTwoPoints(cityList));
                stateList.add(0,new State(cityList,countLengthSegment(cityList),0));

            }

        }

        // liczenie heurystyki k(min)
        for(State state1:stateList){
            if(state1.getHeuristic()==0)
                state1.setHeuristic(countHeuristicMin(state1.getCost(),Collections.min(tempList),cities.size()+1-state1.getCityList().size()));
        }
        // w razie odwiedzenia wszystkich miast do stannu dodajemy miasto poczatkowe i wyliczamy dla niego koszt i heurystyke
        if(stateList.get(0).getCityList().size()==cities.size()){
            List<City> cityList = stateList.get(0).getCityList();
            cityList.add(cityList.size(),cities.get(startCity));
            tempList.add(countLenthbyTwoPoints(cityList));
            stateList.set(0,new State(cityList,countLengthSegment(cityList),
                    countHeuristicMin(countLengthSegment(cityList),Collections.min(tempList),cities.size()+1-cityList.size())));
        }
        // sortowanie stanow na podstawie heurystyki
        Collections.sort(stateList, new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Double.compare(o1.getHeuristic(),o2.getHeuristic());
            }
        });

        //Testowanie krok po kroku

        for (State state1:stateList) {
            System.out.print(state1.getCost()+" "+state1.getHeuristic());
            for(City city:state1.getCityList()){
                System.out.print(" "+city.getCityName());
            }
            System.out.println("");
        }
        System.out.println("");




        // Jezeli po sortowaniu na 1 miejscu znajduje sie stan koncowy to dodajemy go do finalowej sciezki, co zakonczy algorytm
        if(stateList.get(0).getCityList().size()==cities.size()+1)
            finalPath.add(stateList.get(0));

        for(City city:cities)
            city.setVisited(false);

        // rekurencja
        //if(finalPath.isEmpty())
           // potomstwo(stateList.get(0));


    }
    // obliczanie kosztu dla calego stanu
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
    // obliczanie sciezki pomiedzy dwoma punktami, ostatnim i docelowym
    private double countLenthbyTwoPoints(List<City> cityList){
        double length = Math.sqrt(
                Math.pow(cityList.get(cityList.size()-2).getCoordinateX() - cityList.get(cityList.size()-1).getCoordinateX(),2) +
                        Math.pow(cityList.get(cityList.size()-2).getCoordinateY() - cityList.get(cityList.size()-1).getCoordinateY(),2)
        );

        return length;
    }

    private double countHeuristicMin(Double overallCost, Double min , int cityToVisit){
        if(cityToVisit==0)
            return overallCost+(min*1);
        else
            return overallCost+(min*cityToVisit);
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

        while (finalPath.isEmpty()){
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
