package com.imudges.MagicTower.things.map;

import com.imudges.MagicTower.things.ModelRectangle;
import com.imudges.MagicTower.things.monster.Monster;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Timer;

/**
 * 地图实体层
 * Created by killer on 2016/9/30.
 */
public class MapThings extends Parent{
    public static class Things{
        public int goldBox = 1001;  //宝箱
        public int monster = 1002;  //怪物
        public int noThings = 999;    //路
        public int obstacle = 1000;   //障碍物
    }
    public ArrayList<ModelRectangle> thingsList;
    public int things[][];
    Image image = new Image(getClass().getResourceAsStream("map2.png"));
    private ImageView mImageView;

    public int getPngX(int id){
        return (id%8)*32;
    }

    public int getPngY(int id){
        return id/8*32;
    }

    public MapThings(){
        thingsList = new ArrayList<>();
    }

    public void dorw(){
        for(int i = 0;i<things.length;i++){
            for(int j = 0;j<things[i].length;j++){
                mImageView = new ImageView(image);
                mImageView.setViewport(new Rectangle2D(getPngX(things[i][j]),getPngY(things[i][j]), 32, 32));
                mImageView.setLayoutX(32*j);
                mImageView.setLayoutY(32*i);
                getChildren().add(mImageView);

                if(things[i][j]!=999){
                    MapSingle mapSingle = new MapSingle(j*32,i*32,32, 10);
                    thingsList.add(mapSingle);
                }
            }
        }
//        createMonster();
    }
}
