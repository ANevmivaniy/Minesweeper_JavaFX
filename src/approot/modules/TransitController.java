package approot.modules;

public class TransitController {
    private static TransitListener transitListener;

    public static void setTransitListener(TransitListener transitListener){
        TransitController.transitListener = transitListener;
    }

    public static void transit(Coord coord){
        transitListener.transitAndNotify(coord);
    }
    public static void restart(){
        transitListener.restart();
    }
}
