package approot.modules;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof  Coord){
            Coord coord = (Coord) o;
            return this.x == coord.x && this.y == coord.y;
        }
        return super.equals(o);

    }


}
