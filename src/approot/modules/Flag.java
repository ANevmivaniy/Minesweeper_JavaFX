package approot.modules;


class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
        transitToController(coord);
    }

    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED: setClosedToBox(coord); break;
            case CLOSED: setFlagedToBox(coord); break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
        transitToController(coord);
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
        transitToController(coord);
    }
    private void transitToController(Coord coord){
        TransitController.transit(coord);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
        transitToController(coord);
    }

    void setOpenedToCloseBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
            transitToController(coord);
        }
    }

    void setNoBombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED) {
            flagMap.set(coord, Box.NOBOMB);
            transitToController(coord);
        }
    }

    int getCountOfFlageBoxesAround(Coord coord) {
        int count = 00;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}