package aircraft;

import simulator.WeatherTower;
import simulator.Simulator;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch(weather) {
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getHeight() - 5);
                Simulator.pen.println("Baloon#" + name + "(" + id + "): " + "It is raining on the baloon.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getHeight() - 3);
                Simulator.pen.println("Baloon#" + name + "(" + id + "): " + "There is too much fog, I can not see anything.");
                break;
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() - 4);
                Simulator.pen.println("Baloon#" + name + "(" + id + "): " + "Ahh the sun is shinning bright today.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getHeight() - 15);
                Simulator.pen.println("Baloon#" + name + "(" + id + "): " + "OMG it is snowing, we are going down.");
                break;
        }
        if(coordinates.getHeight() == 0) {
            Simulator.pen.println("Baloon#" + name + "(" + id + "): landing");
            Simulator.pen.println("Tower says: Baloon#" + name + "(" + id + ") unregister from weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower1) {
        Simulator.pen.println("Tower says: Baloon#" + name + "(" + id + ") register to weather tower");
        weatherTower = weatherTower1;
        weatherTower.register(this);
    }
}