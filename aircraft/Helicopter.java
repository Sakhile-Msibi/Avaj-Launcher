package aircraft;

import simulator.WeatherTower;
import simulator.Simulator;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch(weather) {
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude() + 5, coordinates.getHeight());
                Simulator.pen.println("Helicopter#" + name + "(" + id + "): " + "It is raining on the helicopter.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude() + 1, coordinates.getHeight());
                Simulator.pen.println("Helicopter#" + name + "(" + id + "): " + "There is too much fog, I can not see anything.");
                break;
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                Simulator.pen.println("Helicopter#" + name + "(" + id + "): " + "Ahh the sun is shinning bright today.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getHeight() - 12);
                Simulator.pen.println("Helicopter#" + name + "(" + id + "): " + "OMG it is snowing, we are going down.");
                break;
        }
        if(coordinates.getHeight() == 0) {
            Simulator.pen.println("Helicopter#" + name + "(" + id + "): landing");
            Simulator.pen.println("Tower says: Helicopter#" + name + "(" + id + ") unregister from weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower1) {
        Simulator.pen.println("Tower says: Helicopter#" + name + "(" + id + ") register to weather tower");
        weatherTower = weatherTower1;
        weatherTower.register(this);
    }
}