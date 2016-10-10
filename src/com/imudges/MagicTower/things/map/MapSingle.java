package com.imudges.MagicTower.things.map;

import com.imudges.MagicTower.things.ModelRectangle;
/**
 * 单独的地图文件
 * Created by killer on 2016/9/29.
 */
public class MapSingle extends ModelRectangle{
//    private ImageView imageView;
    private int imgWidth;

    public MapSingle(int x, int y, int xLength, int yLength){
        setX(x);
        setY(y);
        setxLength(xLength);
        setyLength(yLength);

        this.imgWidth = 8;
//        this.imageView = new ImageView(new Image(getClass().getResourceAsStream("map1.png")));
//        imageView.setViewport(new Rectangle2D(getPngX(1),getPngY(1), 32, 32));
//        imageView.setLayoutX(32*x);
//        imageView.setLayoutY(32*y);
    }

    public MapSingle(int x, int y, int xLength, int yLength, String imgUrl, int imgWidth, int index){
        setX(x);
        setY(y);
        setxLength(xLength);
        setyLength(yLength);
        this.imgWidth = imgWidth;
//        this.imageView = new ImageView(new Image(getClass().getResourceAsStream(imgUrl)));
//        imageView.setViewport(new Rectangle2D(getPngX(index),getPngY(index), 32, 32));
//        imageView.setLayoutX(32*x);
//        imageView.setLayoutY(32*y);
    }

    public int getPngX(int id){
        return (id%this.imgWidth)*32;
    }

    public int getPngY(int id){
        return id/this.imgWidth*32;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int pngWidth) {
        this.imgWidth = pngWidth;
    }

}
