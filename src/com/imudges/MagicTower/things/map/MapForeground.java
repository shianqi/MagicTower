package com.imudges.MagicTower.things.map;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 前景地图类
 * Created by killer on 2016/9/30.
 */
public class MapForeground extends Parent {
    public int fontground[][];
    private ImageView mImageView;

    public MapForeground(){

    }

    public int getPngX(int id){
        return (id%8)*32;
    }

    public int getPngY(int id){
        return id/8*32;
    }

    public void dorw(){
        Image image = new Image(getClass().getResourceAsStream("map2.png"));
        for(int i = 0;i<fontground.length;i++){
            for(int j = 0;j<fontground[i].length;j++){
                mImageView = new ImageView(image);
                mImageView.setViewport(new Rectangle2D(getPngX(fontground[i][j]),getPngY(fontground[i][j]), 32, 32));
                mImageView.setLayoutX(32*j);
                mImageView.setLayoutY(32*i);
                getChildren().add(mImageView);
            }
        }
    }

}
