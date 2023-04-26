package com.example.snakeladdergame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize=40,width=10,height=10;
    public static final int buttonLine=height*tileSize +50,infoLine=buttonLine-30;
    private static Dice dice=new Dice();
    private Player playerOne,playerTwo;
    private boolean gameStarted=false,playerOneTurn=false,playerTowTurn=false;
    private Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width*tileSize,height*tileSize +100);
for (int i=0;i<height;i++){
    for(int j=0;j<width;j++){
        Tile tile=new Tile(tileSize);
        tile.setTranslateX(j*tileSize);
        tile.setTranslateY(i*tileSize);
        root.getChildren().add(tile);
    }
}
        Image img=new Image( "C:\\Users\\LENOVO\\IdeaProjects\\SnakeLadderGame\\src\\main\\img.png");
        ImageView board=new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);
        //Buttons
        Button playerOneButton=new Button( "Player One");
        Button playerTwoButton=new Button( "Player Two");
        Button startButton=new Button( "  Start  ");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(160);

        //Create Label to display text
        Label playerOneLabel=new Label( "");
        Label playerTwoLabel=new Label( "");
        Label diceLabel=new Label( "Start The Game");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        playerOne=new Player(tileSize, Color.BLACK,"Virat");
        playerTwo=new Player(tileSize-5, Color.WHITE,"ABD");

playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
        if(gameStarted){
            if(playerOneTurn){
                //play
                int diceValue=dice.getRolledDiceValue();
                diceLabel.setText("Dice Value : "+diceValue);
                playerOne.movePlayer(diceValue);
                //disabling player 1 after moving and check winning condition
                if(playerOne.isWinner()){
                    diceLabel.setText("Winner is"+playerOne.getName());
                    playerOneTurn = false;
                    playerOneButton.setDisable(true);
                    playerOneLabel.setText("");
                    //enabling another player
                    playerTowTurn = true;
                    playerTwoButton.setDisable(true);
                    playerTwoLabel.setText("");

                    startButton.setDisable(false);
                    startButton.setText("ReStart");
                    gameStarted=false;
                }
                else {
                    playerOneTurn = false;
                    playerOneButton.setDisable(true);
                    playerOneLabel.setText("");
                    //enabling another player
                    playerTowTurn = true;
                    playerTwoButton.setDisable(false);
                    playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                }
            }
        }

    }
});

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            if(gameStarted){
                                                if(playerTowTurn){
                                                    //play
                                                    int diceValue=dice.getRolledDiceValue();
                                                    diceLabel.setText("Dice Value : "+diceValue);
                                                    playerTwo.movePlayer(diceValue);
                                                    //disabling player 2 after moving and check winning condition
                                                    if(playerTwo.isWinner()){
                                                        diceLabel.setText("Winner is"+playerTwo.getName());
                                                        playerOneTurn = false;
                                                        playerOneButton.setDisable(true);
                                                        playerOneLabel.setText("");
                                                        //enabling another player
                                                        playerTowTurn = false;
                                                        playerTwoButton.setDisable(true);
                                                        playerTwoLabel.setText("");

                                                        startButton.setDisable(false);
                                                        startButton.setText("ReStart");
                                                    }
                                                    else {
                                                        playerOneTurn = true;
                                                        playerOneButton.setDisable(false);
                                                        playerOneLabel.setText("your turn " + playerOne.getName());
                                                        //enabling another player
                                                        playerTowTurn = true;
                                                        playerTwoButton.setDisable(true);
                                                        playerTwoLabel.setText("");
                                                    }
                                                }
                                            }

                                        }
                                    });

startButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent) {
        gameStarted=true;
        diceLabel.setText("Game is Started");
        startButton.setDisable(true);
        playerOneTurn=true;
        playerOneLabel.setText("your Turn "+playerOne.getName());
        playerOneButton.setDisable(false);
        playerOne.startingPosition();

        playerTowTurn=false;
        playerTwoLabel.setText("");
        playerTwoButton.setDisable(true);
        playerTwo.startingPosition();
    }
});
        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,playerOneLabel,playerTwoLabel,diceLabel,playerOne.getCoin(),playerTwo.getCoin());
                return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snakes and ladders game !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}