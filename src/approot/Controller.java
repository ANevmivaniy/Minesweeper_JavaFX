package approot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import approot.modules.*;

import java.io.InputStream;


public class Controller implements TransitListener {
    private Game game;

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    @FXML  private GridPane gridPane;
    @FXML  private Label label;

    public Controller(){
        game = new Game(COLS,ROWS,BOMBS);
        game.start();
        setImages();
        TransitController.setTransitListener(this);
    }

    public void initPane(){
        restartPane();
        gridPane.setOnMouseClicked(event ->{
            int x = (int) event.getX()/IMAGE_SIZE;
            int y = (int) event.getY()/IMAGE_SIZE;
            Coord coord = new Coord(x, y);
            if(event.getButton() == MouseButton.PRIMARY)
                game.pressPrimaryButton(coord);
            if(event.getButton() == MouseButton.SECONDARY)
                game.pressSecondaryButton(coord);
            if(event.getButton() == MouseButton.MIDDLE)
                game.start();
            label.setText(getMessage());
        });
    }
    private void restartPane(){
        for(Coord coord : Ranges.getAllCoords()){
            ImageView iv = new ImageView((Image) game.getBox(coord).image);
            gridPane.add(iv, coord.x, coord.y);
        }
        gridPane.setPrefSize(Ranges.getSize().x * IMAGE_SIZE,Ranges.getSize().y * IMAGE_SIZE);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED:
                return "Think twice";
            case BOMBED:
                return "YOU LOSE!";
            case WINNER:
                return "CONGRATULATION";
            default:
                return "Welcome";
        }

    }
    private void setImages() {
        for (Box box : Box.values())
            box.image = getImage(box.name());
    }

    private Image getImage(String name) {
        String filename = "/img/" + name.toLowerCase() + ".png";
        InputStream inputStream = getClass().getResourceAsStream(filename);
        return new Image(inputStream);
    }

    @Override
    public void restart(){
            restartPane();
    }

    @Override
    public void transitAndNotify(Coord coord) {
        updatePane(coord);
    }

    private void updatePane(Coord coord){
        ImageView iv = new ImageView((Image) game.getBox(coord).image);
        gridPane.add(iv, coord.x, coord.y);
    }

}
