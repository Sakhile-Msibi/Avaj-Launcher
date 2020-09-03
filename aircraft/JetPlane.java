package aircraft;

import simulator.WeatherTower;
import simulator.Simulator;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch(weather) {
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLatitude() + 5, coordinates.getLongitude(), coordinates.getHeight());
                Simulator.pen.println("JetPlane#" + name + "(" + id + "): " + "It is raining on the JetPlane.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLatitude() + 1, coordinates.getLongitude(), coordinates.getHeight());
                Simulator.pen.println("JetPlane#" + name + "(" + id + "): " + "There is too much fog, I can not see anything.");
                break;
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                Simulator.pen.println("JetPlane#" + name + "(" + id + "): " + "Ahh the sun is shinning bright today.");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getHeight() - 7);
                Simulator.pen.println("JetPlane#" + name + "(" + id + "): " + "OMG it is snowing, we are going down.");
                break;
        }
        if(coordinates.getHeight() == 0) {
            Simulator.pen.println("JetPlane#" + name + "(" + id + "): landing");
            Simulator.pen.println("Tower says: JetPlane#" + name + "(" + id + ") unregister from weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower1) {
        Simulator.pen.println("Tower says: JetPlane#" + name + "(" + id + ") register to weather tower");
        weatherTower = weatherTower1;
        weatherTower.register(this);
    }
}