package approot.modules;

public interface TransitListener {
    void transitAndNotify(Coord coord);
    void restart();
}
