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


public class  FourInALineController{

    private Button[] moveButtons;
    private ImageView[][] cells;

    private Grid grid;

    @FXML // fx:id="mainGrid"
    private GridPane mainGrid; // Value injected by FXMLLoader

    @FXML // fx:id="textWinner"
    private Label textWinner; // Value injected by FXMLLoader

    public FourInALineController(){
        grid = new Grid();
    }

    public GridPane getMainGrid() {
        return mainGrid;
    }

    @FXML // fx:id="texteWinner"
    private Label texteWinner; // Value injected by FXMLLoader

    @FXML // fx:id="tourPersonne"
    private Label tourPersonne; // Value injected by FXMLLoader

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
}
