package Program1;

import java.util.Comparator;
import java.util.List;

public class State{

    List<City> cityList ;
    double cost;
    double heuristic;

    public State(List<City> cityList, double cost, double heuristic) {
        this.cityList = cityList;
        this.cost = cost;
        this.heuristic = heuristic;
    }


    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

}
