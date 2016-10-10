package com.imudges.MagicTower.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 程序入口
 */
public class Main extends Application {
    private GameLoader gameLoader;

    @Override
    public void start(Stage stage) throws Exception {
        gameLoader = new GameLoader();
        final Scene scene = new Scene(gameLoader,20*32, 15*32);
        gameLoader.start();
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("零式之战");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        gameLoader.stop();
        super.stop();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(Main.class, args);
    }

}