package com.example.game;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class HelloApplication extends Application {
    Label txt= new Label("SCORE: 0");

    int score=1;
    TranslateTransition tt=new TranslateTransition();

    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 450);

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        txt.setEffect(is);
        txt.setFont(Font.font(null, FontWeight.BOLD, 25));
        txt.setTextFill(Color.YELLOW);
        txt.setLayoutX(300);
        txt.setLayoutY(20);
        stage.setTitle("Helicopter game!");

        scene.getStylesheets().add("style.css");


        ImageView ship = createShip(scene);
        ImageView cloude = createCloude(scene);
        ImageView cloude2 = createCloude2(scene);
        ImageView cloude3 = createCloude3(scene);
        ImageView cloude4 = createCloude4(scene);
        ImageView coin = createCoin(scene);
        ImageView chen = createChen(scene);
        ImageView five = createFive(scene);
        root.getChildren().addAll(ship, cloude, coin, cloude2, cloude3, cloude4, five, chen);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (var event) -> {
            double x = ship.getX();
            double y = ship.getY();

            switch (event.getCode()) {
                case LEFT -> ship.setX(x - 10);
                case RIGHT -> ship.setX(x + 10);
                case UP -> ship.setY(y - 10);
                case DOWN -> ship.setY(y + 10);

            }

            if (ship.getBoundsInParent().intersects(cloude.getBoundsInParent()) || ship.getBoundsInParent().intersects(cloude2.getBoundsInParent()) || ship.getBoundsInParent().intersects(cloude3.getBoundsInParent()) || ship.getBoundsInParent().intersects(cloude4.getBoundsInParent())) {
                AnimationTimer detector = new AnimationTimer() {
                    @Override
                    public void handle(long l) {
                        // CollisionDetector(ship,enemy,stage);
                        CollisionDetector(ship,cloude,stage);
                        CollisionDetector(ship,cloude2,stage);
                        CollisionDetector(ship,cloude3,stage);
                        CollisionDetector(ship,cloude4,stage);

                        coinDetector(ship,coin);
                        coinDetector(ship,chen);
                        coinDetector(ship,five);
                    }

                    private void coinDetector(ImageView ship, ImageView coin) {
                        if(ship.getBoundsInParent().intersects(coin.getBoundsInParent())){
                            score=score+1;
                            txt.setText("SCORE:" + score);
                         root.getChildren().remove(createCoin(scene));
                            coin.setImage(null);
                        }
                        else {

                            coin.setImage(new Image("coin.png"));
                        }
                    }

                    private void CollisionDetector(ImageView ship, ImageView boy, Stage stage) {
                        if(ship.getBoundsInParent().intersects(boy.getBoundsInParent())){
                            ship.setImage(new Image("crash.png"));
                            PauseTransition pause =new PauseTransition(Duration.millis(1200));
                            pause.setOnFinished(actionEvent -> stage.close());
                            pause.play();

                        }
                    }
                };
                detector.start();



                root.getChildren().addAll(ship,cloude,cloude2,cloude3,cloude4,coin,chen,five,txt);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }


            System.out.println("game over");
                ship.setImage(new Image("fire.png"));

                scene.addEventFilter(KeyEvent.KEY_PRESSED, event1 -> {


                    switch (event.getCode()) {
                        case UP -> ship.setY(y - 0);
                        case DOWN -> ship.setY(y + 0);
                        case LEFT -> ship.setX(y - 0);
                        case RIGHT -> ship.setX(y + 0);

                    }
                });

        });
        stage.setScene(scene);
        stage.show();
    }


    private ImageView createCloude(Scene scene){
        ImageView cloud = new ImageView(new Image("cloud3.png"));
        cloud.setFitWidth(100);
        cloud.setFitHeight(100);
        cloud.setY(100);
        cloud.setX(1800);
        TranslateTransition c = new TranslateTransition(Duration.millis(12000),cloud);
        c.setByX(-2000);
        c.setCycleCount(Integer.MAX_VALUE);
        c.play();

        return cloud;

    }
    private ImageView createCloude2(Scene scene){
        ImageView leru = new ImageView(new Image("cloud3.png"));
        leru.setFitWidth(100);
        leru.setFitHeight(100);
        leru.setX(850);
        leru.setY(30);

        TranslateTransition tt = new TranslateTransition(Duration.millis(30000),  leru);
        tt.setByX(-2000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();

        return leru;
    }
    private ImageView createCloude3(Scene scene){
        ImageView lik = new ImageView(new Image("cloud3.png"));



        lik.setFitWidth(100);
        lik.setFitHeight(100);
        lik.setX(850);
        lik.setY(30);

        TranslateTransition tt = new TranslateTransition(Duration.millis(30000),  lik);
        tt.setByX(-2000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return lik;
    }
    private ImageView createCloude4(Scene scene){
        ImageView molatsi = new ImageView(new Image("cloud3.png"));
        molatsi.setFitWidth(100);
        molatsi.setFitHeight(100);
        molatsi.setX(850);
        molatsi.setY(10);

        TranslateTransition tt = new TranslateTransition(Duration.millis(8000),  molatsi);
        tt.setByX(-2000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();

        return molatsi;
    }
    private ImageView createCoin(Scene scene){
        ImageView coin = new ImageView(new Image("cloud3.png"));
        coin.setFitWidth(100);
        coin.setFitHeight(100);
        coin.setY(300);
        coin.setX(1500);
        TranslateTransition c = new TranslateTransition(Duration.millis(14000),coin);
        c.setByX(-1800);
        c.setCycleCount(Integer.MAX_VALUE);
        c.play();

        return coin;
    }
    private ImageView createChen(Scene scene){
        Image coin = new Image("chen.png");
        ImageView coin2 = new ImageView(coin);


        coin2.setFitWidth(35);
        coin2.setFitHeight(35);
        coin2.setX(850);
        coin2.setY(200);

        TranslateTransition tt = new TranslateTransition(Duration.millis(50000),coin2);
        tt.setByX(-5000);
        tt.setCycleCount(Integer.MAX_VALUE);
        tt.play();
        return coin2;
    }
    private ImageView createFive(Scene scene){
        ImageView five = new ImageView(new Image("coin.png"));
        five.setFitWidth(100);
        five.setFitHeight(100);
        five.setY(100);
        five.setX(1500);
        TranslateTransition c = new TranslateTransition(Duration.millis(10000),five);
        c.setByX(-1800);
        c.setCycleCount(Integer.MAX_VALUE);
        c.play();


        return five;
    }
    private ImageView createShip(Scene scene) {
        ImageView image = new ImageView(new Image("Airplane.png"));
        image.setFitWidth(140);
        image.setFitHeight(70);
        image.setLayoutX(10);
        image.setLayoutY(-200);

        image.setY(scene.getHeight() - image.getFitHeight());
        return image;
    }


    public static void main(String[] args) {
        launch();
    }
}