/**
 * Sample Skeleton for 'puissance4.fxml' Controller Class
 */

package fr.univartois.butinfo.ihm.fourinaline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;


public class  FourInALineController{

    private Button[] tabButton;
    private ImageView[][] tabImage;

    @FXML // fx:id="mainGrid"
    private GridPane mainGrid; // Value injected by FXMLLoader

    private Token jeton; // On declare la variable (on la juste creer on doit donc affecter une valeur a cette var)

    @FXML // fx:id="textWinner"
    private Label textWinner; // Value injected by FXMLLoader

    public Button[] getTabButton() {
        return tabButton;
    }

    public ImageView[][] getTabImage() {
        return tabImage;
    }

    private Grid grid;

    public FourInALineController(){
        grid = new Grid();
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
            int rowIndex = grid.play(jeton, columnIndex);
            if (rowIndex != -1) {
                cells[rowIndex][columnIndex].setImage(loadImage(grid.get(rowIndex,columnIndex).toString()));
                if (grid.findFourInALine().isPresent() || grid.isFull()) {
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


    public Button[] getMoveButtons() {
        return moveButtons;
    }

    public ImageView[][] getCells() {
        return cells;
    }

    public GridPane getMainGrid() {
        return mainGrid;
    }

    private Button[] moveButtons;
    private ImageView[][] cells;


    private void afficheGrid(){
        for(int i = 0 ; i < cells.length ; i++){
            for(int j = 0 ; j < cells[i].length; j ++){
                cells[i][j].setImage(loadImage(grid.get(i,j).toString()));
            }
        }
    }

    private void init(){
        grid.clear();
        setDesable(false);
        jeton = Token.YELLOW;
        afficheGrid();
    }

    private void setDesable(boolean bool){
        for(int i = 0 ; i < moveButtons.length; i++){
            moveButtons[i].setDisable(bool);
        }
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


    private Image loadImage(String name) {
        URL urlImage = getClass().getResource("./images/" + name + ".gif"); //On recuper le fichier contenat l'image
        return new Image(urlImage.toExternalForm(), 50, 50, true, false); //ON en fait une representation graphique qu'on peut afficher
    }





}
