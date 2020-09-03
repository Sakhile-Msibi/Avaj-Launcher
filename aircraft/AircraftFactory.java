package aircraft;

public class AircraftFactory {
    private static boolean isMD5(String MD5Code) {
        if(MD5Code.length() == 32) {
            for(int i = 0; i < MD5Code.length(); i++) {
                if(!(MD5Code.charAt(i) >= '0' && MD5Code.charAt(i) <= '9') &&
                !(MD5Code.charAt(i) >= 'a' && MD5Code.charAt(i) <= 'f')) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        if(isMD5(type)) {
            switch(type) {
                case "994736b4f0aec72f6e5ae580051d012f":
                    return new Baloon(name, coordinates);
                case "2ab8b43468e8b92b0fc5c81e70e35a2d":
                    return new Helicopter(name, coordinates);
                case "554cd647d6b135f7e36ab1214c5e816a":
                    return new JetPlane(name, coordinates);
                default:
                    return null;
            } 
        } else {
            switch(type) {
                case "Baloon":
                    return new Baloon(name, coordinates);
                case "Helicopter":
                    return new Helicopter(name, coordinates);
                case "JetPlane":
                    return new JetPlane(name, coordinates);
                default:
                    return null;
            }
        }
    }
}