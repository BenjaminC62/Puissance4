package fr.univartois.butinfo.ihm.fourinaline.model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;

public class Game {

    public static final String FULL = "La grille est pleine";

    private Grid grid;
    private Token jeton; // On declare la variable (on la juste creer on doit donc affecter une valeur a cette var)

    public Game(){
        grid = new Grid();
        initGame();
    }

    public void initGame(){
        grid.clear();
        jeton = Token.YELLOW;
    }

    public void initGrille(){
        grid.clear();
    }


    public Grid getGrille() {
        return grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public Token getJeton() {
        return jeton;
    }

    public int play(int colonne){
        int result = grid.play(jeton, colonne);
        if(result != -1){
            Optional<Token> stat = grid.findFourInALine();

            jeton = jeton.next();
        }
        return result;

    }

}

