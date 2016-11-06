package com.imudges.MagicTower.things.monster;

import com.imudges.MagicTower.things.ModelRectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 怪物基本类
 * Created by killer on 2016/9/23.
 */
public class Monster extends ModelRectangle{
    private int imgWidth;
    private ImageView imageView;
    public Monster(int x, int y, int xLength, int yLength, Image image){
        setX(x);
        setY(y);
        setxLength(xLength);
        setyLength(yLength);
        this.imgWidth = 8;

        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(32 ,0 , xLength, yLength));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        getChildren().add(imageView);
    }
    public void remove(){
        imageView.setImage(null);
        setxLength(0);
        setyLength(0);
    }
}
