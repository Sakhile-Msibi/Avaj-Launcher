package aircraft;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    protected Aircraft(String name1, Coordinates coordinates1) {
        name = name1;
        coordinates = coordinates1;
        id = nextId();
    }

    private long nextId() {
        return idCounter++;
    }
}