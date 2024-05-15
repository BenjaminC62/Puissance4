/**
 * Sample Skeleton for 'puissance4.fxml' Controller Class
 */

package fr.univartois.butinfo.ihm.fourinaline.controller;

import fr.univartois.butinfo.ihm.fourinaline.model.Game;
import fr.univartois.butinfo.ihm.fourinaline.model.Grid;
import fr.univartois.butinfo.ihm.fourinaline.model.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Optional;


public class  FourInALineController{
    private Game game;

    private Token jeton;

    /**
     *  Tableau de button.
     */
    private Button[] tabButton;

    private ImageView[][] tabImage;

    /**
     * Permet d'avoir la tableau
     * @return Un tableau d'images
     */
    public ImageView[][] getTabImage() {
        return tabImage;
    }

    public Button[] getTabButton() {
        return tabButton;
    }

    /**
     * Same as cells.
     */
    private Button[] moveButtons;

    /**
     * Permet d'avoir un tableau à deux dimension qui imite la taille de la grille il y aura des images view dedans
     */
    private ImageView[][] cells;

    public ImageView[][] getCells() {
        return cells;
    }


    /**
     *  Attributs gridPane.
     *
     */
    @FXML // fx:id="mainGrid"
    private GridPane mainGrid; // Value injected by FXMLLoader

    /**
     * Permet de créer le jeu.
     *
     */
    public FourInALineController(){
        game = new Game();
    }

    /**
     * Creer la gridPane.
     *
     * @return On return la gridPane
     */
    public GridPane getMainGrid() {
        return mainGrid;
    }

    /*  =========== BUTTONS  ===========*/

    @FXML
    void onButtonCollumn1(ActionEvent event) {
       placeToken(0, event);
    }

    @FXML
    void onButtonCollumn2(ActionEvent event) {
        placeToken(1, event);
    }

    @FXML
    void onButtonCollumn3(ActionEvent event) {
        placeToken(2, event);
    }

    @FXML
    void onButtonCollumn4(ActionEvent event) {
        placeToken(3, event);
    }

    @FXML
    void onButtonCollumn5(ActionEvent event) {
        placeToken(4, event);
    }

    @FXML
    void onButtonCollumn6(ActionEvent event) {
        placeToken(5, event);
    }

    @FXML
    void onButtonCollumn7(ActionEvent event) {
        placeToken(6, event);
    }


    @FXML
    void onButtonReset(ActionEvent event) {
        init();
    }

    /* ======== Methodes ======= */
    private void init(){
        game.getGrille().clear();
        setDesable(false);
        jeton = Token.YELLOW;
        afficheGrid();
    }

    @FXML
    void initialize() {
        moveButtons = new Button[mainGrid.getColumnCount()];
        cells = new ImageView[mainGrid.getRowCount() - 1][mainGrid.getColumnCount()];

        for (Node child : mainGrid.getChildren()) {
            // On récupère la ligne où le label se trouve.
            Integer row = GridPane.getRowIndex(child);
            if (row == null) {
                row = 0;
            }

            // On récupère la colonne où le label se trouve.
            Integer column = GridPane.getColumnIndex(child);
            if (column == null) {
                column = 0;
            }

            if (child instanceof Button button) {
                moveButtons[column] = button;

            } else if (child instanceof ImageView view) {
                cells[row - 1][column] = view;
            }
        }init();
    }

    private void afficheGrid(){
        for(int i = 0 ; i < cells.length ; i++){
            for(int j = 0 ; j < cells[i].length; j ++){
                cells[i][j].setImage(loadImage(game.getGrille().get(i,j).toString()));
            }
        }
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

    private void setDesable(boolean bool){
        for(int i = 0 ; i < moveButtons.length; i++){
            moveButtons[i].setDisable(bool);
        }
    }

    public void setGame(Game game){
        this.game = game;
    }

    private Image loadImage(String name) {
        URL urlImage = getClass().getResource("../view/images/" + name + ".gif"); //On recuper le fichier contenat l'image
        return new Image(urlImage.toExternalForm(), 50, 50, true, false); //ON en fait une representation graphique qu'on peut afficher
    }
}
