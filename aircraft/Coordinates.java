package aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude1, int latitude1, int height1) {
        longitude = longitude1;
        latitude = latitude1;
        height = height1 < 0 ? 0 : height1;
        height = height1 > 100 ? 100 : height1;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}