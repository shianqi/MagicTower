package com.imudges.map;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.imudges.synchron.Synchron;

import java.awt.*;

/**
 * 地图类
 * Created by killer on 2016/9/24.
 */
public class Map extends Parent{
    private Image actor;
    private ImageView mImageView;

    private int mapId;
    private int mapWidth;
    private int mapHeight;
    private String mapName;
    private int pngWidth;
    public int[][] background;
    public int[][] things;

    public Map(int id,int width, int height,int pngWidth, String name,String background,String thingsString){
        mapId = id;
        mapWidth = width;
        mapHeight = height;
        this.pngWidth = pngWidth;
        mapName = name;
        this.background = new int[height][width];
        this.things = new int[height][width];
        setBackground(background);
        setThings(thingsString);
        drow();
    }

    public void setBackground(String str){
        String[] a = str.split("-");
        for(int i=0;i<a.length;i++){
            String[] backgroundStr= a[i].split(",");
            for(int j = 0;j<backgroundStr.length;j++){
                background[i][j] = Integer.parseInt(backgroundStr[j]);
            }
        }
    }

    public void setThings(String str){
        String[] a = str.split("-");
        for(int i=0;i<a.length;i++){
            String[] backgroundStr= a[i].split(",");
            for(int j = 0;j<backgroundStr.length;j++){
                things[i][j] = Integer.parseInt(backgroundStr[j]);
            }
        }
    }

    public void drow(){
        initMap(background);
        initMap(things);
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

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getPngWidth() {
        return pngWidth;
    }

    public void setPngWidth(int pngWidth) {
        this.pngWidth = pngWidth;
    }
}
