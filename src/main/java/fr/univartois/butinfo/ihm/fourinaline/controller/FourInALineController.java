/**
 * Sample Skeleton for 'puissance4.fxml' Controller Class
 */

package fr.univartois.butinfo.ihm.fourinaline.controller;

import fr.univartois.butinfo.ihm.fourinaline.model.Game;
import fr.univartois.butinfo.ihm.fourinaline.model.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;


public class  FourInALineController{

    /**
     *  Tableau de button.
     */
    private Button[] tabButton;

    private ImageView[][] tabImage;

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
     *  Attributs gridPane.
     *
     */
    @FXML // fx:id="mainGrid"
    private GridPane mainGrid; // Value injected by FXMLLoader

    /**
     * Creer la gridPane.
     *
     * @return On return la gridPane
     */
    public GridPane getMainGrid() {
        return mainGrid;
    }

    private Game game;
    /* ======== Contruscteur ======= */
    public FourInALineController(){
        game = new Game();
    }
    /*  =========== BUTTONS  ===========*/

    @FXML
    void onButtonCollumn1(ActionEvent event) {
        game.placeToken(0, event);
    }

    @FXML
    void onButtonCollumn2(ActionEvent event) {
        game.placeToken(1, event);
    }

    @FXML
    void onButtonCollumn3(ActionEvent event) {
        game.placeToken(2, event);
    }

    @FXML
    void onButtonCollumn4(ActionEvent event) {
        game.placeToken(3, event);
    }

    @FXML
    void onButtonCollumn5(ActionEvent event) {
        game.placeToken(4, event);
    }

    @FXML
    void onButtonCollumn6(ActionEvent event) {
        game.placeToken(5, event);
    }

    @FXML
    void onButtonCollumn7(ActionEvent event) {
        game.placeToken(6, event);
    }


    @FXML
    void onButtonReset(ActionEvent event) {
        game.initGame();
    }

    /* ======== Methodes ======= */

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
        }game.initGame();
    }

}
