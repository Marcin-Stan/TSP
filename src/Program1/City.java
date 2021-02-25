package Program1;

public class City {

    private double coordinateX;
    private double coordinateY;
    private boolean isVisited;
    private String cityName;

    public City(double coordinateX,double coordinateY, String cityName){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.isVisited = false;
        this.cityName = cityName;
    }

    public City(){}

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
