package fr.univartois.butinfo.ihm.fourinaline.model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;

public class Game {

    private Grid grid;
    public static final String FULL = "La grille est pleine";

    public Button[] getMoveButtons() {
        return moveButtons;
    }

    public ImageView[][] getCells() {
        return cells;
    }

    private Button[] moveButtons;
    private ImageView[][] cells;

    public Button[] getTabButton() {
        return tabButton;
    }

    public ImageView[][] getTabImage() {
        return tabImage;
    }

    private Grid grid;
    private Game game;

    public void setGame(Game game){
        this.game = game;
    }


    private Token jeton; // On declare la variable (on la juste creer on doit donc affecter une valeur a cette var)


    private Button[] tabButton;
    private ImageView[][] tabImage;


    public Game(){
        grid = new Grid();
        init();
    }

    private void initPartie(){

    }

    public Grid getGrille() {
        return grid;
    }

    private void jetonPartie(){
        grid.clear();
        jeton = Token.YELLOW;
    }

    public int play(int colonne){
        int result = grid.play(jeton, colonne);
            if(result != -1){
                Optional<Token> stat = grid.findFourInALine();

                jeton = jeton.next();
            }
        return result;

    }

    public Token getToken(){
        return grid;
    }

    private void placeToken(int column, ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int columnIndex = -1;
        for (int i = 0; i < moveButtons.length; i++) {
            if (moveButtons[i] == clickedButton) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == column) {
            int rowIndex = game.getGrille().play(jeton, columnIndex);
            if (rowIndex != -1) {
                cells[rowIndex][columnIndex].setImage(loadImage(game.getGrille().get(rowIndex,columnIndex).toString()));
                if (game.getGrille().findFourInALine().isPresent() || game.getGrille().isFull()) {
                    setDesable(true);
                } else {
                    jeton = jeton.next();
                }
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("Error");
        }
    }

    private void afficheGrid(){
        for(int i = 0 ; i < cells.length ; i++){
            for(int j = 0 ; j < cells[i].length; j ++){
                cells[i][j].setImage(loadImage(game.getGrille().get(i,j).toString()));
            }
        }
    }

    private void setDesable(boolean bool){
        for(int i = 0 ; i < moveButtons.length; i++){
            moveButtons[i].setDisable(bool);
        }
    }

    private Image loadImage(String name) {
        URL urlImage = getClass().getResource("\\src\\main\\resources\\fr\\univartois\\butinfo\\ihm\\fourinaline\\view\\images" + name + ".gif"); //On recuper le fichier contenat l'image
        return new Image(urlImage.toExternalForm(), 50, 50, true, false); //ON en fait une representation graphique qu'on peut afficher
    }

}

