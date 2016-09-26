package com.imudges.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 程序入口
 */
public class Main extends Application {
    private GamePanel mPanel;

    @Override
    public void start(Stage stage) throws Exception {
        mPanel = new GamePanel();
        final Scene scene = new Scene(mPanel,800, 600);
        mPanel.load();
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("零式之战");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        mPanel.stop();
        super.stop();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(Main.class, args);
    }

}