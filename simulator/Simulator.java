package simulator;

import aircraft.AircraftFactory;
import aircraft.Flyable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    public static class NoArgErr extends Exception {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        
        NoArgErr(String message) {
            super(message);
        }
    }

    public static PrintWriter pen;
    private static List<Flyable> flyables = new ArrayList<>();

    private static void checkArg(String[] args) throws NoArgErr {
        if (args.length < 1) throw new NoArgErr("Please provide a scenario file");
    }

    public static void main(String[] args) {
        try {
            checkArg(args);
        } catch (NoArgErr e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        WeatherTower weatherTower = new WeatherTower();
        try {
            BufferedReader read = new BufferedReader(new FileReader(args[0]));
            pen = new PrintWriter(new FileWriter("simulation.txt"));
            String linereader = read.readLine();
            if (linereader == null) {
                read.close();
                return;
            }
            int numberOfSimulations = Integer.parseInt(linereader.split(" ")[0]);
            if (numberOfSimulations < 0) {
                System.out.println("Invalid number of simulations: " + numberOfSimulations);
                System.exit(1);
            }
            while ((linereader = read.readLine()) != null) {
                Flyable flyable = AircraftFactory.newAircraft(
                    linereader.split(" ")[0],
                    linereader.split(" ")[1],
                    Integer.parseInt(linereader.split(" ")[2]),
                    Integer.parseInt(linereader.split(" ")[3]),
                    Integer.parseInt(linereader.split(" ")[4])
                );
                if(flyable == null) {
                    System.err.println("Invalid aircraft type");
                    System.exit(1);
                }
                flyables.add(flyable);
            }
            for(Flyable flyable : flyables) {
                flyable.registerTower(weatherTower);
            }
            System.out.println(numberOfSimulations);
            for(int i = 0; i < numberOfSimulations; i++) {
                weatherTower.changeWeather();
            }
            read.close();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("The file does not exist or is invalid");
        } finally {
            if(pen != null)
                pen.close();

        }
    }
}