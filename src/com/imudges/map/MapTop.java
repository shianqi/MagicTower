package com.imudges.map;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 地图覆盖层
 * Created by killer on 2016/9/26.
 */
public class MapTop extends Parent{
    private Image actor;
    private ImageView mImageView;

    private int mapId;
    private int mapWidth;
    private int mapHeight;
    private String mapName;
    private int pngWidth;
    public int[][] top;

    public MapTop(int id,int width, int height,int pngWidth, String name,String top){
        mapId = id;
        mapWidth = width;
        mapHeight = height;
        this.pngWidth = pngWidth;
        mapName = name;
        this.top = new int[height][width];
        setTop(top);
        drow();
    }

    public void drow(){
        initMap(top);
    }

    public void setTop(String str){
        String[] a = str.split("-");
        for(int i=0;i<a.length;i++){
            String[] backgroundStr= a[i].split(",");
            for(int j = 0;j<backgroundStr.length;j++){
                top[i][j] = Integer.parseInt(backgroundStr[j]);
            }
        }
    }

    public void initMap(int[][] arr){
        actor = new Image(getClass().getResourceAsStream(mapName));
        for(int i=0;i<mapHeight;i++){
            for(int j=0;j<mapWidth;j++){
                mImageView = new ImageView(actor);
                mImageView.setViewport(new Rectangle2D(getPngX(arr[i][j]),getPngY(arr[i][j]), 32, 32));
                mImageView.setLayoutX(32*j);
                mImageView.setLayoutY(32*i);
                getChildren().add(mImageView);
            }
        }
    }

    public int getPngX(int id){
        int width = (id%this.pngWidth)*32;
        return width;
    }

    public int getPngY(int id){
        int height = id/this.pngWidth*32;
        return height;
    }
}
